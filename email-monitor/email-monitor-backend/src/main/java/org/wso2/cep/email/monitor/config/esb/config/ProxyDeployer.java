package org.wso2.cep.email.monitor.config.esb.config;


import org.apache.axis2.AxisFault;

import org.wso2.carbon.proxyadmin.stub.ProxyServiceAdminProxyAdminException;
import org.wso2.carbon.proxyadmin.stub.ProxyServiceAdminStub;
import org.wso2.carbon.proxyadmin.stub.types.carbon.ProxyData;
import org.wso2.carbon.utils.CarbonUtils;
import org.apache.log4j.Logger;
import org.wso2.cep.email.monitor.util.EmailMonitorConstants;

import java.io.*;
import java.rmi.RemoteException;


public class ProxyDeployer {

    private static Logger logger = Logger.getLogger(ProxyDeployer.class);
    private ProxyServiceAdminStub stub;


    public ProxyDeployer(String ip, String port) {
        String endPoint = EmailMonitorConstants.PROTOCOL + ip + ":" + port + EmailMonitorConstants.SERVICES + EmailMonitorConstants.PROXY_ADMIN_SERVICE;

        try {
            stub = new ProxyServiceAdminStub(endPoint);
        } catch (AxisFault axisFault) {
            logger.error(axisFault.getMessage());
        }



    }

    public void addMailProxy(String userName, String password){

        CarbonUtils.setBasicAccessSecurityHeaders(userName, password, stub._getServiceClient());

        String proxyName = EmailMonitorConstants.PROXY_NAME;

        //Set proxy configuration data
        String[] transport = {"http", "https"};
        ProxyData data = new ProxyData();
        data.setName(proxyName);
        data.setStartOnLoad(true);
        data.setTransports(transport);
        String content = "";

        InputStream is = null;
        BufferedReader br = null;
        String line;

        is = ProxyDeployer.class.getResourceAsStream(EmailMonitorConstants.PROXY_PATH);
        br = new BufferedReader(new InputStreamReader(is));
        try {
            while (null != (line = br.readLine())) {
                content = content + line;
            }
        } catch (IOException e) {
            e.printStackTrace();
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


}


