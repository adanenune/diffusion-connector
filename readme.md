# Diffusion Connector

## Version

Diffusion Connector 10.0.0.0 Updated
Push Technology Ltd.
April 2016

## Introduction

This project provides an IBM IIB connector for Diffusion and Reappt from Push Technology. The connector allows for both
the publication of data via Diffusion/Reappt as well as subscription of data from it.  The supports both Single Value 
and JSON Topics. The JSON topics are a new feature of Diffusion 5.7 and so at least version 5.7.0 is required.

For further information on Diffusion and Reappt please go to http://www.pushtechnology.com and http://reapt.io

# Build

The connector is comprised of two elements: the connector implementation in Java in Eclipse and the user defined node defined within the 
IBM IIB tool kit built upon Eclipse. Prior to building the connector implementation, import the Diffusion Java Client API JAR file into 
the project src directory.

Instructions on building the connector are availbale at : https://www-01.ibm.com/support/knowledgecenter/SSMKHH_10.0.0/com.ibm.etools.mft.doc/cb23108_.htm

## Installation

To install the Diffusion IIB Connector:

1. To install the connector to the IIB pallette:

Copy the user-defined node plug-in JAR file into the dropins folder in your IBM Integration Toolkit installation location: install_dir/tools/dropins.
For example, the default installation directory on Windows is C:\Program Files\IBM\IIB\version\. The user defined node jar file will be named
DiffusionConnector-10.0.0.x.jar or similar depending upon the version number chosen when the node was defined.

To ensure that the new files are available to the IBM Integration Toolkit, restart the Toolkit.

2. To install the connector classes:

Stop the integration node on which you want to install your connector.
Save the JAR file that contains the connector, DiffusionConnector.jar, in workpath/connectors and unpack its' contents into a directory named 
DiffusionConnector. Delete the DiffusionConnector.jar file. The default workpath directory on Windows is C:\ProgramData\IBM\MQSI. Copy the Diffusion 
Java Client API from your Diffusion installation into the DiffusionConnector directory.

For further installation details as well as instructions on creating and configuring a user defined node, visit:

## Further Information

https://www-01.ibm.com/support/knowledgecenter/SSMKHH_10.0.0/com.ibm.etools.mft.doc/cb23108_.htm

 