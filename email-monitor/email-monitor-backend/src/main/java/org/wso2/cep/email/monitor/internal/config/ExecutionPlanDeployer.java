package org.wso2.cep.email.monitor.internal.config;


import org.apache.axis2.AxisFault;
import org.apache.axis2.client.Options;
import org.apache.axis2.client.ServiceClient;
import org.apache.axis2.context.ConfigurationContext;
import org.apache.axis2.engine.AxisConfiguration;
import org.apache.log4j.Logger;
import org.wso2.carbon.event.processor.core.EventProcessorService;
import org.wso2.carbon.event.processor.core.exception.ExecutionPlanConfigurationException;
import org.wso2.carbon.event.processor.core.exception.ExecutionPlanDependencyValidationException;
import org.wso2.carbon.event.processor.stub.EventProcessorAdminServiceStub;
import org.wso2.carbon.proxyadmin.stub.ProxyServiceAdminStub;
import org.wso2.carbon.utils.CarbonUtils;
import org.wso2.cep.email.monitor.exception.EmailMonitorServiceException;
import org.wso2.cep.email.monitor.internal.config.esb.config.util.SecurityConstants;
import org.wso2.cep.email.monitor.internal.ds.EmailMonitorValueHolder;
import org.wso2.cep.email.monitor.internal.util.EmailMonitorConstants;

import java.rmi.RemoteException;

public class ExecutionPlanDeployer {

    private static Logger logger = Logger.getLogger(ExecutionPlanDeployer.class);
    private EventProcessorAdminServiceStub eventProcessorAdminServiceStub;
    private EmailMonitorValueHolder emailMonitorValueHolder;
    private EventProcessorService eventProcessorService;

    public ExecutionPlanDeployer(){


        emailMonitorValueHolder = EmailMonitorValueHolder.getInstance();
        eventProcessorService = emailMonitorValueHolder.getEventProcessorService() ;
    }



    public void createExecutionPlan(String executionPlanXmlConfiguration,AxisConfiguration axisConfiguration) throws EmailMonitorServiceException {
        try {
            eventProcessorService.deployExecutionPlanConfiguration(executionPlanXmlConfiguration,axisConfiguration );
        } catch (ExecutionPlanDependencyValidationException e) {
            logger.error(e.getMessage());
        } catch (ExecutionPlanConfigurationException e) {
           logger.error(e.getMessage());
        }
    }


    }



