package org.wso2.cep.email.esb;

import org.apache.axis2.AxisFault;
import org.apache.log4j.Logger;
import org.wso2.carbon.mediator.bam.config.stub.BAMMediatorConfigAdminStub;
import org.wso2.carbon.mediator.bam.config.stub.axis2.types.SaveBamServerConfig;
import org.wso2.carbon.mediator.bam.config.xsd.BamServerConfig;
import org.wso2.carbon.utils.CarbonUtils;
import org.wso2.cep.email.esb.util.SecurityConstants;
import org.wso2.cep.util.EmailMonitorConstants;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.rmi.RemoteException;


public class BAMMediatorAdminClient {


    private static Logger logger = Logger.getLogger(BAMMediatorAdminClient.class);
    private BAMMediatorConfigAdminStub stub;


    public BAMMediatorAdminClient(String ip, String port) {
        String endPoint = EmailMonitorConstants.PROTOCOL + ip + ":" + port + EmailMonitorConstants.SERVICES + EmailMonitorConstants.BAM_MEDIATOR_ADMIN_SERVICE;

       try {
            stub = new BAMMediatorConfigAdminStub(endPoint);
        } catch (AxisFault axisFault) {
            logger.error(axisFault.getMessage());
        }



    }

    public void addMailProxy(String userName, String password){

        CarbonUtils.setBasicAccessSecurityHeaders(userName, password, stub._getServiceClient());

        String content = "";

        InputStream is = null;
        BufferedReader br = null;
        String line;

        is = ProxyAdminClient.class.getResourceAsStream(EmailMonitorConstants.BAM_SERVER_PROFILE_CONFIGURATION_PATH);
        br = new BufferedReader(new InputStreamReader(is));
        try {
            while (null != (line = br.readLine())) {
                content = content + line;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            stub.saveResourceString(content,EmailMonitorConstants.BAM_SERVER_PROFILE_NAME);
        } catch (RemoteException e) {
           logger.error(e.getMessage());
        }


    }

    public String encryptAndBase64Encode(String plainText) throws RemoteException {
        try {
            return stub.encryptAndBase64Encode(plainText);
        } catch (RemoteException e) {
            logger.error(e.getMessage());
            throw new RemoteException(e.getMessage(), e);
        }
    }
}


