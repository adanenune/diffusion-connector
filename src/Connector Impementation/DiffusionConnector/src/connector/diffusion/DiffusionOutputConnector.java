/*
 * DiffusionOutputConnector
 * 
 * The output connector is responsible for publishing data passed to it by the framework over a configured Diffusion topic.
 * 
 * Copyright (c) 2016, Push Technology Ltd., All rights reserved.
 * 
 * This program file is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3.0 of the License, or (at your option) any later version.
 * 
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 * 
 */

package connector.diffusion;

import java.util.Properties;

import com.ibm.connectors.AbstractOutputConnector;
import com.ibm.connectors.AbstractOutputInteraction;
import com.ibm.connectors.ConnectorCallback;
import com.ibm.connectors.ConnectorException;
import com.ibm.connectors.OutputInteraction;

import com.pushtechnology.diffusion.client.Diffusion;
import com.pushtechnology.diffusion.client.callbacks.ErrorReason;
import com.pushtechnology.diffusion.client.callbacks.Registration;
import com.pushtechnology.diffusion.client.session.Session;
import com.pushtechnology.diffusion.client.session.SessionEstablishmentException;
import com.pushtechnology.diffusion.client.features.control.topics.TopicAddFailReason;
import com.pushtechnology.diffusion.client.features.control.topics.TopicControl;
import com.pushtechnology.diffusion.client.features.control.topics.TopicControl.AddCallback;
import com.pushtechnology.diffusion.client.topics.details.SingleValueTopicDetails;
import com.pushtechnology.diffusion.client.topics.details.TopicDetails;
import com.pushtechnology.diffusion.client.features.control.topics.TopicUpdateControl;
import com.pushtechnology.diffusion.client.features.control.topics.TopicUpdateControl.UpdateSource;
import com.pushtechnology.diffusion.client.features.control.topics.TopicUpdateControl.Updater;
import com.pushtechnology.diffusion.client.features.control.topics.TopicUpdateControl.Updater.UpdateCallback;


public class DiffusionOutputConnector extends AbstractOutputConnector implements UpdateSource {
	private String myName;
	private String hostName;
	private String port;
	private String protocol;
	private String userName;
	private String password;
	private String topic;
	private Session session = null;
    private TopicControl topicControl;
    private TopicUpdateControl updateControl;
    private Updater updater = null;

	public DiffusionOutputConnector( String name) {
		myName= name;
	}

	@Override
	protected void onInitialise() throws Exception {
		// Initialise connection details from properties
		hostName = getProperties().getProperty("hostName");
		port     = getProperties().getProperty("port");
		protocol = getProperties().getProperty("protocol");
		userName = getProperties().getProperty("userName");
		password = getProperties().getProperty("password");
		topic    = getProperties().getProperty("topic");
		
		// Validate the properties. Log issue and throw exception if any are invalid. 
		// TODO : check behaviour of framework if exception is thrown.
		boolean h = true, p = true, t = true;
		if(!(h = PropertiesValidator.ValidateHostName(hostName)) ||
		   !(p = PropertiesValidator.ValidatePort(port)) ||
		   !(t = PropertiesValidator.ValidateTopic(topic))) {
			String logString = "";
			if(!h) logString += "Host Name";
			if(!h&&!p) logString += ", ";
			if(!p) logString += "Port";
			if(!h||!p) logString += ", ";
			if(!t) logString += "Topic";
			logMessage("Invalid configuration : " + logString);
			throw new ConnectorException("DiffusionOutputConnector : Invalid configuration.");
		}
		
		String URL = protocol + "://" + hostName + ":" + port;
			
		// Create Diffusion connection
		if(session == null) {
			// The IIB framework or Connector API set the Java context classpath. We need to set 
			// the one used by Diffusion when we first use the Diffusion API and re-instate the 
			// default one once done. Standard Java context classpath loader issue workaround...
			final Thread thread = Thread.currentThread();
        	final ClassLoader ccl = thread.getContextClassLoader();

        	try {
        		thread.setContextClassLoader(
                     Diffusion.class.getClassLoader());
        	 session = Diffusion.sessions().principal(userName).password(password).open(URL);
        	}
        	catch( SessionEstablishmentException e) {
        		logMessage("Failed to connect to Diffusion : " + URL + " : " + e.getMessage());
        	}
        	finally {
            	thread.setContextClassLoader(ccl);
        	}
		}
		
		// Get TopicControl feature and create configured topic
		if(session != null) {
			topicControl = session.feature(TopicControl.class);
			updateControl = session.feature(TopicUpdateControl.class);
			final SingleValueTopicDetails.Builder builder = topicControl.newDetailsBuilder(SingleValueTopicDetails.Builder.class);
			final TopicDetails details = builder.metadata(Diffusion.metadata().string("IIBPayload")).build();
			topicControl.addTopic(
                topic,
                details,
                new TopicAddCallback(this));
		}
		super.onInitialise();
	}

