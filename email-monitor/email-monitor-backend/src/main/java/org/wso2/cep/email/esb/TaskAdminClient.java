package org.wso2.cep.email.esb;



import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.util.AXIOMUtil;
import org.apache.axis2.AxisFault;
import org.apache.log4j.Logger;
import org.wso2.carbon.task.stub.TaskAdminStub;
import org.wso2.carbon.task.stub.TaskManagementException;
import org.wso2.carbon.utils.CarbonUtils;
import org.wso2.cep.email.esb.util.SecurityConstants;
import org.wso2.cep.util.EmailMonitorConstants;

import javax.xml.stream.XMLStreamException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.rmi.RemoteException;

public class TaskAdminClient {

    private static Logger logger = Logger.getLogger(TaskAdminClient.class);
    private TaskAdminStub stub;


    public TaskAdminClient(String ip, String port) {
        String endPoint = EmailMonitorConstants.PROTOCOL + ip + ":" + port + EmailMonitorConstants.SERVICES + EmailMonitorConstants.TASK_ADMIN_SERVICE;

         try {
            stub = new TaskAdminStub(endPoint);
        } catch (AxisFault axisFault) {
            logger.error(axisFault.getMessage());
        }


    }

    public void addScheduledTask(String userName, String password)  {
        CarbonUtils.setBasicAccessSecurityHeaders(userName, password, stub._getServiceClient());

        String content = "";

       InputStream is = null;
        BufferedReader br = null;
        String line;

        is = ProxyAdminClient.class.getResourceAsStream(EmailMonitorConstants.TASK_CONFIGURATION_FILE_PATH);
        br = new BufferedReader(new InputStreamReader(is));
        try {
            while (null != (line = br.readLine())) {
                content = content + line;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        OMElement omElementTask = null;
        try {
            omElementTask = AXIOMUtil.stringToOM(content);
        } catch (XMLStreamException e) {
           logger.error(e.getMessage());
        }

        try {
            stub.addTaskDescription(omElementTask);
        } catch (RemoteException e) {
            logger.error(e.getMessage());
        } catch (TaskManagementException e) {
           logger.error(e.getMessage());
        }

    }
}
