package org.wso2.cep.email.esb;


import org.apache.axis2.AxisFault;

import org.apache.commons.io.FileUtils;
import org.wso2.carbon.proxyadmin.stub.ProxyServiceAdminProxyAdminException;
import org.wso2.carbon.proxyadmin.stub.ProxyServiceAdminStub;
import org.wso2.carbon.proxyadmin.stub.types.carbon.ProxyData;
import org.wso2.carbon.utils.CarbonUtils;
import org.apache.log4j.Logger;
import org.wso2.cep.email.esb.util.SecurityConstants;

import java.io.File;
import java.io.IOException;
import java.rmi.RemoteException;


public class ProxyAdminClient {
    private static final String PROXY_ADMIN_SERVICE = "ProxyServiceAdmin";
    private static Logger logger = Logger.getLogger(ProxyAdminClient.class);
    private ProxyServiceAdminStub stub;


    public ProxyAdminClient(String ip, String port) {
        String endPoint = "https://" + ip + ":" + port + "/services/" + PROXY_ADMIN_SERVICE;


        // Set client trust store
        System.setProperty(SecurityConstants.TRUSTSTORE, SecurityConstants.CLIENT_TRUST_STORE_PATH);
        System.setProperty(SecurityConstants.TRUSTSTORE_PASSWORD, SecurityConstants.KEY_STORE_PASSWORD);
        System.setProperty(SecurityConstants.TRUSTSTORE_TYPE, SecurityConstants.KEY_STORE_TYPE);


        try {
            stub = new ProxyServiceAdminStub(endPoint);
        } catch (AxisFault axisFault) {
            logger.error(axisFault.getMessage());
        }



    }

    public void addMailProxy(String userName, String password){

        CarbonUtils.setBasicAccessSecurityHeaders(userName, password, stub._getServiceClient());

        String proxyName = "gmail_passwordAuthentication_proxy";

        //Set proxy configuration data
        String[] transport = {"http", "https"};
        ProxyData data = new ProxyData();
        data.setName(proxyName);
        data.setStartOnLoad(true);
        data.setTransports(transport);
        String content = null;

        try {
            String path = new File("").getAbsolutePath();
            content = FileUtils.readFileToString(new File(path + "/email-monitor-backend/src/main/resources/config/insequence.xml"));
        } catch (IOException e) {
            logger.error(e.getMessage());
        }

        data.setInSeqXML(content);


        try {
            stub.addProxy(data);
        } catch (RemoteException e) {
            logger.error(e.getMessage());
        } catch (ProxyServiceAdminProxyAdminException e) {
            logger.error(e.getMessage());
        }

    }


    public static void main(String[] args) throws RemoteException, ProxyServiceAdminProxyAdminException {

        new ProxyAdminClient("10.100.5.89", "9443").addMailProxy("admin", "admin");
    }

}


