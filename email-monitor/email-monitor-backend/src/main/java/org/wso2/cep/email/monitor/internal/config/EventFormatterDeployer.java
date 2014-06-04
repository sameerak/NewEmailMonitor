package org.wso2.cep.email.monitor.internal.config;


import org.apache.axis2.AxisFault;
import org.apache.axis2.client.Options;
import org.apache.axis2.client.ServiceClient;
import org.apache.axis2.context.ConfigurationContext;
import org.apache.axis2.engine.AxisConfiguration;
import org.apache.log4j.Logger;
import org.wso2.carbon.event.formatter.core.EventFormatterService;
import org.wso2.carbon.event.formatter.core.exception.EventFormatterConfigurationException;
import org.wso2.carbon.event.formatter.stub.EventFormatterAdminServiceStub;
import org.wso2.carbon.event.output.adaptor.manager.stub.OutputEventAdaptorManagerAdminServiceStub;
import org.wso2.cep.email.monitor.exception.EmailMonitorServiceException;
import org.wso2.cep.email.monitor.internal.ds.EmailMonitorValueHolder;

import java.rmi.RemoteException;

public class EventFormatterDeployer {
    private static Logger logger = Logger.getLogger(EventFormatterDeployer.class);
    private EventFormatterService eventFormatterService ;
    private EmailMonitorValueHolder emailMonitorValueHolder;

    public EventFormatterDeployer(String cookie, String backendServerURL, ConfigurationContext configCtx) throws EmailMonitorServiceException {

        emailMonitorValueHolder = EmailMonitorValueHolder.getInstance();
        eventFormatterService = emailMonitorValueHolder.getEventFormatterService() ;
    }




    public void createEventFormatter(String eventFormatterConfigurationXML) throws EmailMonitorServiceException {

        try {
            eventFormatterService.deployDefaultEventSender(eventFormatterConfigurationXML,new AxisConfiguration() );
        } catch (EventFormatterConfigurationException e) {
            logger.error(e.getMessage());
        }


    }
}
