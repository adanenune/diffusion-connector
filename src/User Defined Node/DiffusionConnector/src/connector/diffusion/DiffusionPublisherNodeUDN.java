package connector.diffusion;

import com.ibm.broker.config.appdev.InputTerminal;
import com.ibm.broker.config.appdev.Node;
import com.ibm.broker.config.appdev.NodeProperty;
import com.ibm.broker.config.appdev.OutputTerminal;

/*** 
 * <p>  <I>DiffusionPublisherNodeUDN</I> instance</p>
 * <p></p>
 */
public class DiffusionPublisherNodeUDN extends Node {

	private static final long serialVersionUID = 1L;

	// Node constants
	protected final static String NODE_TYPE_NAME = "connector/diffusion/OutputNode";
	protected final static String NODE_GRAPHIC_16 = "platform:/plugin/DiffusionConnector/icons/full/obj16/connector/diffusion/ComIbmOutput.gif";
	protected final static String NODE_GRAPHIC_32 = "platform:/plugin/DiffusionConnector/icons/full/obj30/connector/diffusion/ComIbmOutput.gif";

	protected final static String PROPERTY_CONNECTORNAME = "connectorName";
	protected final static String PROPERTY_PROTOCOL = "protocol";
	protected final static String PROPERTY_HOSTNAME = "hostName";
	protected final static String PROPERTY_PORT = "port";
	protected final static String PROPERTY_USERNAME = "userName";
	protected final static String PROPERTY_PASSWORD = "password";
	protected final static String PROPERTY_TOPIC = "topic";


	/**
	 * <I>ENUM_DIFFUSIONPUBLISHER_PROTOCOL</I>
	 * <pre>
	 * ENUM_DIFFUSIONPUBLISHER_PROTOCOL.dpt = dpt
	 * ENUM_DIFFUSIONPUBLISHER_PROTOCOL.ws = ws
	 * </pre>
	 */
	public static class ENUM_DIFFUSIONPUBLISHER_PROTOCOL {
		private String value;

		public static final ENUM_DIFFUSIONPUBLISHER_PROTOCOL dpt = new ENUM_DIFFUSIONPUBLISHER_PROTOCOL("dpt");
		public static final ENUM_DIFFUSIONPUBLISHER_PROTOCOL ws = new ENUM_DIFFUSIONPUBLISHER_PROTOCOL("ws");

		protected ENUM_DIFFUSIONPUBLISHER_PROTOCOL(String value) {
			this.value = value;
		}
		public String toString() {
			return value;
		}

		protected static ENUM_DIFFUSIONPUBLISHER_PROTOCOL getEnumFromString(String enumValue) {
			ENUM_DIFFUSIONPUBLISHER_PROTOCOL enumConst = ENUM_DIFFUSIONPUBLISHER_PROTOCOL.dpt;
			if (ENUM_DIFFUSIONPUBLISHER_PROTOCOL.ws.value.equals(enumValue)) enumConst = ENUM_DIFFUSIONPUBLISHER_PROTOCOL.ws;
			return enumConst;
		}

		public static String[] values = new String[]{ "dpt", "ws" };

	}
	protected NodeProperty[] getNodeProperties() {
		return new NodeProperty[] {
			new NodeProperty(DiffusionPublisherNodeUDN.PROPERTY_CONNECTORNAME,		NodeProperty.Usage.OPTIONAL,	false,	NodeProperty.Type.STRING, "DiffusionConnector","","",	"connector/diffusion/ComIbmOutput",	"DiffusionConnector"),
			new NodeProperty(DiffusionPublisherNodeUDN.PROPERTY_PROTOCOL,		NodeProperty.Usage.MANDATORY,	true,	NodeProperty.Type.ENUMERATION, "dpt", ENUM_DIFFUSIONPUBLISHER_PROTOCOL.class,"","",	"connector/diffusion/ComIbmOutput",	"DiffusionConnector"),
			new NodeProperty(DiffusionPublisherNodeUDN.PROPERTY_HOSTNAME,		NodeProperty.Usage.MANDATORY,	true,	NodeProperty.Type.STRING, "localhost","","",	"connector/diffusion/ComIbmOutput",	"DiffusionConnector"),
			new NodeProperty(DiffusionPublisherNodeUDN.PROPERTY_PORT,		NodeProperty.Usage.MANDATORY,	true,	NodeProperty.Type.STRING, "8080","","",	"connector/diffusion/ComIbmOutput",	"DiffusionConnector"),
			new NodeProperty(DiffusionPublisherNodeUDN.PROPERTY_USERNAME,		NodeProperty.Usage.MANDATORY,	true,	NodeProperty.Type.STRING, null,"","",	"connector/diffusion/ComIbmOutput",	"DiffusionConnector"),
			new NodeProperty(DiffusionPublisherNodeUDN.PROPERTY_PASSWORD,		NodeProperty.Usage.MANDATORY,	true,	NodeProperty.Type.STRING, null,"","",	"connector/diffusion/ComIbmOutput",	"DiffusionConnector"),
			new NodeProperty(DiffusionPublisherNodeUDN.PROPERTY_TOPIC,		NodeProperty.Usage.MANDATORY,	true,	NodeProperty.Type.STRING, null,"","",	"connector/diffusion/ComIbmOutput",	"DiffusionConnector")
		};
	}

	public DiffusionPublisherNodeUDN() {
	}

	public final InputTerminal INPUT_TERMINAL_IN = new InputTerminal(this,"InTerminal.in");
	@Override
	public InputTerminal[] getInputTerminals() {
		return new InputTerminal[] {
			INPUT_TERMINAL_IN
	};
	}

