package org.wso2.cep.email.monitor.internal.config;



import org.apache.axis2.context.ConfigurationContext;
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
    private EventFormatterService eventFormatterService ;
    private EmailMonitorValueHolder emailMonitorValueHolder;
    private XMLReader xmlReader;

    public EventFormatterDeployer() throws EmailMonitorServiceException {

        emailMonitorValueHolder = EmailMonitorValueHolder.getInstance();
        eventFormatterService = emailMonitorValueHolder.getEventFormatterService() ;
        xmlReader = new XMLReader();
    }




    public void createGmailOutStreamEventFormatter(String ESBServerIP, String ESBServerPort, String ESBServerUsername, String ESBServerPassword, AxisConfiguration axisConfiguration) throws EmailMonitorServiceException {
        String content = xmlReader.readXML(EmailMonitorConstants.GMAIL_OUT_STREAM_CONFIGURATION_PATH);
        content = content.replace(EmailMonitorConstants.ESB_SERVER_USER_NAME,ESBServerUsername);
        content = content.replace(EmailMonitorConstants.ESB_SERVER_PASSWORD,ESBServerPassword);
        content = content.replace(EmailMonitorConstants.ESB_SERVER_IP,ESBServerIP);

        int offset = Integer.parseInt(ESBServerPort) - 9443;
        content = content.replace(EmailMonitorConstants.ESB_SERVER_ENDPOINT_PORT,String.valueOf(8243+offset));

        try {
            eventFormatterService.deployDefaultEventSender(content,axisConfiguration);
        } catch (EventFormatterConfigurationException e) {
            logger.error(e.getMessage());
            throw new EmailMonitorServiceException("Error when adding event formatter", e);
        }


    }


    public void createEmailSenderOutputStreamFormatter(String mailBody, String mailAddress, String mailSubject, AxisConfiguration axisConfiguration) throws EmailMonitorServiceException {
        String content = xmlReader.readXML(EmailMonitorConstants.MAIL_SENDER_OUT_STREAM_CONFIGURATION_PATH);
        content = content.replace(EmailMonitorConstants.MAIL_BODY, mailBody);
        content = content.replace(EmailMonitorConstants.GMAIL_USERNAME,mailAddress);
        content = content.replace(EmailMonitorConstants.MAIL_SUBJECT, mailSubject);

        try {
            eventFormatterService.deployDefaultEventSender(content,axisConfiguration);
        } catch (EventFormatterConfigurationException e) {
            logger.error(e.getMessage());
            throw new EmailMonitorServiceException("Error when adding event formatter", e);
        }


    }
}
