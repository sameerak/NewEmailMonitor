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
    private XMLReader xmlReader;


    public BAMMediatorDeployer(String ip, String port) throws EmailMonitorServiceException {
        xmlReader = new XMLReader();
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

        boolean isServerProfileCreated = false;

        try {
            stub.bamServerConfigExists(EmailMonitorConstants.BAM_SERVER_PROFILE_NAME);
        } catch (RemoteException e) {
            throw new EmailMonitorServiceException("Error when adding BAM Server Profiles and adding to  Stub", e);
        }

        if (!isServerProfileCreated) {

            String content = xmlReader.readXML(EmailMonitorConstants.BAM_SERVER_PROFILE_CONFIGURATION_PATH);

            content.replace("CEPServerUserName", CEPServerUserName);
            content.replace("CEPServerIP", CEPServerIP);
            content.replace("CEPServerPort", CEPServerPort);

            content.replace("CEPServerPassword", CEPServerPassword);

            content = content.replace(EmailMonitorConstants.CEP_SERVER_USER_NAME, CEPServerUserName);
            content = content.replace(EmailMonitorConstants.CEP_SERVER_IP, CEPServerIP);
            content = content.replace(EmailMonitorConstants.CEP_SERVER_PORT, CEPServerPort);

            String encryptedPassword = "";

            try {

                encryptedPassword = stub.encryptAndBase64Encode(CEPServerPassword);
            } catch (RemoteException e) {

                logger.error(e.getMessage());
                throw new EmailMonitorServiceException(e);
            }
            content = content.replace(EmailMonitorConstants.CEP_SERVER_ENCRYPTED_PASSWORD, encryptedPassword);

            try {
                stub.saveResourceString(content, EmailMonitorConstants.BAM_SERVER_PROFILE_NAME);
            } catch (RemoteException e) {
                logger.error(e.getMessage());
                throw new EmailMonitorServiceException("Error when adding BAM Server Profiles and adding to  Stub", e);
            }


        }
    }




}




