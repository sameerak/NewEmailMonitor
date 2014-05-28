package org.wso2.cep.email.esb;

import org.apache.axis2.AxisFault;
import org.apache.log4j.Logger;
import org.wso2.carbon.mediator.bam.config.stub.BAMMediatorConfigAdminStub;
import org.wso2.carbon.mediator.bam.config.stub.axis2.types.SaveBamServerConfig;
import org.wso2.carbon.mediator.bam.config.xsd.BamServerConfig;
import org.wso2.carbon.utils.CarbonUtils;
import org.wso2.cep.email.esb.util.SecurityConstants;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.rmi.RemoteException;


public class BAMMediatorAdminClient {

    private static final String BAM_MEDIATOR_ADMIN_SERVICE = "BAMMediatorConfigAdmin";
    private static Logger logger = Logger.getLogger(BAMMediatorAdminClient.class);
    private BAMMediatorConfigAdminStub stub;


    public BAMMediatorAdminClient(String ip, String port) {
        String endPoint = "https://" + ip + ":" + port + "/services/" + BAM_MEDIATOR_ADMIN_SERVICE;


        // Set client trust store
        System.setProperty(SecurityConstants.TRUSTSTORE, SecurityConstants.CLIENT_TRUST_STORE_PATH);
        System.setProperty(SecurityConstants.TRUSTSTORE_PASSWORD, SecurityConstants.KEY_STORE_PASSWORD);
        System.setProperty(SecurityConstants.TRUSTSTORE_TYPE, SecurityConstants.KEY_STORE_TYPE);


        try {
            stub = new BAMMediatorConfigAdminStub(endPoint);
        } catch (AxisFault axisFault) {
            logger.error(axisFault.getMessage());
        }



    }

    public void addMailProxy(String userName, String password){

        CarbonUtils.setBasicAccessSecurityHeaders(userName, password, stub._getServiceClient());

        String content = "";

        /*
        Need to read a config file bundled in the jar file
        to get the configuration needed to create the BAM mediator
        Source - http://www.coderanch.com/t/329156/java/java/Read-File-jar-file
         */
        InputStream is = null;
        BufferedReader br = null;
        String line;

        is = ProxyAdminClient.class.getResourceAsStream("/config/bamMediator.xml");
        br = new BufferedReader(new InputStreamReader(is));
        try {
            while (null != (line = br.readLine())) {
                content = content + line;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        //TODO add content variable instead of String added to the stub call and check the functionality before proceeding to next stage

        try {
                      stub.saveResourceString("<serverProfile xmlns=\"http://ws.apache.org/ns/synapse\"><connection loadbalancer=\"false\" secure=\"true\" urlSet=\"\" ip=\"10.100.5.89\" authPort=\"7612\" receiverPort=\"\"></connection><credential userName=\"admin\" password=\"kuv2MubUUveMyv6GeHrXr9il59ajJIqUI4eoYHcgGKf/BBFOWn96NTjJQI+wYbWjKW6r79S7L7ZzgYeWx7DlGbff5X3pBN2Gh9yV0BHP1E93QtFqR7uTWi141Tr7V7ZwScwNqJbiNoV+vyLbsqKJE7T3nP8Ih9Y6omygbcLcHzg=\"></credential><streams><stream name=\"dfsdf\" version=\"1.0.1\" nickName=\"fdsf\" description=\"fsd\"><payload></payload><properties></properties></stream></streams></serverProfile>\n","bamServerProfiles/sachinij");
        } catch (RemoteException e) {
           logger.error(e.getMessage());
        }


    }


    public static void main(String[] args) throws AxisFault {

        new BAMMediatorAdminClient("10.100.5.89", "9443").addMailProxy("admin", "admin");
    }

}


