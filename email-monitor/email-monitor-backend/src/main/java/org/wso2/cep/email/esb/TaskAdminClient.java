package org.wso2.cep.email.esb;



import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.util.AXIOMUtil;
import org.apache.axis2.AxisFault;
import org.apache.log4j.Logger;
import org.wso2.carbon.task.stub.TaskAdminStub;
import org.wso2.carbon.task.stub.TaskManagementException;
import org.wso2.carbon.utils.CarbonUtils;
import org.wso2.cep.email.esb.util.SecurityConstants;

import javax.xml.stream.XMLStreamException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.rmi.RemoteException;

public class TaskAdminClient {

    private static final String TASK_ADMIN_SERVICE = "TaskAdmin";
    private static Logger logger = Logger.getLogger(TaskAdminClient.class);
    private TaskAdminStub stub;


    public TaskAdminClient(String ip, String port) {
        String endPoint = "https://" + ip + ":" + port + "/services/" + TASK_ADMIN_SERVICE;

        // Set client trust store
        System.setProperty(SecurityConstants.TRUSTSTORE, SecurityConstants.CLIENT_TRUST_STORE_PATH);
        System.setProperty(SecurityConstants.TRUSTSTORE_PASSWORD, SecurityConstants.KEY_STORE_PASSWORD);
        System.setProperty(SecurityConstants.TRUSTSTORE_TYPE, SecurityConstants.KEY_STORE_TYPE);


        try {
            stub = new TaskAdminStub(endPoint);
        } catch (AxisFault axisFault) {
            logger.error(axisFault.getMessage());
        }


    }

    public void addScheduledTask(String userName, String password) throws XMLStreamException, TaskManagementException, RemoteException {
        CarbonUtils.setBasicAccessSecurityHeaders(userName, password, stub._getServiceClient());

        String content = "";

        /*
        Need to read a config file bundled in the jar file
        to get the configuration needed to create the ESB task configuration
        Source - http://www.coderanch.com/t/329156/java/java/Read-File-jar-file
         */
        InputStream is = null;
        BufferedReader br = null;
        String line;

        is = ProxyAdminClient.class.getResourceAsStream("/config/taskConfig.xml");
        br = new BufferedReader(new InputStreamReader(is));
        try {
            while (null != (line = br.readLine())) {
                content = content + line;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        //TODO add content variable instead of String added to the stub call and check the functionality before proceeding to next stage

        OMElement omElementTask = AXIOMUtil.stringToOM("<task:task xmlns:task=\"http://www.wso2.org/products/wso2commons/tasks\" name=\"testset\" class=\"org.apache.synapse.startup.tasks.MessageInjector\" group=\"synapse.simple.quartz\"><task:trigger count=\"1\"></task:trigger><task:property name=\"soapAction\" value=\"root\"></task:property><task:property name=\"message\"><root xmlns=\"http://ws.apache.org/ns/synapse\" xmlns:urn=\"urn:wso2.connector.gmail.passwordauthentication\">\n" +
                "         <urn:username>synapse.demo.1@gmail.com</urn:username>\n" +
                "         <urn:password>mailpassword1</urn:password>\n" +
                "      </root></task:property><task:property name=\"proxyName\" value=\"gmail_passwordAuthentication\"></task:property><task:property name=\"injectTo\" value=\"proxy\"></task:property></task:task>");


        stub.addTaskDescription(omElementTask);

    }


    public static void main(String[] args) throws RemoteException, TaskManagementException, XMLStreamException {

        new TaskAdminClient("10.100.5.89", "9443").addScheduledTask("admin", "admin");
    }
}
