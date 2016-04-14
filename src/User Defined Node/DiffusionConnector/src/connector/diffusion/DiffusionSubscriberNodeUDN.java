package connector.diffusion;

import com.ibm.broker.config.appdev.InputTerminal;
import com.ibm.broker.config.appdev.Node;
import com.ibm.broker.config.appdev.NodeProperty;
import com.ibm.broker.config.appdev.OutputTerminal;

/*** 
 * <p>  <I>DiffusionSubscriberNodeUDN</I> instance</p>
 * <p></p>
 */
public class DiffusionSubscriberNodeUDN extends Node {

	private static final long serialVersionUID = 1L;

	// Node constants
	protected final static String NODE_TYPE_NAME = "connector/diffusion/EventInputNode";
	protected final static String NODE_GRAPHIC_16 = "platform:/plugin/DiffusionConnector/icons/full/obj16/connector/diffusion/ComIbmEventInput.gif";
	protected final static String NODE_GRAPHIC_32 = "platform:/plugin/DiffusionConnector/icons/full/obj30/connector/diffusion/ComIbmEventInput.gif";

	protected final static String PROPERTY_CONNECTORNAME = "connectorName";
	protected final static String PROPERTY_PROTOCOL = "protocol";
	protected final static String PROPERTY_HOSTNAME = "hostName";
	protected final static String PROPERTY_PORT = "port";
	protected final static String PROPERTY_USERNAME = "userName";
	protected final static String PROPERTY_PASSWORD = "password";
	protected final static String PROPERTY_TOPICSELECTOR = "topicSelector";
	protected final static String PROPERTY_TOPICTYPE = "topicType";


	/**
	 * <I>ENUM_DIFFUSIONSUBSCRIBER_PROTOCOL</I>
	 * <pre>
	 * ENUM_DIFFUSIONSUBSCRIBER_PROTOCOL.dpt = dpt
	 * ENUM_DIFFUSIONSUBSCRIBER_PROTOCOL.ws = ws
	 * </pre>
	 */
	public static class ENUM_DIFFUSIONSUBSCRIBER_PROTOCOL {
		private String value;

		public static final ENUM_DIFFUSIONSUBSCRIBER_PROTOCOL dpt = new ENUM_DIFFUSIONSUBSCRIBER_PROTOCOL("dpt");
		public static final ENUM_DIFFUSIONSUBSCRIBER_PROTOCOL ws = new ENUM_DIFFUSIONSUBSCRIBER_PROTOCOL("ws");

		protected ENUM_DIFFUSIONSUBSCRIBER_PROTOCOL(String value) {
			this.value = value;
		}
		public String toString() {
			return value;
		}

		protected static ENUM_DIFFUSIONSUBSCRIBER_PROTOCOL getEnumFromString(String enumValue) {
			ENUM_DIFFUSIONSUBSCRIBER_PROTOCOL enumConst = ENUM_DIFFUSIONSUBSCRIBER_PROTOCOL.dpt;
			if (ENUM_DIFFUSIONSUBSCRIBER_PROTOCOL.ws.value.equals(enumValue)) enumConst = ENUM_DIFFUSIONSUBSCRIBER_PROTOCOL.ws;
			return enumConst;
		}

		public static String[] values = new String[]{ "dpt", "ws" };

	}

	/**
	 * <I>ENUM_DIFFUSIONSUBSCRIBER_TOPICTYPE</I>
	 * <pre>
	 * ENUM_DIFFUSIONSUBSCRIBER_TOPICTYPE.SingleValue = Single Value
	 * ENUM_DIFFUSIONSUBSCRIBER_TOPICTYPE.JSON = JSON
	 * </pre>
	 */
	public static class ENUM_DIFFUSIONSUBSCRIBER_TOPICTYPE {
		private String value;