	@Override
	public OutputInteraction createOutputInteraction() throws ConnectorException {
		return new DiffusionOutputInteraction();
	}

	@Override
	public void terminate() throws ConnectorException {
		if(session != null)
		{
			session.close();
			session = null;
		}
		super.terminate();
	}
	
	private void sendDiffusionMessage(String data) {
		final Thread thread = Thread.currentThread();
        final ClassLoader ccl = thread.getContextClassLoader();

		// There seems to be an issue with the context classpath loader in IIB or the Connector API. We temporarily instate 
		// the one used by Diffusion when we first use the Diffusion API and re-instate the default one once done. Standard
		// Java context classpath loader issue workaround...
        try {
        	 thread.setContextClassLoader(
                     Diffusion.class.getClassLoader());
        	 updater.update(
                topic,
                Diffusion.content().newContent(data),
                new UpdateCallback.Default());
        }
        finally {
            thread.setContextClassLoader(ccl);
        }
	}
	
	// Private inner class implementation of the OutPutInteraction. A call to instantiate this object is made each time 
	// a message is to be sent from IIB.
	private class DiffusionOutputInteraction extends AbstractOutputInteraction {
		
		@Override
		public long asyncSend(Properties arg0, Object arg1, ConnectorCallback arg2) throws ConnectorException {
			// Not yet implemented apparently...
			return 0;
		}

		@Override
		public Properties send(Properties props, Object data) throws ConnectorException {
			// Send via Diffusion
			String theData = new String((byte[]) data);
			sendDiffusionMessage(theData);
			return null;
		}
	}
	
	// Topic add callback. The only use of this class is to provide register the parent class as a topic updater
	// once the topic has been added
	private class TopicAddCallback implements AddCallback {
		UpdateSource source;
		public TopicAddCallback(UpdateSource updateSource) {
			source = updateSource;
		}
		
		@Override
		public void onDiscard() {
		}

		@Override
		public void onTopicAddFailed(String theTopic, TopicAddFailReason reason) {
			if( reason == TopicAddFailReason.EXISTS)
				updateControl.registerUpdateSource(theTopic, source);
			else
				logMessage("Failed to add topic " + theTopic + " Reason : " + reason.toString());
		}

		@Override
		public void onTopicAdded(String theTopic) {
			// Register the outer class object as the topic updater
			updateControl.registerUpdateSource(theTopic, source);
		}
	}
	
	// Methods required of a Diffusion Update Source
	@Override
	public void onClose(String arg0) {
	}

	@Override
	public void onError(String arg0, ErrorReason arg1) {
	}

	@Override
	public void onRegistered(String arg0, Registration arg1) {
	}

	@Override
	public void onActive(String theTopic, Updater theUpdater) {
		if( theUpdater == null)
			logMessage("Failed to create updater.");
		updater = theUpdater;
	}

	@Override
	public void onStandby(String arg0) {
	}
	
	private void logMessage( String message ) {
		getLogger().info("DiffusionOutputConnector : " + myName + " : " + message);
	}

}
