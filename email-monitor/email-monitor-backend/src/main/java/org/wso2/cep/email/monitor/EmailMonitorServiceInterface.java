package org.wso2.cep.email.monitor;


import org.apache.axis2.context.ConfigurationContext;
import org.wso2.cep.email.monitor.exception.EmailMonitorServiceException;

/**
 * This is the EmailMonitorServiceInterface for access EmailMonitorService functinalites from service consumer.
 */
public interface EmailMonitorServiceInterface {


    /**
     * Add BAMServerProfile to the ESB registry for send events to BAM or CEP
     *
     * @param ip
     * @param port
     * @param userName
     * @param password
     * @param CEPServerUserName
     * @param CEPServerPassword
     * @param CEPServerIP
     * @param CEPServerPort
     * @return
     */
    public boolean addBAMServerProfile(String ip, String port, String userName, String password, String CEPServerUserName, String CEPServerPassword, String CEPServerIP, String CEPServerPort) throws EmailMonitorServiceException;

    /**
     * Add MailProxy for ESB
     *
     * @param ip
     * @param port
     * @param userName
     * @param password
     * @return
     */
    public boolean addMailProxy(String ip, String port, String userName, String password) throws EmailMonitorServiceException;

    /**
     * SchedulingTasks for predically run the ESB proxy
     *
     * @param ip
     * @param port
     * @param userName
     * @param password
     * @param mailUserName
     * @param mailPassword
     * @return
     */
    public boolean addScheduledTask(String ip, String port, String userName, String password, String mailUserName, String mailPassword) throws EmailMonitorServiceException;

    /**
     * Create Execution Plan for deploy in CEP for the run CEP queries among mails
     *
     * @param cookie
     * @param backendServerURL
     * @param configCtx
     * @param executionPlanXmlConfiguration
     * @return
     */
    public boolean createExecutionPlan(String cookie, String backendServerURL, ConfigurationContext configCtx, String executionPlanXmlConfiguration) throws EmailMonitorServiceException;

    /**
     * Create MailInputStream and stores it in the CEP
     *
     * @param cookie
     * @param backendServerURL
     * @param configCtx
     * @return
     */
    public boolean createMailInputStream(String cookie, String backendServerURL, ConfigurationContext configCtx) throws EmailMonitorServiceException;

    /**
     * Add bam proxy and tasks configurations to esb
     *
     * @param ip
     * @param port
     * @param userName
     * @param password
     * @param CEPServerUserName
     * @param CEPServerPassword
     * @param mailUserNAme
     * @param mailPassword
     * @param CEPServerIP
     * @param CEPServerPort
     * @return
     */
    public boolean addESBConfigurations(String ip, String port, String userName, String password, String CEPServerUserName, String CEPServerPassword, String mailUserNAme, String mailPassword, String CEPServerIP, String CEPServerPort) throws EmailMonitorServiceException;


}
