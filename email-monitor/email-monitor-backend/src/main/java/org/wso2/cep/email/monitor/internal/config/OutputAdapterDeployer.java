package org.wso2.cep.email.monitor.internal.config;



import org.apache.axis2.engine.AxisConfiguration;
import org.apache.log4j.Logger;
import org.wso2.carbon.event.output.adaptor.core.OutputEventAdaptorService;
import org.wso2.carbon.event.output.adaptor.core.config.OutputEventAdaptorConfiguration;
import org.wso2.carbon.event.output.adaptor.manager.core.OutputEventAdaptorManagerService;
import org.wso2.carbon.event.output.adaptor.manager.core.exception.OutputEventAdaptorManagerConfigurationException;

import org.wso2.cep.email.monitor.exception.EmailMonitorServiceException;
import org.wso2.cep.email.monitor.internal.ds.EmailMonitorValueHolder;
import org.wso2.cep.email.monitor.internal.util.EmailMonitorConstants;


public class OutputAdapterDeployer {


    private static Logger logger = Logger.getLogger(OutputAdapterDeployer.class);
    private OutputEventAdaptorManagerService outputEventAdaptorManagerService;



    public OutputAdapterDeployer() throws EmailMonitorServiceException {

        EmailMonitorValueHolder emailMonitorValueHolder = EmailMonitorValueHolder.getInstance();
        outputEventAdaptorManagerService = emailMonitorValueHolder.getOutputEventAdaptorManagerService() ;
    }

    public void createSoapOutputAdapter(AxisConfiguration axisConfiguration) throws EmailMonitorServiceException {
        OutputEventAdaptorConfiguration outputEventAdaptorConfiguration = new OutputEventAdaptorConfiguration();
        outputEventAdaptorConfiguration.setName(EmailMonitorConstants.SOAP_OUTPUT_ADAPTER_NAME);
        outputEventAdaptorConfiguration.setType(EmailMonitorConstants.ADAPTER_TYPE_SOAP);
        try {
            outputEventAdaptorManagerService.deployOutputEventAdaptorConfiguration(outputEventAdaptorConfiguration, axisConfiguration);
        } catch (OutputEventAdaptorManagerConfigurationException e) {
            logger.error(e.getMessage());
            throw new EmailMonitorServiceException("Error when adding output adapter", e);
        }
    }

    public void createEmailOutputAdapter(AxisConfiguration axisConfiguration) throws EmailMonitorServiceException {
        OutputEventAdaptorConfiguration outputEventAdaptorConfiguration = new OutputEventAdaptorConfiguration();
        outputEventAdaptorConfiguration.setName(EmailMonitorConstants.EMAIL_OUTPUT_ADAPTER_NAME);
        outputEventAdaptorConfiguration.setType(EmailMonitorConstants.ADAPTER_TYPE_EMAIL);
        try {
            outputEventAdaptorManagerService.deployOutputEventAdaptorConfiguration(outputEventAdaptorConfiguration, axisConfiguration);
        } catch (OutputEventAdaptorManagerConfigurationException e) {
            logger.error(e.getMessage());
            throw new EmailMonitorServiceException("Error when adding output adapter", e);
        }
    }
}

