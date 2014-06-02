package org.wso2.cep.email.monitor.admin;
import org.apache.axis2.context.ConfigurationContext;
import org.apache.log4j.Logger;
import org.wso2.carbon.core.AbstractAdmin;
import org.wso2.cep.email.monitor.EmailMonitorServiceInterface;
import org.wso2.cep.email.monitor.admin.exception.EmailMonitorAdminException;
import org.wso2.cep.email.monitor.admin.internal.EmailMonitorAdminValueHolder;
import org.wso2.cep.email.monitor.exception.EmailMonitorServiceException;
import org.wso2.cep.email.monitor.internal.EmailMonitorService;



public class EmailMonitorAdminService extends AbstractAdmin {

private static final Logger log = Logger.getLogger(EmailMonitorAdminService.class);




    public boolean addBAMServerProfile(String ip, String port, String userName, String password, String CEPServerUserName, String CEPServerPassword, String CEPServerIP, String CEPServerPort) throws EmailMonitorAdminException {
        EmailMonitorServiceInterface emailMonitorServiceInterface = EmailMonitorAdminValueHolder.getInstance().getEmailMonitorService();
        try {
          return  emailMonitorServiceInterface.addBAMServerProfile(ip,port,userName,password,CEPServerUserName,CEPServerPassword,CEPServerIP,CEPServerPort);
        } catch (EmailMonitorServiceException e) {
           log.error(e.getMessage());
            throw new EmailMonitorAdminException(e);
        }
    }


    public boolean addMailProxy(String ip, String port, String userName, String password) throws EmailMonitorAdminException {
        EmailMonitorServiceInterface emailMonitorServiceInterface = EmailMonitorAdminValueHolder.getInstance().getEmailMonitorService();
        try {
            return  emailMonitorServiceInterface.addMailProxy( ip,  port,  userName, password);
        } catch (EmailMonitorServiceException e) {
            log.error(e.getMessage());
            throw new EmailMonitorAdminException(e);
        }
    }


    public boolean addScheduledTask(String ip, String port, String userName, String password, String mailUserName, String mailPassword) throws EmailMonitorAdminException {
        EmailMonitorServiceInterface emailMonitorServiceInterface = EmailMonitorAdminValueHolder.getInstance().getEmailMonitorService();
        try {
            return  emailMonitorServiceInterface.addScheduledTask( ip,  port,  userName,  password,  mailUserName, mailPassword);
        } catch (EmailMonitorServiceException e) {
            log.error(e.getMessage());
            throw new EmailMonitorAdminException(e);
        }
    }


    public boolean createExecutionPlan(String ip, String port, String executionPlanXmlConfiguration) throws EmailMonitorAdminException {
        EmailMonitorServiceInterface emailMonitorServiceInterface = EmailMonitorAdminValueHolder.getInstance().getEmailMonitorService();
        try {
            return  emailMonitorServiceInterface.createExecutionPlan(ip,  port, executionPlanXmlConfiguration);
        } catch (EmailMonitorServiceException e) {
            log.error(e.getMessage());
            throw new EmailMonitorAdminException(e);
        }
    }


    public boolean createExecutionPlan(String cookie, String backendServerURL, ConfigurationContext configCtx, String executionPlanXmlConfiguration) throws EmailMonitorAdminException {
        EmailMonitorServiceInterface emailMonitorServiceInterface = EmailMonitorAdminValueHolder.getInstance().getEmailMonitorService();
        try {
            return  emailMonitorServiceInterface.createExecutionPlan(cookie,backendServerURL,configCtx,  executionPlanXmlConfiguration);
        } catch (EmailMonitorServiceException e) {
            log.error(e.getMessage());
            throw new EmailMonitorAdminException(e);
        }
    }

    public boolean createMailInputStream(String cookie, String backendServerURL, ConfigurationContext configCtx) throws EmailMonitorAdminException {
        EmailMonitorServiceInterface emailMonitorServiceInterface = EmailMonitorAdminValueHolder.getInstance().getEmailMonitorService();
        try {
            return  emailMonitorServiceInterface.createMailInputStream(cookie,  backendServerURL,  configCtx);
        } catch (EmailMonitorServiceException e) {
            log.error(e.getMessage());
            throw new EmailMonitorAdminException(e);
        }
    }


    public boolean addESBConfigurations(String ip, String port, String userName, String password, String CEPServerUserName, String CEPServerPassword, String mailUserNAme, String mailPassword, String CEPServerIP, String CEPServerPort) throws EmailMonitorAdminException {
        EmailMonitorServiceInterface emailMonitorServiceInterface = EmailMonitorAdminValueHolder.getInstance().getEmailMonitorService();
        try {
            return  emailMonitorServiceInterface.addESBConfigurations( ip,  port,  userName, password,  CEPServerUserName,  CEPServerPassword,  mailUserNAme,  mailPassword,  CEPServerIP,  CEPServerPort);
        } catch (EmailMonitorServiceException e) {
            log.error(e.getMessage());
            throw new EmailMonitorAdminException(e);
        }
    }
















}