		public static final ENUM_DIFFUSIONSUBSCRIBER_TOPICTYPE SingleValue = new ENUM_DIFFUSIONSUBSCRIBER_TOPICTYPE("SingleValue");
		public static final ENUM_DIFFUSIONSUBSCRIBER_TOPICTYPE JSON = new ENUM_DIFFUSIONSUBSCRIBER_TOPICTYPE("JSON");

		protected ENUM_DIFFUSIONSUBSCRIBER_TOPICTYPE(String value) {
			this.value = value;
		}
		public String toString() {
			return value;
		}

		protected static ENUM_DIFFUSIONSUBSCRIBER_TOPICTYPE getEnumFromString(String enumValue) {
			ENUM_DIFFUSIONSUBSCRIBER_TOPICTYPE enumConst = ENUM_DIFFUSIONSUBSCRIBER_TOPICTYPE.SingleValue;
			if (ENUM_DIFFUSIONSUBSCRIBER_TOPICTYPE.JSON.value.equals(enumValue)) enumConst = ENUM_DIFFUSIONSUBSCRIBER_TOPICTYPE.JSON;
			return enumConst;
		}

		public static String[] values = new String[]{ "SingleValue", "JSON" };

	}
	protected NodeProperty[] getNodeProperties() {
		return new NodeProperty[] {
			new NodeProperty(DiffusionSubscriberNodeUDN.PROPERTY_CONNECTORNAME,		NodeProperty.Usage.OPTIONAL,	false,	NodeProperty.Type.STRING, "DiffusionConnector","","",	"connector/diffusion/ComIbmEventInput",	"DiffusionConnector"),
			new NodeProperty(DiffusionSubscriberNodeUDN.PROPERTY_PROTOCOL,		NodeProperty.Usage.MANDATORY,	true,	NodeProperty.Type.ENUMERATION, "dpt", ENUM_DIFFUSIONSUBSCRIBER_PROTOCOL.class,"","",	"connector/diffusion/ComIbmEventInput",	"DiffusionConnector"),
			new NodeProperty(DiffusionSubscriberNodeUDN.PROPERTY_HOSTNAME,		NodeProperty.Usage.MANDATORY,	true,	NodeProperty.Type.STRING, "localhost","","",	"connector/diffusion/ComIbmEventInput",	"DiffusionConnector"),
			new NodeProperty(DiffusionSubscriberNodeUDN.PROPERTY_PORT,		NodeProperty.Usage.MANDATORY,	true,	NodeProperty.Type.STRING, "8080","","",	"connector/diffusion/ComIbmEventInput",	"DiffusionConnector"),
			new NodeProperty(DiffusionSubscriberNodeUDN.PROPERTY_USERNAME,		NodeProperty.Usage.MANDATORY,	true,	NodeProperty.Type.STRING, null,"","",	"connector/diffusion/ComIbmEventInput",	"DiffusionConnector"),
			new NodeProperty(DiffusionSubscriberNodeUDN.PROPERTY_PASSWORD,		NodeProperty.Usage.MANDATORY,	true,	NodeProperty.Type.STRING, null,"","",	"connector/diffusion/ComIbmEventInput",	"DiffusionConnector"),
			new NodeProperty(DiffusionSubscriberNodeUDN.PROPERTY_TOPICSELECTOR,		NodeProperty.Usage.MANDATORY,	true,	NodeProperty.Type.STRING, null,"","",	"connector/diffusion/ComIbmEventInput",	"DiffusionConnector"),
			new NodeProperty(DiffusionSubscriberNodeUDN.PROPERTY_TOPICTYPE,		NodeProperty.Usage.OPTIONAL,	false,	NodeProperty.Type.ENUMERATION, "SingleValue", ENUM_DIFFUSIONSUBSCRIBER_TOPICTYPE.class,"","",	"connector/diffusion/ComIbmEventInput",	"DiffusionConnector")
		};
	}

	public DiffusionSubscriberNodeUDN() {
	}

	@Override
	public InputTerminal[] getInputTerminals() {
		return null;
	}

