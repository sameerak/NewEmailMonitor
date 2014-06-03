package org.wso2.cep.email.monitor.internal.config;


import org.apache.axis2.AxisFault;
import org.apache.axis2.client.Options;
import org.apache.axis2.client.ServiceClient;
import org.apache.axis2.context.ConfigurationContext;
import org.apache.log4j.Logger;
import org.wso2.carbon.event.formatter.stub.EventFormatterAdminServiceStub;
import org.wso2.carbon.event.output.adaptor.manager.stub.OutputEventAdaptorManagerAdminServiceStub;
import org.wso2.cep.email.monitor.exception.EmailMonitorServiceException;

import java.rmi.RemoteException;

public class EventFormatterDeployer {
    private static Logger logger = Logger.getLogger(EventFormatterDeployer.class);
    private EventFormatterAdminServiceStub eventFormatterAdminServiceStub;

    public EventFormatterDeployer(String cookie, String backendServerURL, ConfigurationContext configCtx) throws EmailMonitorServiceException {

        String endPoint = backendServerURL + "EventFormatterAdminService";


        try {
            eventFormatterAdminServiceStub = new EventFormatterAdminServiceStub(configCtx, endPoint);
        } catch (AxisFault axisFault) {
            logger.error(axisFault.getMessage());
            throw new EmailMonitorServiceException("Error when creating EventAdapterDeployer", axisFault);
        }

        ServiceClient client = eventFormatterAdminServiceStub._getServiceClient();
        Options option = client.getOptions();
        option.setManageSession(true);
        option.setProperty(org.apache.axis2.transport.http.HTTPConstants.COOKIE_STRING, cookie);

    }

    public void createEventFormatter(String eventFormatterConfigurationXML) throws EmailMonitorServiceException {
        try {
            eventFormatterAdminServiceStub.deployEventFormatterConfiguration(eventFormatterConfigurationXML);
        } catch (RemoteException e) {
            logger.error(e.getMessage());
            throw new EmailMonitorServiceException("Error when creating EventAdapterDeployer", e) ;
        }
    }
}
