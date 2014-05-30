package org.wso2.cep.email.monitor.config.esb.config;


import org.apache.log4j.Logger;
import org.wso2.cep.email.monitor.config.esb.config.util.SecurityConstants;

public class ESBConfigurationHelper {
    private static Logger logger = Logger.getLogger(TaskAdminClient.class);
    private ProxyAdminClient proxyAdminClient;
    private BAMMediatorAdminClient bamMediatorAdminClient;
    private TaskAdminClient taskAdminClient;

    public ESBConfigurationHelper(String ip, String port) {


        System.setProperty(SecurityConstants.TRUSTSTORE, SecurityConstants.CLIENT_TRUST_STORE_PATH);
        System.setProperty(SecurityConstants.TRUSTSTORE_PASSWORD, SecurityConstants.KEY_STORE_PASSWORD);
        System.setProperty(SecurityConstants.TRUSTSTORE_TYPE, SecurityConstants.KEY_STORE_TYPE);

        proxyAdminClient = new ProxyAdminClient(ip, port);
        bamMediatorAdminClient = new BAMMediatorAdminClient(ip, port);
        taskAdminClient = new TaskAdminClient(ip, port);


    }


    public void addConfigurations(String userName, String password, String CEPServerUserName, String CEPServerPassword, String mailUserNAme, String mailPassword) {
        logger.info("Stared adding ESB configurations");

        bamMediatorAdminClient.addBAMServerProfile(userName, password, CEPServerUserName, CEPServerPassword);
        proxyAdminClient.addMailProxy(userName, password);
        taskAdminClient.addScheduledTask(userName, password, mailUserNAme, mailPassword);


    }

    public static void main(String[] args) {

        new ESBConfigurationHelper("10.100.5.89", "9443").addConfigurations("admin", "admin", "admin", "admin","gmail'","gmail");
    }


}
