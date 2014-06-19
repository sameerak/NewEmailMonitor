package org.wso2.cep.email.monitor.admin;

import org.apache.axis2.context.ConfigurationContext;
import org.apache.axis2.engine.AxisConfiguration;
import org.apache.log4j.Logger;
import org.wso2.carbon.core.AbstractAdmin;
import org.wso2.carbon.user.core.UserStoreException;
import org.wso2.cep.email.monitor.EmailMonitorServiceInterface;
import org.wso2.cep.email.monitor.admin.exception.EmailMonitorAdminException;
import org.wso2.cep.email.monitor.admin.internal.EmailMonitorAdminValueHolder;
import org.wso2.cep.email.monitor.exception.EmailMonitorServiceException;
import org.wso2.cep.email.monitor.query.compiler.QueryManagerServiceInterface;
import org.wso2.cep.email.monitor.query.compiler.exeception.EmailMonitorCompilerException;


public class EmailMonitorAdminService extends AbstractAdmin {

    private static final Logger log = Logger.getLogger(EmailMonitorAdminService.class);


    public boolean addBAMServerProfile(String ip, String port, String userName, String password, String CEPServerUserName, String CEPServerPassword, String CEPServerIP, String CEPServerPort) throws EmailMonitorAdminException {
        EmailMonitorServiceInterface emailMonitorServiceInterface = EmailMonitorAdminValueHolder.getInstance().getEmailMonitorService();
        try {
            return emailMonitorServiceInterface.addBAMServerProfile(ip, port, userName, password, CEPServerUserName, CEPServerPassword, CEPServerIP, CEPServerPort);
        } catch (EmailMonitorServiceException e) {
            log.error(e.getMessage());
            throw new EmailMonitorAdminException(e);
        }
    }


    public boolean addMailProxy(String ip, String port, String userName, String password) throws EmailMonitorAdminException {
        EmailMonitorServiceInterface emailMonitorServiceInterface = EmailMonitorAdminValueHolder.getInstance().getEmailMonitorService();
        try {
            return emailMonitorServiceInterface.addMailProxy(ip, port, userName, password);
        } catch (EmailMonitorServiceException e) {
            log.error(e.getMessage());
            throw new EmailMonitorAdminException(e);
        }
    }


    public boolean addScheduledTask(String ip, String port, String userName, String password, String mailUserName, String mailPassword) throws EmailMonitorAdminException {
        EmailMonitorServiceInterface emailMonitorServiceInterface = EmailMonitorAdminValueHolder.getInstance().getEmailMonitorService();
        try {
            return emailMonitorServiceInterface.addScheduledTask(ip, port, userName, password, mailUserName, mailPassword);
        } catch (EmailMonitorServiceException e) {
            log.error(e.getMessage());
            throw new EmailMonitorAdminException(e);
        }
    }

    public String[] getSiddhiQuery(String query) throws EmailMonitorAdminException {
        QueryManagerServiceInterface queryManagerServiceInterface = EmailMonitorAdminValueHolder.getInstance().getQueryManagerServiceInterface();
        try {
          return   queryManagerServiceInterface.getSiddhiQuery(query);
        } catch (EmailMonitorCompilerException e) {
            log.error(e.getMessage());
            throw new EmailMonitorAdminException(e);
        }


    }




    public String createExecutionPlan(String query) throws EmailMonitorAdminException {
        EmailMonitorServiceInterface emailMonitorServiceInterface = EmailMonitorAdminValueHolder.getInstance().getEmailMonitorService();
        try {
            return emailMonitorServiceInterface.createExecutionPlan(query, getAxisConfig());
        } catch (EmailMonitorServiceException e) {
            log.error(e.getMessage());
            throw new EmailMonitorAdminException(e);
        }
    }

    public boolean createMailInputStream() throws EmailMonitorAdminException {
        EmailMonitorServiceInterface emailMonitorServiceInterface = EmailMonitorAdminValueHolder.getInstance().getEmailMonitorService();

        int tenantID = 0;
        try {
            tenantID = getUserRealm().getRealmConfiguration().getTenantId();
        } catch (UserStoreException e) {
            log.error(e.getMessage());
            throw new EmailMonitorAdminException(e);
        }
        try {
            return emailMonitorServiceInterface.createMailInputStream(tenantID);
        } catch (EmailMonitorServiceException e) {
            log.error(e.getMessage());
            throw new EmailMonitorAdminException(e);
        }
    }

