package org.wso2.cep.email.monitor.internal;


import org.apache.axis2.context.ConfigurationContext;
import org.apache.log4j.Logger;
import org.wso2.cep.email.monitor.EmailMonitorServiceInterface;
import org.wso2.cep.email.monitor.exception.EmailMonitorServiceException;
import org.wso2.cep.email.monitor.internal.config.ExecutionPlanDeployer;
import org.wso2.cep.email.monitor.internal.config.StreamDeployer;
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
    public boolean createExecutionPlan(String cookie, String backendServerURL, ConfigurationContext configCtx, String executionPlanXmlConfiguration) throws EmailMonitorServiceException {

        try {
            ExecutionPlanDeployer executionPlanDeployer = new ExecutionPlanDeployer(cookie, backendServerURL, configCtx);
            executionPlanDeployer.createExecutionPlan(executionPlanXmlConfiguration);
            return true;
        } catch (EmailMonitorServiceException e) {
            logger.error(e.getMessage());
            throw new EmailMonitorServiceException(e);

        }
    }

    @Override
    public boolean createMailInputStream(String cookie, String backendServerURL, ConfigurationContext configCtx) throws EmailMonitorServiceException {

        try {
            StreamDeployer streamDeployer = new StreamDeployer(cookie, backendServerURL, configCtx);
            streamDeployer.createMailInputStream();
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
}
