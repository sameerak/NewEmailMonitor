package org.wso2.cep.email.monitor.internal.config;


import org.apache.axis2.engine.AxisConfiguration;
import org.apache.log4j.Logger;
import org.wso2.carbon.event.processor.core.EventProcessorService;
import org.wso2.carbon.event.processor.core.exception.ExecutionPlanConfigurationException;
import org.wso2.carbon.event.processor.core.exception.ExecutionPlanDependencyValidationException;
import org.wso2.cep.email.monitor.exception.EmailMonitorServiceException;
import org.wso2.cep.email.monitor.internal.config.esb.config.XMLReader;
import org.wso2.cep.email.monitor.internal.ds.EmailMonitorValueHolder;
import org.wso2.cep.email.monitor.internal.util.EmailMonitorConstants;


public class ExecutionPlanDeployer {

    private static Logger logger = Logger.getLogger(ExecutionPlanDeployer.class);
    private EventProcessorService eventProcessorService;
    private XMLReader xmlReader;
    private static int queryCount = 0;

    public ExecutionPlanDeployer() {

        xmlReader = new XMLReader();
        EmailMonitorValueHolder emailMonitorValueHolder = EmailMonitorValueHolder.getInstance();
        eventProcessorService = emailMonitorValueHolder.getEventProcessorService();
    }


    public void createExecutionPlan(String query, AxisConfiguration axisConfiguration) throws EmailMonitorServiceException {
        String executionPlanXmlConfiguration = this.getExecutionPlanConfiguration(query);
        try {

            eventProcessorService.deployExecutionPlanConfiguration(executionPlanXmlConfiguration, axisConfiguration);
        } catch (ExecutionPlanDependencyValidationException e) {
            logger.error(e.getMessage());
            throw new EmailMonitorServiceException("Error when adding execution plan", e);
        } catch (ExecutionPlanConfigurationException e) {
            logger.error(e.getMessage());
            throw new EmailMonitorServiceException("Error when adding execution plan", e);
        }
    }


    private String getExecutionPlanConfiguration(String query) throws EmailMonitorServiceException {
        String[] queryInfo = query.split("\\s+|\\[|#|;");
        String inputStream = queryInfo[1];



        String queryOutputPart =  query.substring(query.indexOf("insert into"));
        String[] arry = queryOutputPart.split("\\s+|\\[|#|;");
        String outputStream = arry[2];

        String content = xmlReader.readXML(EmailMonitorConstants.EXECUTION_PLAN_TEMPLATE);
        content = content.replace(EmailMonitorConstants.EXECUTION_PLAN_NAME, EmailMonitorConstants.ADD_EXECUTION_PLAN_NAME + queryCount);
        content = content.replace(EmailMonitorConstants.ADD_QUERY, query);
        content = content.replaceAll(EmailMonitorConstants.INPUT_STREAM_NAME, inputStream);
        content = content.replaceAll(EmailMonitorConstants.OUTPUT_STREAM_NAME, outputStream);
        queryCount++;

        return content;
    }


}



