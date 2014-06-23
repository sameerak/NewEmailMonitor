package org.wso2.cep.email.monitor.internal.config;


import org.apache.axis2.engine.AxisConfiguration;
import org.apache.log4j.Logger;
import org.wso2.carbon.event.formatter.core.EventFormatterService;
import org.wso2.carbon.event.formatter.core.exception.EventFormatterConfigurationException;
import org.wso2.cep.email.monitor.exception.EmailMonitorServiceException;
import org.wso2.cep.email.monitor.internal.config.esb.config.XMLReader;
import org.wso2.cep.email.monitor.internal.ds.EmailMonitorValueHolder;
import org.wso2.cep.email.monitor.internal.util.EmailMonitorConstants;


public class EventFormatterDeployer {
    private static Logger logger = Logger.getLogger(EventFormatterDeployer.class);
    private EventFormatterService eventFormatterService;
    private XMLReader xmlReader;

    public EventFormatterDeployer() throws EmailMonitorServiceException {

        EmailMonitorValueHolder emailMonitorValueHolder = EmailMonitorValueHolder.getInstance();
        eventFormatterService = emailMonitorValueHolder.getEventFormatterService();
        xmlReader = new XMLReader();
    }


    public void createGmailOutStreamEventFormatter(String ESBServerIP, String ESBServerPort, String ESBServerUsername, String ESBServerPassword, AxisConfiguration axisConfiguration) throws EmailMonitorServiceException {
        try {
            eventFormatterService.getActiveEventFormatterConfigurationContent(EmailMonitorConstants.GMAIL_OUT_STREAM_FORMATTER_NAME,axisConfiguration);

        } catch (EventFormatterConfigurationException e) {
            String content = xmlReader.readXML(EmailMonitorConstants.GMAIL_OUT_STREAM_CONFIGURATION_PATH);
            content = content.replace(EmailMonitorConstants.ESB_SERVER_USER_NAME, ESBServerUsername);
            content = content.replace(EmailMonitorConstants.ESB_SERVER_PASSWORD, ESBServerPassword);
            content = content.replace(EmailMonitorConstants.ESB_SERVER_IP, ESBServerIP);

            int offset = Integer.parseInt(ESBServerPort) - 9443;
            content = content.replace(EmailMonitorConstants.ESB_SERVER_ENDPOINT_PORT, String.valueOf(9763 + offset));

            try {
                eventFormatterService.deployEventFormatterConfiguration(content, axisConfiguration);
            } catch (EventFormatterConfigurationException ex) {
                logger.error(ex.getMessage());
                throw new EmailMonitorServiceException("Error when adding event formatter", ex);
            }
        }





    }


//    public void createEmailSenderOutputStreamFormatter(String mailAddress, AxisConfiguration axisConfiguration) throws EmailMonitorServiceException {
//
//        try {
//            eventFormatterService.getActiveEventFormatterConfigurationContent(EmailMonitorConstants.MAIL_SENDER_OUT_STREAM_FORMATTER_NAME,axisConfiguration);
//        } catch (EventFormatterConfigurationException e) {
//            String content = xmlReader.readXML(EmailMonitorConstants.MAIL_SENDER_OUT_STREAM_CONFIGURATION_PATH);
//            content = content.replace(EmailMonitorConstants.MAIL_BODY, "Details:");
//            content = content.replace(EmailMonitorConstants.GMAIL_USERNAME, mailAddress);
//            content = content.replace(EmailMonitorConstants.MAIL_SUBJECT, "[CEP]Important email alert ");
//
//            try {
//                eventFormatterService.deployEventFormatterConfiguration(content, axisConfiguration);
//            } catch (EventFormatterConfigurationException ex) {
//                logger.error(ex.getMessage());
//                throw new EmailMonitorServiceException("Error when adding event formatter", ex);
//            }
//
//        }
//
//
//    }


    public void createEmailSenderOutputStreamFormatter(String ESBServerIP, String ESBServerPort, String ESBServerUsername, String ESBServerPassword, AxisConfiguration axisConfiguration) throws EmailMonitorServiceException {
        try {
            eventFormatterService.getActiveEventFormatterConfigurationContent(EmailMonitorConstants.MAIL_SENDER_OUT_STREAM_FORMATTER_NAME,axisConfiguration);

        } catch (EventFormatterConfigurationException e) {
            String content = xmlReader.readXML(EmailMonitorConstants.MAIL_SENDER_OUT_STREAM_CONFIGURATION_PATH);
            content = content.replace(EmailMonitorConstants.ESB_SERVER_USER_NAME, ESBServerUsername);
            content = content.replace(EmailMonitorConstants.ESB_SERVER_PASSWORD, ESBServerPassword);
            content = content.replace(EmailMonitorConstants.ESB_SERVER_IP, ESBServerIP);

            int offset = Integer.parseInt(ESBServerPort) - 9443;
            content = content.replace(EmailMonitorConstants.ESB_SERVER_ENDPOINT_PORT, String.valueOf(9763 + offset));

            try {
                eventFormatterService.deployEventFormatterConfiguration(content, axisConfiguration);
            } catch (EventFormatterConfigurationException ex) {
                logger.error(ex.getMessage());
                throw new EmailMonitorServiceException("Error when adding event formatter", ex);
            }
        }





    }
}
