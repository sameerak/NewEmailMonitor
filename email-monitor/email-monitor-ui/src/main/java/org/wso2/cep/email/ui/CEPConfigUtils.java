package org.wso2.cep.email.ui;

import org.apache.axis2.AxisFault;
import org.apache.axis2.context.ConfigurationContext;
import org.wso2.cep.email.monitor.exception.EmailMonitorServiceException;
import org.wso2.cep.email.monitor.internal.config.StreamDeployer;

import org.wso2.cep.email.monitor.internal.util.EmailMonitorConstants;
import org.wso2.cep.email.monitor.stub.admin.EmailMonitorAdminServiceEmailMonitorAdminException;
import org.wso2.cep.email.monitor.stub.admin.EmailMonitorAdminServiceStub;

import java.rmi.RemoteException;

public class CEPConfigUtils {

    StreamDeployer streamDeployer;
    EmailMonitorAdminServiceStub emailMonitorAdminServiceStub;

    public CEPConfigUtils(String backendServerURL, ConfigurationContext configCtx){
        String endPoint = backendServerURL + "EmailMonitorAdminService";
        try {
            emailMonitorAdminServiceStub = new EmailMonitorAdminServiceStub(configCtx, endPoint);

            emailMonitorAdminServiceStub.createMailInputStream();
            emailMonitorAdminServiceStub.createMailOutputStream();

        } catch (AxisFault axisFault) {
            axisFault.printStackTrace();
        }catch (RemoteException e) {
            e.printStackTrace();
        } catch (EmailMonitorAdminServiceEmailMonitorAdminException e) {
            e.printStackTrace();
        }



//        try {
//            streamDeployer = new StreamDeployer(cookie, backendServerURL, configCtx);
//            streamDeployer.createMailInputStream();
//            streamDeployer.createMailOutputStream();
//        } catch (EmailMonitorServiceException e) {
//            e.printStackTrace();
//        }
    }
}
