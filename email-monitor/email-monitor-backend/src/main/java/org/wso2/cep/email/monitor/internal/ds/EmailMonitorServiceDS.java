package org.wso2.cep.email.monitor.internal.ds;


import org.apache.log4j.Logger;
import org.osgi.framework.ServiceReference;
import org.osgi.service.component.ComponentContext;
import org.wso2.carbon.utils.Axis2ConfigurationContextObserver;
import org.wso2.carbon.utils.ConfigurationContextService;
import org.wso2.cep.email.monitor.EmailMonitorServiceInterface;
import org.wso2.cep.email.monitor.internal.EmailMonitorService;

/**
 * @scr.component name="emailmonitor.component" immediate="true"
 * @scr.reference name="configurationcontext.service"
 * interface="org.wso2.carbon.utils.ConfigurationContextService" cardinality="1..1"
 * policy="dynamic" bind="setConfigurationContextService" unbind="unsetConfigurationContextService"
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

    protected void setConfigurationContextService(
            ConfigurationContextService configurationContextService) {
        EmailMonitorValueHolder.getInstance().registerConfigurationContextService(configurationContextService);
    }

    protected void unsetConfigurationContextService(
            ConfigurationContextService configurationContextService) {

    }
}
