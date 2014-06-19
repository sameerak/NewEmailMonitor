package org.wso2.cep.email.monitor.internal;


import org.apache.axis2.engine.AxisConfiguration;
import org.apache.log4j.Logger;
import org.wso2.cep.email.monitor.EmailMonitorServiceInterface;
import org.wso2.cep.email.monitor.exception.EmailMonitorServiceException;
import org.wso2.cep.email.monitor.internal.config.*;
import org.wso2.cep.email.monitor.internal.config.esb.config.BAMMediatorDeployer;
import org.wso2.cep.email.monitor.internal.config.esb.config.ESBConfigurationHelper;
import org.wso2.cep.email.monitor.internal.config.esb.config.ProxyDeployer;
import org.wso2.cep.email.monitor.internal.config.esb.config.TaskDeployer;



public class EmailMonitorService implements EmailMonitorServiceInterface {

    private static Logger logger = Logger.getLogger(EmailMonitorService.class);


    @Override
    public boolean addBAMServerProfile(String ip, String port, String userName, String password, String CEPServerUserName, String CEPServerPassword, String CEPServerIP, String CEPServerPort) throws EmailMonitorServiceException {


        try {
            BAMMediatorDeployer bamMediatorDeployer = new BAMMediatorDeployer(ip, port);
            bamMediatorDeployer.addBAMServerProfile(userName, password, CEPServerUserName, CEPServerPassword, CEPServerIP, CEPServerPort);
            return true;
        } catch (EmailMonitorServiceException e) {
            logger.error(e.getMessage());
            throw new EmailMonitorServiceException(e);

        }
    }

    @Override
    public boolean addMailProxy(String ip, String port, String userName, String password) throws EmailMonitorServiceException {

        try {
            ProxyDeployer proxyDeployer = new ProxyDeployer(ip, port);
            proxyDeployer.addMailProxy(userName, password);
            return true;
        } catch (EmailMonitorServiceException e) {
            logger.error(e.getMessage());
            throw new EmailMonitorServiceException(e);

        }
    }

    @Override
    public boolean addScheduledTask(String ip, String port, String userName, String password, String mailUserName, String mailPassword) throws EmailMonitorServiceException {

        try {
            TaskDeployer taskDeployer = new TaskDeployer(ip, port);
            taskDeployer.addScheduledTask(userName, password, mailUserName, mailPassword);
            return true;
        } catch (EmailMonitorServiceException e) {
            logger.error(e.getMessage());
            throw new EmailMonitorServiceException(e);

        }
    }

    @Override
    public String createExecutionPlan( String query,AxisConfiguration axisConfiguration) throws EmailMonitorServiceException {

        try {
            ExecutionPlanDeployer executionPlanDeployer = new ExecutionPlanDeployer();
            return executionPlanDeployer.createExecutionPlan(query,axisConfiguration);

        } catch (EmailMonitorServiceException e) {
            logger.error(e.getMessage());
            throw new EmailMonitorServiceException(e);

        }
    }

    @Override
    public boolean createMailInputStream(int tenantID) throws EmailMonitorServiceException {

        try {
            StreamDeployer streamDeployer = new StreamDeployer();
            streamDeployer.createMailInputStream(tenantID);
            return true;
        } catch (EmailMonitorServiceException e) {
            logger.error(e.getMessage());
            throw new EmailMonitorServiceException(e);

        }
    }

    @Override
    public boolean addESBConfigurations(String ip, String port, String userName, String password, String CEPServerUserName, String CEPServerPassword, String mailUserNAme, String mailPassword, String CEPServerIP, String CEPServerPort) throws EmailMonitorServiceException {
        try {
            ESBConfigurationHelper esbConfigurationHelper = new ESBConfigurationHelper(ip, port);
            esbConfigurationHelper.addConfigurations(userName, password, CEPServerUserName, CEPServerPassword, mailUserNAme, mailPassword, CEPServerIP, CEPServerPort);
            return true;
        } catch (EmailMonitorServiceException e) {
            logger.error(e.getMessage());
            throw new EmailMonitorServiceException(e);

        }
    }

    @Override
    public boolean addCEPConfigurations(String ESBServerIP, String ESBServerPort, String ESBServerUsername, String ESBServerPassword, String mailAddress, int tenantID, AxisConfiguration axisConfiguration) throws EmailMonitorServiceException {
        try {
            CEPConfigurationHelper cepConfigurationHelper = new CEPConfigurationHelper();
            cepConfigurationHelper.addCEPConfigurations(ESBServerIP, ESBServerPort, ESBServerUsername, ESBServerPassword, mailAddress, tenantID, axisConfiguration);
            return true;
        } catch (EmailMonitorServiceException e) {
            logger.error(e.getMessage());
            throw new EmailMonitorServiceException(e);

        }
    }

    @Override
    public boolean createMailOutputStream(int tenantID) throws EmailMonitorServiceException {
        try {
            StreamDeployer streamDeployer = new StreamDeployer();
            streamDeployer.createMailOutputStream(tenantID);
            return true;
        } catch (EmailMonitorServiceException e) {
            logger.error(e.getMessage());
            throw new EmailMonitorServiceException(e);

        }
    }

