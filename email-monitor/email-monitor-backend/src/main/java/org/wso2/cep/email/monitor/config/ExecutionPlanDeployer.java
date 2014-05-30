package org.wso2.cep.email.monitor.config;


import org.apache.axis2.AxisFault;
import org.apache.axis2.client.Options;
import org.apache.axis2.client.ServiceClient;
import org.apache.axis2.context.ConfigurationContext;
import org.apache.log4j.Logger;
import org.wso2.carbon.event.processor.stub.EventProcessorAdminServiceStub;
import org.wso2.carbon.utils.CarbonUtils;
import org.wso2.cep.email.monitor.config.esb.config.util.SecurityConstants;
import org.wso2.cep.email.monitor.util.EmailMonitorConstants;

import java.rmi.RemoteException;

public class ExecutionPlanDeployer {

    private static Logger logger = Logger.getLogger(ExecutionPlanDeployer.class);
    private EventProcessorAdminServiceStub eventProcessorAdminServiceStub;

    public ExecutionPlanDeployer(String cookie, String backendServerURL, ConfigurationContext configCtx){

        String endPoint = backendServerURL + "EventProcessorAdminService";


        try {
            eventProcessorAdminServiceStub = new EventProcessorAdminServiceStub(configCtx, endPoint);
        } catch (AxisFault axisFault) {
            logger.error(axisFault.getMessage());
        }

        ServiceClient client = eventProcessorAdminServiceStub._getServiceClient();
        Options option = client.getOptions();
        option.setManageSession(true);
        option.setProperty(org.apache.axis2.transport.http.HTTPConstants.COOKIE_STRING, cookie);

    }


    public ExecutionPlanDeployer(String ip, String port){


        System.setProperty(SecurityConstants.TRUSTSTORE, "/home/sachini/Documents/wso2esb-4.8.1/repository/resources/security/client-truststore.jks");
        System.setProperty(SecurityConstants.TRUSTSTORE_PASSWORD, SecurityConstants.KEY_STORE_PASSWORD);
        System.setProperty(SecurityConstants.TRUSTSTORE_TYPE, SecurityConstants.KEY_STORE_TYPE);

        String endPoint = EmailMonitorConstants.PROTOCOL + ip + ":" + port + EmailMonitorConstants.SERVICES + "EventProcessorAdminService";

        try {
            eventProcessorAdminServiceStub = new EventProcessorAdminServiceStub(endPoint);
        } catch (AxisFault axisFault) {
            logger.error(axisFault.getMessage());
        }

        CarbonUtils.setBasicAccessSecurityHeaders("admin", "admin", eventProcessorAdminServiceStub._getServiceClient());

    }


    public void createExecutionPlan(String executionPlanXmlConfiguration){

        try {
            eventProcessorAdminServiceStub.deployExecutionPlanConfigurationFromConfigXml(executionPlanXmlConfiguration);
        } catch (RemoteException e) {
           logger.error(e.getMessage());
        }

    }




}