    public boolean createMailOutputStream() throws EmailMonitorAdminException {
        EmailMonitorServiceInterface emailMonitorServiceInterface = EmailMonitorAdminValueHolder.getInstance().getEmailMonitorService();

        int tenantID = 0;
        try {
            tenantID = getUserRealm().getRealmConfiguration().getTenantId();
        } catch (UserStoreException e) {
            log.error(e.getMessage());
            throw new EmailMonitorAdminException(e);
        }
        try {
            return emailMonitorServiceInterface.createMailOutputStream(tenantID);
        } catch (EmailMonitorServiceException e) {
            log.error(e.getMessage());
            throw new EmailMonitorAdminException(e);
        }
    }

    public boolean createThreadDetailsStream() throws EmailMonitorAdminException {
        EmailMonitorServiceInterface emailMonitorServiceInterface = EmailMonitorAdminValueHolder.getInstance().getEmailMonitorService();

        int tenantID = 0;
        try {
            tenantID = getUserRealm().getRealmConfiguration().getTenantId();
        } catch (UserStoreException e) {
            log.error(e.getMessage());
            throw new EmailMonitorAdminException(e);
        }
        try {
            return emailMonitorServiceInterface.createThreadDetailsStream(tenantID);
        } catch (EmailMonitorServiceException e) {
            log.error(e.getMessage());
            throw new EmailMonitorAdminException(e);
        }
    }

    public boolean createLabelDetailsStream() throws EmailMonitorAdminException {
        EmailMonitorServiceInterface emailMonitorServiceInterface = EmailMonitorAdminValueHolder.getInstance().getEmailMonitorService();

        int tenantID = 0;
        try {
            tenantID = getUserRealm().getRealmConfiguration().getTenantId();
        } catch (UserStoreException e) {
            log.error(e.getMessage());
            throw new EmailMonitorAdminException(e);
        }
        try {
            return emailMonitorServiceInterface.createLabelDetailsStream(tenantID);
        } catch (EmailMonitorServiceException e) {
            log.error(e.getMessage());
            throw new EmailMonitorAdminException(e);
        }
    }

    public boolean createEmailSenderOutputStream() throws EmailMonitorAdminException {
        EmailMonitorServiceInterface emailMonitorServiceInterface = EmailMonitorAdminValueHolder.getInstance().getEmailMonitorService();

        int tenantID = 0;
        try {
            tenantID = getUserRealm().getRealmConfiguration().getTenantId();
        } catch (UserStoreException e) {
            log.error(e.getMessage());
            throw new EmailMonitorAdminException(e);
        }
        try {
            return emailMonitorServiceInterface.createEmailSenderOutputStream(tenantID);
        } catch (EmailMonitorServiceException e) {
            log.error(e.getMessage());
            throw new EmailMonitorAdminException(e);
        }
    }

    public boolean createFilteredEmailDetailsStream() throws EmailMonitorAdminException {
        EmailMonitorServiceInterface emailMonitorServiceInterface = EmailMonitorAdminValueHolder.getInstance().getEmailMonitorService();

        int tenantID = 0;
        try {
            tenantID = getUserRealm().getRealmConfiguration().getTenantId();
        } catch (UserStoreException e) {
            log.error(e.getMessage());
            throw new EmailMonitorAdminException(e);
        }
        try {
            return emailMonitorServiceInterface.createFilteredEmailDetailsStream(tenantID);
        } catch (EmailMonitorServiceException e) {
            log.error(e.getMessage());
            throw new EmailMonitorAdminException(e);
        }
    }


    public boolean createSoapOutputAdapter() throws EmailMonitorAdminException {
        EmailMonitorServiceInterface emailMonitorServiceInterface = EmailMonitorAdminValueHolder.getInstance().getEmailMonitorService();
        try {
            return emailMonitorServiceInterface.createSoapOutputAdapter(getAxisConfig());
        } catch (EmailMonitorServiceException e) {
            log.error(e.getMessage());
            throw new EmailMonitorAdminException(e);
        }
    }

    public boolean createEmailOutputAdapter() throws EmailMonitorAdminException {
        EmailMonitorServiceInterface emailMonitorServiceInterface = EmailMonitorAdminValueHolder.getInstance().getEmailMonitorService();
        try {
            return emailMonitorServiceInterface.createEmailOutputAdapter(getAxisConfig());
        } catch (EmailMonitorServiceException e) {
            log.error(e.getMessage());
            throw new EmailMonitorAdminException(e);
        }
    }

