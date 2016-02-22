/*
 * DiffusionConnectorFactory
 * 
 * The connector factory is responsible for instantianting instances of the input and output 
 * connectors when requested to do so by the framework. The Diffusion connector does not implement 
 * the Request/Reply operation.
 * 
 * Copyright (c) 2016, Push Technology Ltd., All rights reserved.
 * 
 * This program file is distributed under the MIT license. A copy of this license is included in the home directory
 * of the connector on OT4i.
 * 
 */

package connector.diffusion;

import com.ibm.connectors.AbstractConnectorFactory;
import com.ibm.connectors.ConnectorException;
import com.ibm.connectors.InputConnector;
import com.ibm.connectors.OutputConnector;
import com.ibm.connectors.RequestConnector;


public class DiffusionConnectorFactory extends AbstractConnectorFactory {
	
	public DiffusionConnectorFactory() {
		// TODO You can create shared resources that are used by all connectors
		// for this provider. If the resources depend on properties that are provided
		// on initialisation, create those resources in the onInitialise() method,
		// when the properties are available.
	}
	
	@Override
	protected void onInitialise() throws Exception {
		// TODO This method is called after the factory has been initialised with its properties,
		// the provider name, and connector services, which are all available via getters on 
		// this class. A standard Java logger is created under the provider name, 
		// and it is available by using getLogger().
	}
	
	@Override
	public InputConnector createInputConnector(String name) throws ConnectorException {
		// Instantiate a new DiffusionInputConnector
		return new DiffusionInputConnector(name);
	}

	@Override
	public OutputConnector createOutputConnector(String name) throws ConnectorException {
		// Instantiate a new DiffusionOutputConnector
		return new DiffusionOutputConnector(name);
	}

	@Override
	public RequestConnector createRequestConnector(String name) throws ConnectorException {
		// No request Connector is provided for Diffusion
		return null;
	}

	@Override
	public String getInfo() {
		// TODO Auto-generated method stub
		return null;
	}

}
