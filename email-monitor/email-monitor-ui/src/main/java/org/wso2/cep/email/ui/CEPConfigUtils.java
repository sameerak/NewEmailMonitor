package org.wso2.cep.email.ui;

import org.apache.axis2.context.ConfigurationContext;
import org.wso2.cep.email.monitor.exception.EmailMonitorServiceException;
import org.wso2.cep.email.monitor.internal.config.StreamDeployer;

public class CEPConfigUtils {

    StreamDeployer streamDeployer;

    public CEPConfigUtils(String cookie, String backendServerURL, ConfigurationContext configCtx){
        try {
            streamDeployer = new StreamDeployer(cookie, backendServerURL, configCtx);
            streamDeployer.createMailInputStream();
            streamDeployer.createMailOutputStream();
        } catch (EmailMonitorServiceException e) {
            e.printStackTrace();
        }
    }
}
