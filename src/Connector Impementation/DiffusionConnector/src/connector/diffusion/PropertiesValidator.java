/*
 * PropertiesValidator
 * 
 * The properties validator provides a set of helper methods used by the input and output connectors to validate  
 * the properties passed to them by the framework.
 * 
 * Copyright (c) 2016, Push Technology Ltd., All rights reserved.
 * 
 * This program file is distributed under the MIT license. A copy of this license is included in the home directory
 * of the connector on OT4i.
 * 
 */

package connector.diffusion;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.pushtechnology.diffusion.client.Diffusion;
import com.pushtechnology.diffusion.client.topics.TopicSelectors;

public class PropertiesValidator {
	
	static final private String ipPattern = "(\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3})";
	static final private String ipV6Pattern = "\\[([a-zA-Z0-9:]+)\\]";
	static final private String hostPattern = "([\\w\\.\\-]+)";  
	
	public static boolean ValidateHostName(String hostName) {
		Pattern p = Pattern.compile( ipPattern + "|" + ipV6Pattern + "|" + hostPattern );
		Matcher m = p.matcher( hostName );
		if( m.matches())
			return true;
		else
			return false;
	}

	public static boolean ValidatePort(String port) {
		try {
			@SuppressWarnings("unused")
			Integer i = Integer.parseInt(port);
			return true;
		}
		catch(NumberFormatException e) {
			return false;
		}
	}
	
	public static boolean ValidateTopic(String topic) {
		return ValidateTopicSelector(">" + topic);
	}

	public static boolean ValidateTopicSelector(String topicSelector) {
		TopicSelectors selectors = Diffusion.topicSelectors();
		try {
			selectors.parse(topicSelector);
			return true;
		}
		catch(IllegalArgumentException e) {
			return false;
		}
	}
}
