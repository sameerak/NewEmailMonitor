package org.wso2.cep.email.monitor.config.esb.config;


import org.apache.log4j.Logger;
import org.wso2.cep.email.monitor.config.esb.config.util.SecurityConstants;

public class ESBConfigurationHelper {
    private static Logger logger = Logger.getLogger(TaskDeployer.class);
    private ProxyDeployer proxyDeployer;
    private BAMMediatorDeployer bamMediatorDeployer;
    private TaskDeployer taskDeployer;

    public ESBConfigurationHelper(String ip, String port) {


        System.setProperty(SecurityConstants.TRUSTSTORE, SecurityConstants.CLIENT_TRUST_STORE_PATH);
        System.setProperty(SecurityConstants.TRUSTSTORE_PASSWORD, SecurityConstants.KEY_STORE_PASSWORD);
        System.setProperty(SecurityConstants.TRUSTSTORE_TYPE, SecurityConstants.KEY_STORE_TYPE);

        proxyDeployer = new ProxyDeployer(ip, port);
        bamMediatorDeployer = new BAMMediatorDeployer(ip, port);
        taskDeployer = new TaskDeployer(ip, port);


    }


    public void addConfigurations(String userName, String password, String CEPServerUserName, String CEPServerPassword, String mailUserNAme, String mailPassword,String CEPServerIP ,String CEPServerPort ) {
        logger.info("Stared adding ESB configurations");

        bamMediatorDeployer.addBAMServerProfile(userName, password, CEPServerUserName, CEPServerPassword , CEPServerIP ,CEPServerPort);
        proxyDeployer.addMailProxy(userName, password);
        taskDeployer.addScheduledTask(userName, password, mailUserNAme, mailPassword);


    }



}