    public boolean createEventFormatter(String ESBServerIP, String ESBServerPort, String ESBServerUsername, String ESBServerPassword, String eventFormatterConfigurationXML) throws EmailMonitorAdminException {
        EmailMonitorServiceInterface emailMonitorServiceInterface = EmailMonitorAdminValueHolder.getInstance().getEmailMonitorService();
        try {
            return emailMonitorServiceInterface.createGmailOutStreamEventFormatter(ESBServerIP,ESBServerPort,ESBServerUsername,ESBServerPassword, getAxisConfig());
        } catch (EmailMonitorServiceException e) {
            log.error(e.getMessage());
            throw new EmailMonitorAdminException(e);
        }
    }

    public boolean createEmailSenderOutputStreamFormatter( String mailAddress) throws EmailMonitorAdminException {
        EmailMonitorServiceInterface emailMonitorServiceInterface = EmailMonitorAdminValueHolder.getInstance().getEmailMonitorService();
        try {
            return emailMonitorServiceInterface.createEmailSenderOutputStreamFormatter(mailAddress,getAxisConfig());
        } catch (EmailMonitorServiceException e) {
            log.error(e.getMessage());
            throw new EmailMonitorAdminException(e);
        }
    }


        public boolean addESBConfigurations(String ip, String port, String userName, String password, String CEPServerUserName, String CEPServerPassword, String mailUserNAme, String mailPassword, String CEPServerIP, String CEPServerPort) throws EmailMonitorAdminException {
        EmailMonitorServiceInterface emailMonitorServiceInterface = EmailMonitorAdminValueHolder.getInstance().getEmailMonitorService();
        try {
            return emailMonitorServiceInterface.addESBConfigurations(ip, port, userName, password, CEPServerUserName, CEPServerPassword, mailUserNAme, mailPassword, CEPServerIP, CEPServerPort);
        } catch (EmailMonitorServiceException e) {
            log.error(e.getMessage());
            throw new EmailMonitorAdminException(e);
        }
    }


    public boolean addCEPConfigurations(String ESBServerIP, String ESBServerPort, String ESBServerUsername, String ESBServerPassword, String mailAddress) throws EmailMonitorAdminException {

        int tenantID = 0;
        try {
            tenantID = getUserRealm().getRealmConfiguration().getTenantId();
        } catch (UserStoreException e) {
            log.error(e.getMessage());
            throw new EmailMonitorAdminException(e);
        }


        EmailMonitorServiceInterface emailMonitorServiceInterface = EmailMonitorAdminValueHolder.getInstance().getEmailMonitorService();
        try {
            return emailMonitorServiceInterface.addCEPConfigurations(ESBServerIP, ESBServerPort, ESBServerUsername, ESBServerPassword, mailAddress, tenantID, getAxisConfig());
        } catch (EmailMonitorServiceException e) {
            log.error(e.getMessage());
            throw new EmailMonitorAdminException(e);
        }
    }


    public boolean saveResourceString(String resourceString, String resourcePath){
        EmailMonitorServiceInterface emailMonitorServiceInterface = EmailMonitorAdminValueHolder.getInstance().getEmailMonitorService();
        return emailMonitorServiceInterface.saveResourceString(resourceString, resourcePath);
    }

    public boolean resourceAlreadyExists(String path){
        EmailMonitorServiceInterface emailMonitorServiceInterface = EmailMonitorAdminValueHolder.getInstance().getEmailMonitorService();
        return emailMonitorServiceInterface.resourceAlreadyExists(path);
    }

    public boolean removeResource(String path) {
        EmailMonitorServiceInterface emailMonitorServiceInterface = EmailMonitorAdminValueHolder.getInstance().getEmailMonitorService();
        return emailMonitorServiceInterface.removeResource(path);
    }

    public String getResourceString(String path){
        EmailMonitorServiceInterface emailMonitorServiceInterface = EmailMonitorAdminValueHolder.getInstance().getEmailMonitorService();
        return emailMonitorServiceInterface.getResourceString(path);
    }

    public boolean addCollection(String collectionPath){
        EmailMonitorServiceInterface emailMonitorServiceInterface = EmailMonitorAdminValueHolder.getInstance().getEmailMonitorService();
        return emailMonitorServiceInterface.addCollection(collectionPath);
    }

    public String[] getEmailMonitorResources(String emailMonitorCollectionLocation) {
        EmailMonitorServiceInterface emailMonitorServiceInterface = EmailMonitorAdminValueHolder.getInstance().getEmailMonitorService();
        return emailMonitorServiceInterface.getEmailMonitorResources(emailMonitorCollectionLocation);
    }

    }
