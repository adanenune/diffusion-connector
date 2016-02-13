/*
 * DiffusionInputConnector
 * 
 * The input connector factory is responsible for subscribing to a configured topic selector.
 * and passing the payload of messages received to the framework.
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

import com.ibm.connectors.AbstractInputConnector;
import com.ibm.connectors.ConnectorException;
import com.pushtechnology.diffusion.client.Diffusion;
import com.pushtechnology.diffusion.client.callbacks.ErrorReason;
import com.pushtechnology.diffusion.client.session.Session;
import com.pushtechnology.diffusion.client.session.SessionEstablishmentException;
import com.pushtechnology.diffusion.client.topics.details.TopicDetails;
import com.pushtechnology.diffusion.client.features.Topics;
import com.pushtechnology.diffusion.client.features.Topics.TopicStream;
import com.pushtechnology.diffusion.client.content.Content;
import com.pushtechnology.diffusion.client.types.UpdateContext;

public class DiffusionInputConnector extends AbstractInputConnector {

	private String myName;
	private String hostName;
	private String port;
	private String protocol;
	private String userName;
	private String password;
	private String topicSelector;
	private Session session = null;
	private boolean started = false;
	private boolean connected = false;
	
	public DiffusionInputConnector( String name) {
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
		topicSelector    = getProperties().getProperty("topicSelector");

		// Validate the properties. Log issue and throw exception if any are invalid. 
		// TODO : check behaviour of framework if exception is thrown.
		boolean h = true, p = true, t = true;
		if(!(h = PropertiesValidator.ValidateHostName(hostName)) ||
		   !(p = PropertiesValidator.ValidatePort(port)) ||
		   !(t = PropertiesValidator.ValidateTopicSelector(topicSelector))) {
			String logString = "";
			if(!h) logString += "Host Name";
			if(!h&&!p) logString += ", ";
			if(!p) logString += "Port";
			if(!h||!p) logString += ", ";
			if(!t) logString += "Topic Selector";
			logMessage("Invalid configuration : " + logString);
			throw new ConnectorException("DiffusionInputConnector : Invalid configuration.");
		}
		
		// Create Diffusion connection
		if( session == null ) {
			// The IIB framework or Connector API set the Java context classpath. We need to set 
			// the one used by Diffusion when we first use the Diffusion API and re-instate the 
			// default one once done. Standard Java context classpath loader issue workaround...
			final Thread thread = Thread.currentThread();
			final ClassLoader ccl = thread.getContextClassLoader();
			String URL = protocol + "://" + hostName + ":" + port;
			
			try {
				thread.setContextClassLoader(
						Diffusion.class.getClassLoader());

				session = Diffusion.sessions().principal(userName).password(password)
				       .open(URL);
       	  
			 }
			 catch(SessionEstablishmentException e) {
				 logMessage("Cannot connect : " + URL + " : " + e.getMessage());
				 connected = false;
			 }
			 finally {
		 		thread.setContextClassLoader(ccl);
		 	 }
		}
		connected = true;
	}
		
	@Override
	public boolean isStarted() {
		return started;
	}

	@Override
	public void start() throws ConnectorException {
		// If we did not connect to Diffusion, just return...
		
		if(connected) {
			// Subscribe to configured topic selector
			final Topics topics = session.feature(Topics.class);
			topics.addTopicStream(topicSelector, new DiffusionTopicStream());
			topics.subscribe(
					Diffusion.topicSelectors().anyOf(topicSelector),
						new Topics.CompletionCallback.Default());
        started = true;
		}
	}

	@Override
	public void stop() throws ConnectorException {
		// Close session
		if(connected)
			session.close();
	}

	private class DiffusionTopicStream extends TopicStream.Default {
        @Override
		public void onError(ErrorReason errorReason) {
    		logMessage("Subscription error : " + errorReason);
			
			super.onError(errorReason);
		}

		@SuppressWarnings("unused")
		@Override
		public void onSubscription(String topicPath, TopicDetails details) {
    		final Thread thread = Thread.currentThread();
            final ClassLoader ccl = thread.getContextClassLoader();

			// Java context classpath loader issue workaround...
    		try {
            	 thread.setContextClassLoader(
                         Diffusion.class.getClassLoader());
            	Content content;
            	content = null;
            }
            finally {
            	thread.setContextClassLoader(ccl);
            }

			super.onSubscription(topicPath, details);
		}

		@Override
        public void onTopicUpdate(String topic, Content content, UpdateContext context) {
    		Properties props = new Properties();
    		props.setProperty("topic", topic);
    		getCallback().processInboundData(content.toBytes(), props);
        }
    }
	
	private void logMessage( String message ) {
		getLogger().info("DiffusionInputConnector : " + myName + " : " + message);
	}
}
