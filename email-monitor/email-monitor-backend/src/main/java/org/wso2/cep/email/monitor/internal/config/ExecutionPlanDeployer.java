package org.wso2.cep.email.monitor.internal.config;


import org.apache.axis2.AxisFault;
import org.apache.axis2.client.Options;
import org.apache.axis2.client.ServiceClient;
import org.apache.axis2.context.ConfigurationContext;
import org.apache.log4j.Logger;
import org.wso2.carbon.event.processor.stub.EventProcessorAdminServiceStub;
import org.wso2.carbon.utils.CarbonUtils;
import org.wso2.cep.email.monitor.exception.EmailMonitorServiceException;
import org.wso2.cep.email.monitor.internal.config.esb.config.util.SecurityConstants;
import org.wso2.cep.email.monitor.internal.util.EmailMonitorConstants;

import java.rmi.RemoteException;

public class ExecutionPlanDeployer {

    private static Logger logger = Logger.getLogger(ExecutionPlanDeployer.class);
    private EventProcessorAdminServiceStub eventProcessorAdminServiceStub;

    public ExecutionPlanDeployer(String cookie, String backendServerURL, ConfigurationContext configCtx) throws EmailMonitorServiceException {

        String endPoint = backendServerURL + "EventProcessorAdminService";


        try {
            eventProcessorAdminServiceStub = new EventProcessorAdminServiceStub(configCtx, endPoint);
        } catch (AxisFault axisFault) {
            logger.error(axisFault.getMessage());
            throw new EmailMonitorServiceException("Error when creating ExecutionPlanDeployer", axisFault);
        }

        ServiceClient client = eventProcessorAdminServiceStub._getServiceClient();
        Options option = client.getOptions();
        option.setManageSession(true);
        option.setProperty(org.apache.axis2.transport.http.HTTPConstants.COOKIE_STRING, cookie);

    }


    public void createExecutionPlan(String executionPlanXmlConfiguration) throws EmailMonitorServiceException {

        try {
            eventProcessorAdminServiceStub.deployExecutionPlanConfigurationFromConfigXml(executionPlanXmlConfiguration);
        } catch (RemoteException e) {
            logger.error(e.getMessage());
            throw new EmailMonitorServiceException("Error when creating AdminServiceStub", e);
        }

    }


}