	public final OutputTerminal OUTPUT_TERMINAL_FAILURE = new OutputTerminal(this,"OutTerminal.failure");
	@Override
	public OutputTerminal[] getOutputTerminals() {
		return new OutputTerminal[] {
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
	 * Set the <I>DiffusionPublisherNodeUDN</I> "<I>connectorName</I>" property
	 * 
	 * @param value String ; the value to set the property "<I>connectorName</I>"
	 */
	public DiffusionPublisherNodeUDN setConnectorName(String value) {
		setProperty(DiffusionPublisherNodeUDN.PROPERTY_CONNECTORNAME, value);
		return this;
	}

	/**
	 * Get the <I>DiffusionPublisherNodeUDN</I> "<I>connectorName</I>" property
	 * 
	 * @return String; the value of the property "<I>connectorName</I>"
	 */
	public String getConnectorName() {
		return (String)getPropertyValue(DiffusionPublisherNodeUDN.PROPERTY_CONNECTORNAME);
	}

	/**
	 * Set the <I>DiffusionPublisherNodeUDN</I> "<I>protocol</I>" property
	 * 
	 * @param value ENUM_DIFFUSIONPUBLISHER_PROTOCOL ; the value to set the property "<I>protocol</I>"
	 */
	public DiffusionPublisherNodeUDN setProtocol(ENUM_DIFFUSIONPUBLISHER_PROTOCOL value) {
		setProperty(DiffusionPublisherNodeUDN.PROPERTY_PROTOCOL, value.toString());
		return this;
	}

	/**
	 * Get the <I>DiffusionPublisherNodeUDN</I> "<I>protocol</I>" property
	 * 
	 * @return ENUM_DIFFUSIONPUBLISHER_PROTOCOL; the value of the property "<I>protocol</I>"
	 */
	public ENUM_DIFFUSIONPUBLISHER_PROTOCOL getProtocol() {
		ENUM_DIFFUSIONPUBLISHER_PROTOCOL value = ENUM_DIFFUSIONPUBLISHER_PROTOCOL.getEnumFromString((String)getPropertyValue(DiffusionPublisherNodeUDN.PROPERTY_PROTOCOL));
		return value;
	}

	/**
	 * Set the <I>DiffusionPublisherNodeUDN</I> "<I>hostName</I>" property
	 * 
	 * @param value String ; the value to set the property "<I>hostName</I>"
	 */
	public DiffusionPublisherNodeUDN setHostName(String value) {
		setProperty(DiffusionPublisherNodeUDN.PROPERTY_HOSTNAME, value);
		return this;
	}

	/**
	 * Get the <I>DiffusionPublisherNodeUDN</I> "<I>hostName</I>" property
	 * 
	 * @return String; the value of the property "<I>hostName</I>"
	 */
	public String getHostName() {
		return (String)getPropertyValue(DiffusionPublisherNodeUDN.PROPERTY_HOSTNAME);
	}

	/**
	 * Set the <I>DiffusionPublisherNodeUDN</I> "<I>port</I>" property
	 * 
	 * @param value String ; the value to set the property "<I>port</I>"
	 */
	public DiffusionPublisherNodeUDN setPort(String value) {
		setProperty(DiffusionPublisherNodeUDN.PROPERTY_PORT, value);
		return this;
	}

	/**
	 * Get the <I>DiffusionPublisherNodeUDN</I> "<I>port</I>" property
	 * 
	 * @return String; the value of the property "<I>port</I>"
	 */
	public String getPort() {
		return (String)getPropertyValue(DiffusionPublisherNodeUDN.PROPERTY_PORT);
	}

	/**
	 * Set the <I>DiffusionPublisherNodeUDN</I> "<I>userName</I>" property
	 * 
	 * @param value String ; the value to set the property "<I>userName</I>"
	 */
	public DiffusionPublisherNodeUDN setUserName(String value) {
		setProperty(DiffusionPublisherNodeUDN.PROPERTY_USERNAME, value);
		return this;
	}

	/**
	 * Get the <I>DiffusionPublisherNodeUDN</I> "<I>userName</I>" property
	 * 
	 * @return String; the value of the property "<I>userName</I>"
	 */
	public String getUserName() {
		return (String)getPropertyValue(DiffusionPublisherNodeUDN.PROPERTY_USERNAME);
	}

	/**
	 * Set the <I>DiffusionPublisherNodeUDN</I> "<I>password</I>" property
	 * 
	 * @param value String ; the value to set the property "<I>password</I>"
	 */
	public DiffusionPublisherNodeUDN setPassword(String value) {
		setProperty(DiffusionPublisherNodeUDN.PROPERTY_PASSWORD, value);
		return this;
	}

	/**
	 * Get the <I>DiffusionPublisherNodeUDN</I> "<I>password</I>" property
	 * 
	 * @return String; the value of the property "<I>password</I>"
	 */
	public String getPassword() {
		return (String)getPropertyValue(DiffusionPublisherNodeUDN.PROPERTY_PASSWORD);
	}

	/**
	 * Set the <I>DiffusionPublisherNodeUDN</I> "<I>topic</I>" property
	 * 
	 * @param value String ; the value to set the property "<I>topic</I>"
	 */
	public DiffusionPublisherNodeUDN setTopic(String value) {
		setProperty(DiffusionPublisherNodeUDN.PROPERTY_TOPIC, value);
		return this;
	}

	/**
	 * Get the <I>DiffusionPublisherNodeUDN</I> "<I>topic</I>" property
	 * 
	 * @return String; the value of the property "<I>topic</I>"
	 */
	public String getTopic() {
		return (String)getPropertyValue(DiffusionPublisherNodeUDN.PROPERTY_TOPIC);
	}

	public String getNodeName() {
		String retVal = super.getNodeName();
		if ((retVal==null) || retVal.equals(""))
			retVal = "Diffusion Publisher";
		return retVal;
	};
}
