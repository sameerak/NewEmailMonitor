package org.wso2.cep.email.monitor.internal.config;

import org.apache.axis2.engine.AxisConfiguration;
import org.apache.log4j.Logger;
import org.wso2.cep.email.monitor.exception.EmailMonitorServiceException;
import org.wso2.cep.email.monitor.internal.config.esb.config.TaskDeployer;
import org.wso2.cep.email.monitor.internal.config.esb.config.util.SecurityConstants;

public class CEPConfigurationHelper {
    private static Logger logger = Logger.getLogger(CEPConfigurationHelper.class);

    StreamDeployer streamDeployer;
    OutputAdapterDeployer outputAdapterDeployer;
    EventFormatterDeployer eventFormatterDeployer;

    public CEPConfigurationHelper() throws EmailMonitorServiceException {


        streamDeployer = new StreamDeployer();
        outputAdapterDeployer = new OutputAdapterDeployer();
        eventFormatterDeployer = new EventFormatterDeployer();
    }



    public void addCEPConfigurations(String ESBServerIP, String ESBServerPort, String ESBServerUsername, String ESBServerPassword, String mailAddress,int tenantID, AxisConfiguration axisConfiguration) throws EmailMonitorServiceException {

        logger.info("Stared adding CEP configurations");

        streamDeployer.createMailInputStream(tenantID);
        streamDeployer.createFilteredEmailDetailsStream(tenantID);
        streamDeployer.createThreadDetailsStream(tenantID);
        streamDeployer.createLabelDetailsStream(tenantID);
        streamDeployer.createMailOutputStream(tenantID);
        streamDeployer.createEmailSenderOutputStream(tenantID);

        outputAdapterDeployer.createEmailOutputAdapter(axisConfiguration);
        outputAdapterDeployer.createSoapOutputAdapter(axisConfiguration);

        eventFormatterDeployer.createGmailOutStreamEventFormatter(ESBServerIP, ESBServerPort, ESBServerUsername, ESBServerPassword, axisConfiguration);
        eventFormatterDeployer.createEmailSenderOutputStreamFormatter(mailAddress ,axisConfiguration);

        logger.info("Successfully completed adding CEP configurations");



    }
}
