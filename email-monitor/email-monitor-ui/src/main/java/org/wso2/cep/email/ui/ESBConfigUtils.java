package org.wso2.cep.email.ui;

import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.wso2.cep.email.monitor.exception.EmailMonitorServiceException;
import org.wso2.cep.email.monitor.internal.config.esb.config.ESBConfigurationHelper;

public class ESBConfigUtils {

    private String esbUserName;
    private String esbPassword;
    private String esbIP;
    private String esbPort;

    public ESBConfigUtils(String esbIP, String esbPort, String esbUserName, String esbPassword){
        this.esbUserName = esbUserName;
        this.esbPassword = esbPassword;
        this.esbIP = esbIP;
        this.esbPort = esbPort;
    }

    public String AddConfigurations(String CEPServerUserName, String CEPServerPassword, String mailUserNAme, String mailPassword,String CEPServerIP ,String CEPServerPort){
        String out = "false";
        try {
            ESBConfigurationHelper esbConfigurationHelper = new ESBConfigurationHelper(esbIP, esbPort);
            esbConfigurationHelper.addConfigurations(esbUserName, esbPassword, CEPServerUserName, CEPServerPassword, mailUserNAme, mailPassword, CEPServerIP, CEPServerPort);
        } catch (EmailMonitorServiceException e) {
            e.printStackTrace();
        }

        out = "true";
        return out;
    }


}