	public final OutputTerminal OUTPUT_TERMINAL_OUT = new OutputTerminal(this,"OutTerminal.out");
	public final OutputTerminal OUTPUT_TERMINAL_FAILURE = new OutputTerminal(this,"OutTerminal.failure");
	@Override
	public OutputTerminal[] getOutputTerminals() {
		return new OutputTerminal[] {
			OUTPUT_TERMINAL_OUT,
			OUTPUT_TERMINAL_FAILURE
		};
	}

	@Override
	public String getTypeName() {
		return NODE_TYPE_NAME;
	}

	protected String getGraphic16() {
		return NODE_GRAPHIC_16;
	}

	protected String getGraphic32() {
		return NODE_GRAPHIC_32;
	}

	/**
	 * Set the <I>DiffusionSubscriberNodeUDN</I> "<I>connectorName</I>" property
	 * 
	 * @param value String ; the value to set the property "<I>connectorName</I>"
	 */
	public DiffusionSubscriberNodeUDN setConnectorName(String value) {
		setProperty(DiffusionSubscriberNodeUDN.PROPERTY_CONNECTORNAME, value);
		return this;
	}

	/**
	 * Get the <I>DiffusionSubscriberNodeUDN</I> "<I>connectorName</I>" property
	 * 
	 * @return String; the value of the property "<I>connectorName</I>"
	 */
	public String getConnectorName() {
		return (String)getPropertyValue(DiffusionSubscriberNodeUDN.PROPERTY_CONNECTORNAME);
	}

	/**
	 * Set the <I>DiffusionSubscriberNodeUDN</I> "<I>Protocol</I>" property
	 * 
	 * @param value ENUM_DIFFUSIONSUBSCRIBER_PROTOCOL ; the value to set the property "<I>Protocol</I>"
	 */
	public DiffusionSubscriberNodeUDN setProtocol(ENUM_DIFFUSIONSUBSCRIBER_PROTOCOL value) {
		setProperty(DiffusionSubscriberNodeUDN.PROPERTY_PROTOCOL, value.toString());
		return this;
	}

	/**
	 * Get the <I>DiffusionSubscriberNodeUDN</I> "<I>Protocol</I>" property
	 * 
	 * @return ENUM_DIFFUSIONSUBSCRIBER_PROTOCOL; the value of the property "<I>Protocol</I>"
	 */
	public ENUM_DIFFUSIONSUBSCRIBER_PROTOCOL getProtocol() {
		ENUM_DIFFUSIONSUBSCRIBER_PROTOCOL value = ENUM_DIFFUSIONSUBSCRIBER_PROTOCOL.getEnumFromString((String)getPropertyValue(DiffusionSubscriberNodeUDN.PROPERTY_PROTOCOL));
		return value;
	}

	/**
	 * Set the <I>DiffusionSubscriberNodeUDN</I> "<I>Host Name</I>" property
	 * 
	 * @param value String ; the value to set the property "<I>Host Name</I>"
	 */
	public DiffusionSubscriberNodeUDN setHostName(String value) {
		setProperty(DiffusionSubscriberNodeUDN.PROPERTY_HOSTNAME, value);
		return this;
	}

	/**
	 * Get the <I>DiffusionSubscriberNodeUDN</I> "<I>Host Name</I>" property
	 * 
	 * @return String; the value of the property "<I>Host Name</I>"
	 */
	public String getHostName() {
		return (String)getPropertyValue(DiffusionSubscriberNodeUDN.PROPERTY_HOSTNAME);
	}

	/**
	 * Set the <I>DiffusionSubscriberNodeUDN</I> "<I>Port</I>" property
	 * 
	 * @param value String ; the value to set the property "<I>Port</I>"
	 */
	public DiffusionSubscriberNodeUDN setPort(String value) {
		setProperty(DiffusionSubscriberNodeUDN.PROPERTY_PORT, value);
		return this;
	}

