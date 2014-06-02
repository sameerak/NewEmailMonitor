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


    public void addConfigurations(String ESBUserName, String ESBPassword, String CEPServerUserName, String CEPServerPassword, String mailUserNAme, String mailPassword,String CEPServerIP ,String CEPServerPort ) {
        logger.info("Stared adding ESB configurations");

        bamMediatorDeployer.addBAMServerProfile(ESBUserName, ESBPassword, CEPServerUserName, CEPServerPassword , CEPServerIP ,CEPServerPort);
        proxyDeployer.addMailProxy(ESBUserName, ESBPassword);
       taskDeployer.addScheduledTask(ESBUserName, ESBPassword, mailUserNAme, mailPassword);


    }

    public static void main(String args[]){
                     new ESBConfigurationHelper("10.100.5.89", "9443").addConfigurations("admin","admin","admin","admin","synapse.demo.1@gmail.com","mailpassword1","10.100.5.89", "7712");
    }

}
