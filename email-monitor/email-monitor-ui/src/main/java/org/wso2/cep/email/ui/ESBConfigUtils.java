package org.wso2.cep.email.ui;

import org.apache.axis2.AxisFault;
import org.apache.axis2.context.ConfigurationContext;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.wso2.cep.email.monitor.exception.EmailMonitorServiceException;
import org.wso2.cep.email.monitor.internal.config.esb.config.ESBConfigurationHelper;
import org.wso2.carbon.ui.CarbonUIUtil;

import org.wso2.cep.email.monitor.stub.admin.EmailMonitorAdminServiceEmailMonitorAdminException;
import org.wso2.cep.email.monitor.stub.admin.EmailMonitorAdminServiceStub;

import java.rmi.RemoteException;

public class ESBConfigUtils {

    private String esbUserName;
    private String esbPassword;
    private String esbIP;
    private String esbPort;
    EmailMonitorAdminServiceStub emailMonitorAdminServiceStub;

    public ESBConfigUtils(String esbIP, String esbPort, String esbUserName, String esbPassword){
        this.esbUserName = esbUserName;
        this.esbPassword = esbPassword;
        this.esbIP = esbIP;
        this.esbPort = esbPort;
    }

    public void AddConfigurations(String CEPServerUserName, String CEPServerPassword, String mailUserNAme, String mailPassword, String CEPServerIP, String CEPServerPort, String backendServerURL, ConfigurationContext configCtx){
        String endPoint = backendServerURL + "EmailMonitorAdminService";

        try {
            emailMonitorAdminServiceStub = new EmailMonitorAdminServiceStub(configCtx, endPoint);
            emailMonitorAdminServiceStub.addESBConfigurations(esbIP, esbPort, esbUserName, esbPassword, CEPServerUserName, CEPServerPassword, mailUserNAme, mailPassword, CEPServerIP, CEPServerPort);
        } catch (AxisFault axisFault) {
            axisFault.printStackTrace();
        } catch (RemoteException e) {
                e.printStackTrace();
        } catch (EmailMonitorAdminServiceEmailMonitorAdminException e) {
            e.printStackTrace();
        }

//        String out = "false";
//        try {
//            ESBConfigurationHelper esbConfigurationHelper = new ESBConfigurationHelper(esbIP, esbPort);
//            esbConfigurationHelper.addConfigurations(esbUserName, esbPassword, CEPServerUserName, CEPServerPassword, mailUserNAme, mailPassword, CEPServerIP, CEPServerPort);
//        } catch (EmailMonitorServiceException e) {
//            e.printStackTrace();
//        }
//
//        out = "true";
//        return out;
    }


}
