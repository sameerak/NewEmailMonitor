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

    public void addLocalEntry(String userName, String password) throws EmailMonitorServiceException {
        CarbonUtils.setBasicAccessSecurityHeaders(userName, password, stub._getServiceClient());
        try {
            stub.addEntry("localEntryrou");
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (LocalEntryAdminException e) {
            e.printStackTrace();
        }
    }




    }