package org.wso2.cep.email.monitor.internal.config;


import org.apache.axis2.AxisFault;
import org.apache.axis2.client.Options;
import org.apache.axis2.client.ServiceClient;
import org.apache.axis2.context.ConfigurationContext;
import org.apache.log4j.Logger;
import org.wso2.carbon.event.output.adaptor.manager.stub.OutputEventAdaptorManagerAdminServiceStub;
import org.wso2.carbon.event.output.adaptor.manager.stub.types.OutputEventAdaptorPropertyDto;
import org.wso2.carbon.event.processor.stub.EventProcessorAdminServiceStub;
import org.wso2.cep.email.monitor.exception.EmailMonitorServiceException;

import java.rmi.RemoteException;

public class OutputAdapterDeployer {

    private static Logger logger = Logger.getLogger(OutputAdapterDeployer.class);
    private OutputEventAdaptorManagerAdminServiceStub outputEventAdaptorManagerAdminServiceStub;

    public OutputAdapterDeployer(String cookie, String backendServerURL, ConfigurationContext configCtx) throws EmailMonitorServiceException {

        String endPoint = backendServerURL + "OutputEventAdaptorManagerAdminService";


        try {
            outputEventAdaptorManagerAdminServiceStub = new OutputEventAdaptorManagerAdminServiceStub(configCtx, endPoint);
        } catch (AxisFault axisFault) {
            logger.error(axisFault.getMessage());
            throw new EmailMonitorServiceException("Error when creating OutputAdapterDeployer", axisFault);
        }

        ServiceClient client = outputEventAdaptorManagerAdminServiceStub._getServiceClient();
        Options option = client.getOptions();
        option.setManageSession(true);
        option.setProperty(org.apache.axis2.transport.http.HTTPConstants.COOKIE_STRING, cookie);

    }

    public void createSoapOutputAdapter() throws EmailMonitorServiceException {
        try {
            outputEventAdaptorManagerAdminServiceStub.deployOutputEventAdaptorConfiguration("soap-sender", "soap", new OutputEventAdaptorPropertyDto[0]);
        } catch (RemoteException e) {
            logger.error(e.getMessage());
            throw new EmailMonitorServiceException("Error when creating OutputAdapter", e);
        }
    }
}

