package org.wso2.cep.email.monitor.internal.ds;


import org.wso2.carbon.event.processor.core.EventProcessorService;
import org.wso2.carbon.event.stream.manager.core.EventStreamService;
import org.wso2.carbon.utils.ConfigurationContextService;
import org.wso2.cep.email.monitor.EmailMonitorServiceInterface;
import org.wso2.cep.email.monitor.internal.EmailMonitorService;

public class EmailMonitorValueHolder {

    private EmailMonitorServiceInterface emailMonitorServiceInterface;
    private static EmailMonitorValueHolder emailMonitorValueHolder;
    private ConfigurationContextService configurationContextService;
    private EventStreamService eventStreamService;
    private EventProcessorService eventProcessorService;

    private EmailMonitorValueHolder() {
        emailMonitorServiceInterface = new EmailMonitorService();
    }


    public EmailMonitorService getEmailMonitorService() {
        return (EmailMonitorService) emailMonitorServiceInterface;
    }

    public void setEmailMonitorServiceInterface(EmailMonitorServiceInterface emailMonitorServiceInterface) {
        this.emailMonitorServiceInterface = emailMonitorServiceInterface;
    }

    public static EmailMonitorValueHolder getInstance() {
        if (emailMonitorValueHolder == null) {
            emailMonitorValueHolder = new EmailMonitorValueHolder();
        }
        return emailMonitorValueHolder;
    }

    public void registerConfigurationContextService(ConfigurationContextService configurationContextService) {
        this.configurationContextService = configurationContextService;
    }
    public EventStreamService getEventStreamService() {
        return  eventStreamService;
    }

    public void setEventStreamService(EventStreamService eventStreamService){
       this.eventStreamService = eventStreamService;
    }
    public void unsetEventStreamService(){
        this.eventStreamService = null;
    }
    public EventProcessorService getEventProcessorService() {
        return  eventProcessorService;
    }

    public void setEventProcessorService(EventProcessorService eventProcessorService){
        this.eventProcessorService = eventProcessorService;
    }
    public void unsetEventProcessorService(){
        this.eventProcessorService = null;
    }
}
