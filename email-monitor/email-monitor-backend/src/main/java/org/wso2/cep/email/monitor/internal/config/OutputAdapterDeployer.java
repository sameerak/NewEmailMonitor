package org.wso2.cep.email.monitor.internal.config;



import org.apache.axis2.engine.AxisConfiguration;
import org.apache.log4j.Logger;
import org.wso2.carbon.event.output.adaptor.core.config.OutputEventAdaptorConfiguration;
import org.wso2.carbon.event.output.adaptor.manager.core.OutputEventAdaptorManagerService;
import org.wso2.carbon.event.output.adaptor.manager.core.exception.OutputEventAdaptorManagerConfigurationException;

import org.wso2.cep.email.monitor.exception.EmailMonitorServiceException;
import org.wso2.cep.email.monitor.internal.ds.EmailMonitorValueHolder;



public class OutputAdapterDeployer {


    private static Logger logger = Logger.getLogger(OutputAdapterDeployer.class);
    private EmailMonitorValueHolder emailMonitorValueHolder;
    private OutputEventAdaptorManagerService outputEventAdaptorManagerService;



    public OutputAdapterDeployer() throws EmailMonitorServiceException {

        emailMonitorValueHolder = EmailMonitorValueHolder.getInstance();
        outputEventAdaptorManagerService = emailMonitorValueHolder.getOutputEventAdaptorManagerService() ;
    }

    public void createSoapOutputAdapter(AxisConfiguration axisConfiguration) throws EmailMonitorServiceException {
        OutputEventAdaptorConfiguration outputEventAdaptorConfiguration = new OutputEventAdaptorConfiguration();
        outputEventAdaptorConfiguration.setName("SOAP_output_Adaptor");
        outputEventAdaptorConfiguration.setType("soap");
        try {
            outputEventAdaptorManagerService.deployOutputEventAdaptorConfiguration(outputEventAdaptorConfiguration, axisConfiguration);
        } catch (OutputEventAdaptorManagerConfigurationException e) {
            logger.error(e.getMessage());
        }
    }

    public void createEmailOutputAdapter(AxisConfiguration axisConfiguration) throws EmailMonitorServiceException {
        OutputEventAdaptorConfiguration outputEventAdaptorConfiguration = new OutputEventAdaptorConfiguration();
        outputEventAdaptorConfiguration.setName("EMAIL_output_Adaptor");
        outputEventAdaptorConfiguration.setType("email");
        try {
            outputEventAdaptorManagerService.deployOutputEventAdaptorConfiguration(outputEventAdaptorConfiguration, axisConfiguration);
        } catch (OutputEventAdaptorManagerConfigurationException e) {
            logger.error(e.getMessage());
        }
    }
}

