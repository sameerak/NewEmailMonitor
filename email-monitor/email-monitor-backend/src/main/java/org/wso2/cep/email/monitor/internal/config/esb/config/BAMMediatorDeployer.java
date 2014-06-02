package org.wso2.cep.email.monitor.internal.config.esb.config;

import org.apache.axis2.AxisFault;
import org.apache.log4j.Logger;
import org.wso2.carbon.mediator.bam.config.stub.BAMMediatorConfigAdminStub;
import org.wso2.carbon.utils.CarbonUtils;
import org.wso2.cep.email.monitor.exception.EmailMonitorServiceException;
import org.wso2.cep.email.monitor.internal.util.EmailMonitorConstants;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.rmi.RemoteException;


public class BAMMediatorDeployer {


    private static Logger logger = Logger.getLogger(BAMMediatorDeployer.class);
    private BAMMediatorConfigAdminStub stub;


    public BAMMediatorDeployer(String ip, String port) throws EmailMonitorServiceException {
        String endPoint = EmailMonitorConstants.PROTOCOL + ip + ":" + port + EmailMonitorConstants.SERVICES + EmailMonitorConstants.BAM_MEDIATOR_ADMIN_SERVICE;

        try {
            stub = new BAMMediatorConfigAdminStub(endPoint);
        } catch (AxisFault axisFault) {
            logger.error(axisFault.getMessage());
            throw new EmailMonitorServiceException("Error when creating BAMMediatorDeployer", axisFault);
        }


    }

    public void addBAMServerProfile(String userName, String password, String CEPServerUserName, String CEPServerPassword, String CEPServerIP, String CEPServerPort) throws EmailMonitorServiceException {

        CarbonUtils.setBasicAccessSecurityHeaders(userName, password, stub._getServiceClient());

        String content = "";

        InputStream is = null;
        BufferedReader br = null;
        String line;

        is = ProxyDeployer.class.getResourceAsStream(EmailMonitorConstants.BAM_SERVER_PROFILE_CONFIGURATION_PATH);
        br = new BufferedReader(new InputStreamReader(is));
        try {
            while (null != (line = br.readLine())) {
                content = content + line;
            }
        } catch (IOException e) {
            logger.error(e.getMessage());
            throw new EmailMonitorServiceException("Error when adding BAM Server Profiles", e);
        }


        content.replace("CEPServerUserName", CEPServerUserName);
        content.replace("CEPServerIP", CEPServerIP);
        content.replace("CEPServerPort", CEPServerPort);

        content.replace("CEPServerPassword", CEPServerPassword);

        content = content.replace(EmailMonitorConstants.CEP_SERVER_USER_NAME, CEPServerUserName);
        content = content.replace(EmailMonitorConstants.CEP_SERVER_IP, CEPServerIP);
        content = content.replace(EmailMonitorConstants.CEP_SERVER_PORT, CEPServerPort);

        try {
            content.replace(EmailMonitorConstants.CEP_SERVER_ENCRYPTED_PASSWORD, encryptAndBase64Encode(CEPServerPassword));
        } catch (EmailMonitorServiceException e) {
            logger.error(e.getMessage());
            throw new EmailMonitorServiceException(e);
        }


        try {
            stub.saveResourceString(content, EmailMonitorConstants.BAM_SERVER_PROFILE_NAME);
        } catch (RemoteException e) {
            logger.error(e.getMessage());
            throw new EmailMonitorServiceException("Error when adding BAM Server Profiles and adding to  Stub", e);
        }


    }


    public String encryptAndBase64Encode(String plainText) throws EmailMonitorServiceException {
        try {
            return stub.encryptAndBase64Encode(plainText);
        } catch (RemoteException e) {
            logger.error(e.getMessage());
            throw new EmailMonitorServiceException(e);
        }
    }

}


