package org.wso2.cep.email.monitor.internal.ds;


import org.apache.log4j.Logger;
import org.osgi.framework.ServiceReference;
import org.osgi.service.component.ComponentContext;
import org.wso2.carbon.event.processor.core.EventProcessorService;
import org.wso2.carbon.event.stream.manager.core.EventStreamService;
import org.wso2.carbon.utils.Axis2ConfigurationContextObserver;
import org.wso2.carbon.utils.ConfigurationContextService;
import org.wso2.cep.email.monitor.EmailMonitorServiceInterface;
import org.wso2.cep.email.monitor.internal.EmailMonitorService;

/**
 * @scr.component name="emailmonitor.component" immediate="true"
 * @scr.reference name="eventstream.service"
 * interface="org.wso2.carbon.event.stream.manager.core.EventStreamService" cardinality="1..1"
 * policy="dynamic" bind="setEventStreamService" unbind="unsetEventStreamService"
 * @scr.reference name="eventprocessor.service"
 * interface="org.wso2.carbon.event.processor.core.EventProcessorService" cardinality="1..1"
 * policy="dynamic" bind="setEventProcessorService" unbind="unsetEventProcessorService"
 */
public class EmailMonitorServiceDS {
    private static final Logger log = Logger.getLogger(EmailMonitorServiceDS.class);

    /**
     * initialize the cep service here.
     *
     * @param context
     */
    protected void activate(ComponentContext context) {

        try {
            EmailMonitorServiceInterface emailMonitorServiceInterface = EmailMonitorValueHolder.getInstance().getEmailMonitorService();
            context.getBundleContext().registerService(EmailMonitorServiceInterface.class.getName(),
                    emailMonitorServiceInterface, null);
            log.info("Successfully deployed the Email Monitor Service");
        } catch (Throwable e) {
            log.error("Can not create the Email Monitor Service ", e);
        }
    }

    protected void deactivate(ComponentContext context) {
        // context.getBundleContext().ungetService();
    }

    protected void setEventStreamService(EventStreamService eventStreamService){
        EmailMonitorValueHolder.getInstance().setEventStreamService(eventStreamService);
    }

    protected void unsetEventStreamService(EventStreamService eventStreamService){
       EmailMonitorValueHolder.getInstance().unsetEventStreamService();
    }
    protected void setEventProcessorService(EventProcessorService eventProcessorService){
        EmailMonitorValueHolder.getInstance().setEventProcessorService(eventProcessorService);
    }

    protected void unsetEventProcessorService(EventProcessorService eventStreamService){
        EmailMonitorValueHolder.getInstance().unsetEventProcessorService();
    }

}
