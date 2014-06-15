package org.wso2.cep.email.monitor.internal.config.esb.config;


import org.apache.axis2.AxisFault;
import org.apache.log4j.Logger;
import org.wso2.carbon.localentry.stub.types.LocalEntryAdminException;
import org.wso2.carbon.localentry.stub.types.LocalEntryAdminServiceStub;
import org.wso2.carbon.proxyadmin.stub.ProxyServiceAdminStub;
import org.wso2.carbon.utils.CarbonUtils;
import org.wso2.cep.email.monitor.exception.EmailMonitorServiceException;
import org.wso2.cep.email.monitor.internal.ds.EmailMonitorValueHolder;
import org.wso2.cep.email.monitor.internal.util.EmailMonitorConstants;

import java.rmi.RemoteException;

public class LocalEntryDeployer {

    private static Logger logger = Logger.getLogger(LocalEntryDeployer.class);
    private LocalEntryAdminServiceStub stub;


    public LocalEntryDeployer(String ip, String port) throws EmailMonitorServiceException {
        String endPoint = EmailMonitorConstants.PROTOCOL + ip + ":" + port + EmailMonitorConstants.SERVICES + EmailMonitorConstants.LOCAL_ENTRY_ADMIN_SERVICE;

        try {
            stub = new LocalEntryAdminServiceStub(endPoint);
        } catch (AxisFault axisFault) {
            logger.error(axisFault.getMessage());
            throw new EmailMonitorServiceException("Error when creating LocalEntryDeployer", axisFault);
        }


    }

    public void addLocalEntry(String userName, String password, String mailUserNAme, String mailPassword) throws EmailMonitorServiceException {
        CarbonUtils.setBasicAccessSecurityHeaders(userName, password, stub._getServiceClient());

        boolean isEmailEntryExist = false;
        boolean isPasswordEntryExist = false;
        try {
            String entrySet = stub.getEntryNamesString();
            isEmailEntryExist = entrySet.contains("[Entry]-email");
            isPasswordEntryExist = entrySet.contains("[Entry]-password");
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (LocalEntryAdminException e) {
            e.printStackTrace();
        }

        CryptographyManager cryptographyManager = new CryptographyManager();

        try {

            if(!isEmailEntryExist) {
                stub.addEntry("<localEntry key=\"email\"><email>" + mailUserNAme + "</email><description/></localEntry>");
            }
            if(!isPasswordEntryExist) {
                stub.addEntry("<localEntry key=\"password\"><password>" + cryptographyManager.encryptAndBase64Encode(mailPassword) + "</password><description/></localEntry>");
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (LocalEntryAdminException e) {
            e.printStackTrace();
        }
    }


    }