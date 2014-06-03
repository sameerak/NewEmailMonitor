package org.wso2.cep.email.monitor.admin.internal;


import org.wso2.cep.email.monitor.EmailMonitorServiceInterface;

public class EmailMonitorAdminValueHolder {

    private static EmailMonitorAdminValueHolder emailMonitorAdminValueHolder;
    private EmailMonitorServiceInterface emailMonitorServiceInterface;


    private EmailMonitorAdminValueHolder() {

    }

    public static EmailMonitorAdminValueHolder getInstance() {
        if (emailMonitorAdminValueHolder == null) {
            emailMonitorAdminValueHolder = new EmailMonitorAdminValueHolder();
        }
        return emailMonitorAdminValueHolder;
    }


    public void registerEmailMonitorService(EmailMonitorServiceInterface emailMonitorServiceInterface) {
        this.emailMonitorServiceInterface = emailMonitorServiceInterface;
    }

    public void unregisterEmailMonitorService() {
        this.emailMonitorServiceInterface = null;
    }

    public EmailMonitorServiceInterface getEmailMonitorService() {
        return emailMonitorServiceInterface;
    }

}