	/**
	 * Get the <I>DiffusionSubscriberNodeUDN</I> "<I>Port</I>" property
	 * 
	 * @return String; the value of the property "<I>Port</I>"
	 */
	public String getPort() {
		return (String)getPropertyValue(DiffusionSubscriberNodeUDN.PROPERTY_PORT);
	}

	/**
	 * Set the <I>DiffusionSubscriberNodeUDN</I> "<I>User Name</I>" property
	 * 
	 * @param value String ; the value to set the property "<I>User Name</I>"
	 */
	public DiffusionSubscriberNodeUDN setUserName(String value) {
		setProperty(DiffusionSubscriberNodeUDN.PROPERTY_USERNAME, value);
		return this;
	}

	/**
	 * Get the <I>DiffusionSubscriberNodeUDN</I> "<I>User Name</I>" property
	 * 
	 * @return String; the value of the property "<I>User Name</I>"
	 */
	public String getUserName() {
		return (String)getPropertyValue(DiffusionSubscriberNodeUDN.PROPERTY_USERNAME);
	}

	/**
	 * Set the <I>DiffusionSubscriberNodeUDN</I> "<I>Password</I>" property
	 * 
	 * @param value String ; the value to set the property "<I>Password</I>"
	 */
	public DiffusionSubscriberNodeUDN setPassword(String value) {
		setProperty(DiffusionSubscriberNodeUDN.PROPERTY_PASSWORD, value);
		return this;
	}

	/**
	 * Get the <I>DiffusionSubscriberNodeUDN</I> "<I>Password</I>" property
	 * 
	 * @return String; the value of the property "<I>Password</I>"
	 */
	public String getPassword() {
		return (String)getPropertyValue(DiffusionSubscriberNodeUDN.PROPERTY_PASSWORD);
	}

	/**
	 * Set the <I>DiffusionSubscriberNodeUDN</I> "<I>Topic Selector</I>" property
	 * 
	 * @param value String ; the value to set the property "<I>Topic Selector</I>"
	 */
	public DiffusionSubscriberNodeUDN setTopicSelector(String value) {
		setProperty(DiffusionSubscriberNodeUDN.PROPERTY_TOPICSELECTOR, value);
		return this;
	}

	/**
	 * Get the <I>DiffusionSubscriberNodeUDN</I> "<I>Topic Selector</I>" property
	 * 
	 * @return String; the value of the property "<I>Topic Selector</I>"
	 */
	public String getTopicSelector() {
		return (String)getPropertyValue(DiffusionSubscriberNodeUDN.PROPERTY_TOPICSELECTOR);
	}

	/**
	 * Set the <I>DiffusionSubscriberNodeUDN</I> "<I>Topic Type</I>" property
	 * 
	 * @param value ENUM_DIFFUSIONSUBSCRIBER_TOPICTYPE ; the value to set the property "<I>Topic Type</I>"
	 */
	public DiffusionSubscriberNodeUDN setTopicType(ENUM_DIFFUSIONSUBSCRIBER_TOPICTYPE value) {
		setProperty(DiffusionSubscriberNodeUDN.PROPERTY_TOPICTYPE, value.toString());
		return this;
	}

	/**
	 * Get the <I>DiffusionSubscriberNodeUDN</I> "<I>Topic Type</I>" property
	 * 
	 * @return ENUM_DIFFUSIONSUBSCRIBER_TOPICTYPE; the value of the property "<I>Topic Type</I>"
	 */
	public ENUM_DIFFUSIONSUBSCRIBER_TOPICTYPE getTopicType() {
		ENUM_DIFFUSIONSUBSCRIBER_TOPICTYPE value = ENUM_DIFFUSIONSUBSCRIBER_TOPICTYPE.getEnumFromString((String)getPropertyValue(DiffusionSubscriberNodeUDN.PROPERTY_TOPICTYPE));
		return value;
	}

	public String getNodeName() {
		String retVal = super.getNodeName();
		if ((retVal==null) || retVal.equals(""))
			retVal = "Diffusion Subscriber";
		return retVal;
	};
}
