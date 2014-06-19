package org.wso2.cep.email.monitor;


import org.apache.axis2.context.ConfigurationContext;
import org.apache.axis2.engine.AxisConfiguration;
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
     * @param query
     * @return
     */
    public String createExecutionPlan( String query,AxisConfiguration axisConfiguration) throws EmailMonitorServiceException;

    /**
     * Create MailInputStream and stores it in the CEP
     * @return
     */
    public boolean createMailInputStream(int tenantID) throws EmailMonitorServiceException;

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

    /**
     *
     * @param ESBServerIP
     * @param ESBServerPort
     * @param ESBServerUsername
     * @param ESBServerPassword
     * @param mailAddress
     * @param tenantID
     * @param axisConfiguration
     * @return
     * @throws EmailMonitorServiceException
     */
    public boolean addCEPConfigurations(String ESBServerIP, String ESBServerPort, String ESBServerUsername, String ESBServerPassword, String mailAddress,int tenantID, AxisConfiguration axisConfiguration) throws EmailMonitorServiceException ;


        /**
        * @param tenantID
        * @return
        * @throws EmailMonitorServiceException
        */
    public boolean createMailOutputStream(int tenantID) throws EmailMonitorServiceException;

    /**
     *
     * @param tenantID
     * @return
     * @throws EmailMonitorServiceException
     */
    public boolean createThreadDetailsStream(int tenantID) throws EmailMonitorServiceException;

    /**
     *
     * @param tenantID
     * @return
     * @throws EmailMonitorServiceException
     */
    public boolean createLabelDetailsStream(int tenantID) throws EmailMonitorServiceException;

    /**
     *
     * @param tenantID
     * @return
     * @throws EmailMonitorServiceException
     */
    public boolean createEmailSenderOutputStream(int tenantID) throws EmailMonitorServiceException;

    /**
     *
     * @param tenantID
     * @return
     * @throws EmailMonitorServiceException
     */
    public boolean createFilteredEmailDetailsStream(int tenantID) throws EmailMonitorServiceException;

    /**
     * @param axisConfiguration
     * @return
     * @throws EmailMonitorServiceException
     */
    public boolean createSoapOutputAdapter(AxisConfiguration axisConfiguration) throws EmailMonitorServiceException;

    /**
     *
     * @param axisConfiguration
     * @return
     * @throws EmailMonitorServiceException
     */
    public boolean createEmailOutputAdapter(AxisConfiguration axisConfiguration) throws EmailMonitorServiceException;

    /**
     *
     * @param ESBServerIP
     * @param ESBServerPort
     * @param ESBServerUsername
     * @param ESBServerPassword
     * @param axisConfiguration
     * @return
     * @throws EmailMonitorServiceException
     */

    public boolean createGmailOutStreamEventFormatter(String ESBServerIP, String ESBServerPort, String ESBServerUsername, String ESBServerPassword, AxisConfiguration axisConfiguration)throws EmailMonitorServiceException;;

    /**
     *
     * @param mailAddress
     * @param axisConfiguration
     * @return
     * @throws EmailMonitorServiceException
     */
    public boolean createEmailSenderOutputStreamFormatter(String mailAddress,AxisConfiguration axisConfiguration) throws EmailMonitorServiceException ;

    /**
     *
     * @param resourceString
     * @param resourcePath
     * @return
     */
    public boolean saveResourceString(String resourceString, String resourcePath);

    /**
     *
     * @param path
     * @return
     */
    public boolean resourceAlreadyExists(String path);

    /**
     *
     * @param path
     * @return
     */
    public boolean removeResource(String path) ;

    /**
     *
     * @param path
     * @return
     */
    public String getResourceString(String path);

    /**
     *
     * @param collectionPath
     * @return
     */
    public boolean addCollection(String collectionPath);

    /**
     *
     * @param emailMonitorCollectionLocation
     * @return
     */
    public String[] getEmailMonitorResources(String emailMonitorCollectionLocation);


    }





