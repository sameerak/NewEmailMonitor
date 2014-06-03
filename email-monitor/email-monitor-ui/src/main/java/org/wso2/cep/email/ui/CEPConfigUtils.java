package org.wso2.cep.email.ui;

import org.apache.axis2.AxisFault;
import org.apache.axis2.context.ConfigurationContext;
import org.wso2.cep.email.monitor.exception.EmailMonitorServiceException;
import org.wso2.cep.email.monitor.internal.config.StreamDeployer;

import org.wso2.cep.email.monitor.internal.util.EmailMonitorConstants;
import org.wso2.cep.email.monitor.stub.admin.EmailMonitorAdminServiceStub;

public class CEPConfigUtils {

    StreamDeployer streamDeployer;
    EmailMonitorAdminServiceStub emailMonitorAdminServiceStub;

    public CEPConfigUtils(String cookie, String backendServerURL, ConfigurationContext configCtx){
//        String endPoint = backendServerURL + "EmailMonitorAdminService";
//        try {
//            emailMonitorAdminServiceStub = new EmailMonitorAdminServiceStub(configCtx, endPoint);
//            org.apache.axis2.context.xsd.ConfigurationContext configurationContext = new org.apache.axis2.context.xsd.ConfigurationContext();
////            configurationContext.setRootContext();
//            emailMonitorAdminServiceStub.createMailInputStream(cookie, backendServerURL, (org.apache.axis2.context.xsd.ConfigurationContext) configCtx);
//        } catch (AxisFault axisFault) {
//            axisFault.printStackTrace();
//        }
        try {
            streamDeployer = new StreamDeployer(cookie, backendServerURL, configCtx);
            streamDeployer.createMailInputStream();
            streamDeployer.createMailOutputStream();
        } catch (EmailMonitorServiceException e) {
            e.printStackTrace();
        }
    }
}