    @Override
    public boolean createThreadDetailsStream(int tenantID) throws EmailMonitorServiceException {
        try {
            StreamDeployer streamDeployer = new StreamDeployer();
            streamDeployer.createThreadDetailsStream(tenantID);
            return true;
        } catch (EmailMonitorServiceException e) {
            logger.error(e.getMessage());
            throw new EmailMonitorServiceException(e);

        }
    }

    @Override
    public boolean createLabelDetailsStream(int tenantID) throws EmailMonitorServiceException {
        try {
            StreamDeployer streamDeployer = new StreamDeployer();
            streamDeployer.createLabelDetailsStream(tenantID);
            return true;
        } catch (EmailMonitorServiceException e) {
            logger.error(e.getMessage());
            throw new EmailMonitorServiceException(e);

        }
    }

    @Override
    public boolean createEmailSenderOutputStream(int tenantID) throws EmailMonitorServiceException {
        try {
            StreamDeployer streamDeployer = new StreamDeployer();
            streamDeployer.createEmailSenderOutputStream(tenantID);
            return true;
        } catch (EmailMonitorServiceException e) {
            logger.error(e.getMessage());
            throw new EmailMonitorServiceException(e);

        }
    }

    @Override
    public boolean createFilteredEmailDetailsStream(int tenantID) throws EmailMonitorServiceException {
        try {
            StreamDeployer streamDeployer = new StreamDeployer();
            streamDeployer.createFilteredEmailDetailsStream(tenantID);
            return true;
        } catch (EmailMonitorServiceException e) {
            logger.error(e.getMessage());
            throw new EmailMonitorServiceException(e);

        }
    }

    @Override
    public boolean createSoapOutputAdapter(AxisConfiguration axisConfiguration) throws EmailMonitorServiceException {
        try {
            OutputAdapterDeployer outputAdapterDeployer = new OutputAdapterDeployer();
            outputAdapterDeployer.createSoapOutputAdapter(axisConfiguration);
            return true;
        } catch (EmailMonitorServiceException e) {
            logger.error(e.getMessage());
            throw new EmailMonitorServiceException(e);

        }
    }

    @Override
    public boolean createEmailOutputAdapter(AxisConfiguration axisConfiguration) throws EmailMonitorServiceException {
        try {
            OutputAdapterDeployer outputAdapterDeployer = new OutputAdapterDeployer();
            outputAdapterDeployer.createEmailOutputAdapter(axisConfiguration);
            return true;
        } catch (EmailMonitorServiceException e) {
            logger.error(e.getMessage());
            throw new EmailMonitorServiceException(e);

        }
    }

    @Override
    public boolean createGmailOutStreamEventFormatter(String ESBServerIP, String ESBServerPort, String ESBServerUsername, String ESBServerPassword, AxisConfiguration axisConfiguration) throws EmailMonitorServiceException {
        try {
            EventFormatterDeployer eventFormatterDeployer = new EventFormatterDeployer();
            eventFormatterDeployer.createGmailOutStreamEventFormatter(ESBServerIP,ESBServerPort,ESBServerUsername,ESBServerPassword, axisConfiguration);
            return true;
        } catch (EmailMonitorServiceException e) {
            logger.error(e.getMessage());
            throw new EmailMonitorServiceException(e);

        }
    }

    @Override
    public boolean createEmailSenderOutputStreamFormatter(String mailAddress, AxisConfiguration axisConfiguration) throws EmailMonitorServiceException {
        try {
            EventFormatterDeployer eventFormatterDeployer = new EventFormatterDeployer();
            eventFormatterDeployer.createEmailSenderOutputStreamFormatter(mailAddress,axisConfiguration);
            return true;
        } catch (EmailMonitorServiceException e) {
            logger.error(e.getMessage());
            throw new EmailMonitorServiceException(e);

        }
    }

    @Override
    public boolean saveResourceString(String resourceString, String resourcePath) {
        RegistryManager registryManager = new RegistryManager();
        registryManager.saveResourceString(resourceString, resourcePath);
        return true;
    }

    @Override
    public boolean resourceAlreadyExists(String path) {
        RegistryManager registryManager = new RegistryManager();
        registryManager.resourceAlreadyExists(path);
        return true;
    }

    @Override
    public boolean removeResource(String path) {
        RegistryManager registryManager = new RegistryManager();
        registryManager.removeResource(path);
        return true;
    }

    @Override
    public String getResourceString(String path) {
        RegistryManager registryManager = new RegistryManager();
        return registryManager.getResourceString(path);
    }

    @Override
    public boolean addCollection(String collectionPath) {
        RegistryManager registryManager = new RegistryManager();
        registryManager.addCollection(collectionPath);
        return true;
    }

    @Override
    public String[] getEmailMonitorResources(String emailMonitorCollectionLocation) {
        RegistryManager registryManager = new RegistryManager();
        return registryManager.getEmailMonitorResources(emailMonitorCollectionLocation);
    }


}
