package org.wso2.cep.email.monitor.internal.config;


import org.apache.axis2.AxisFault;
import org.apache.axis2.client.Options;
import org.apache.axis2.client.ServiceClient;
import org.apache.axis2.context.ConfigurationContext;
import org.apache.log4j.Logger;
import org.wso2.carbon.event.stream.manager.stub.EventStreamAdminServiceStub;
import org.wso2.carbon.event.stream.manager.stub.types.EventStreamAttributeDto;

import org.wso2.cep.email.monitor.exception.EmailMonitorServiceException;

import org.wso2.carbon.proxyadmin.stub.ProxyServiceAdminStub;
import org.wso2.carbon.utils.CarbonUtils;
import org.wso2.cep.email.monitor.internal.config.esb.config.util.SecurityConstants;
import org.wso2.cep.email.monitor.internal.util.EmailMonitorConstants;


import java.rmi.RemoteException;

public class StreamDeployer {

    private static Logger logger = Logger.getLogger(StreamDeployer.class);
    private static EventStreamAdminServiceStub eventStreamAdminServiceStub;

    public StreamDeployer(String cookie, String backendServerURL,

                          ConfigurationContext configCtx) throws EmailMonitorServiceException {
        String endPoint = backendServerURL + EmailMonitorConstants.EVENT_STREAM_ADMIN_SERVICE;


        try {
            eventStreamAdminServiceStub = new EventStreamAdminServiceStub(configCtx, endPoint);
        } catch (AxisFault axisFault) {
            logger.error(axisFault.getMessage());
            throw new EmailMonitorServiceException("Error When creating StreamDeployer", axisFault);
        }
        ServiceClient client = eventStreamAdminServiceStub._getServiceClient();
        Options option = client.getOptions();
        option.setManageSession(true);
        option.setProperty(org.apache.axis2.transport.http.HTTPConstants.COOKIE_STRING, cookie);

    }



    public void createMailInputStream() throws EmailMonitorServiceException {


        EventStreamAttributeDto metaTenantID = new EventStreamAttributeDto();
        metaTenantID.setAttributeName("tenant_id");
        metaTenantID.setAttributeType("int");

        EventStreamAttributeDto metaHttpMethod = new EventStreamAttributeDto();
        metaHttpMethod.setAttributeName("http_method");
        metaHttpMethod.setAttributeType("string");

        EventStreamAttributeDto metaCharacterSet = new EventStreamAttributeDto();
        metaCharacterSet.setAttributeName("character_set_encoding");
        metaCharacterSet.setAttributeType("string");

        EventStreamAttributeDto metaRemoteAddress = new EventStreamAttributeDto();
        metaRemoteAddress.setAttributeName("remote_address");
        metaRemoteAddress.setAttributeType("string");

        EventStreamAttributeDto metaTransportIn = new EventStreamAttributeDto();
        metaTransportIn.setAttributeName("transport_in_url");
        metaTransportIn.setAttributeType("string");

        EventStreamAttributeDto metaMessageType = new EventStreamAttributeDto();
        metaMessageType.setAttributeName("message_type");
        metaMessageType.setAttributeType("string");

        EventStreamAttributeDto metaRemoteHost = new EventStreamAttributeDto();
        metaRemoteHost.setAttributeName("remote_host");
        metaRemoteHost.setAttributeType("string");

        EventStreamAttributeDto metaServicePrefix = new EventStreamAttributeDto();
        metaServicePrefix.setAttributeName("service_prefix");
        metaServicePrefix.setAttributeType("string");

        EventStreamAttributeDto metaHost = new EventStreamAttributeDto();
        metaHost.setAttributeName("host");
        metaHost.setAttributeType("string");

        EventStreamAttributeDto[] metaAttributes = new EventStreamAttributeDto[]{metaTenantID, metaHttpMethod, metaCharacterSet, metaRemoteAddress, metaTransportIn, metaMessageType, metaRemoteHost, metaServicePrefix, metaHost};

        EventStreamAttributeDto correlationActivityID = new EventStreamAttributeDto();
        correlationActivityID.setAttributeName("activity_id");
        correlationActivityID.setAttributeType("string");

        EventStreamAttributeDto[] correlationAttributes = new EventStreamAttributeDto[]{correlationActivityID};

        EventStreamAttributeDto payloadMessageDirection = new EventStreamAttributeDto();
        payloadMessageDirection.setAttributeName("message_direction");
        payloadMessageDirection.setAttributeType("string");

        EventStreamAttributeDto payloadServiceName = new EventStreamAttributeDto();
        payloadServiceName.setAttributeName("service_name");
        payloadServiceName.setAttributeType("string");

        EventStreamAttributeDto payloadOperationName = new EventStreamAttributeDto();
        payloadOperationName.setAttributeName("operation_name");
        payloadOperationName.setAttributeType("string");

        EventStreamAttributeDto payloadMessageID = new EventStreamAttributeDto();
        payloadMessageID.setAttributeName("message_id");
        payloadMessageID.setAttributeType("string");

        EventStreamAttributeDto payloadTimestamp = new EventStreamAttributeDto();
        payloadTimestamp.setAttributeName("timestamp");
        payloadTimestamp.setAttributeType("long");

        EventStreamAttributeDto payloadEmailMessageID = new EventStreamAttributeDto();
        payloadEmailMessageID.setAttributeName("messageID");
        payloadEmailMessageID.setAttributeType("long");

        EventStreamAttributeDto payloadSubject = new EventStreamAttributeDto();
        payloadSubject.setAttributeName("subject");
        payloadSubject.setAttributeType("string");

        EventStreamAttributeDto payloadFrom = new EventStreamAttributeDto();
        payloadFrom.setAttributeName("from");
        payloadFrom.setAttributeType("string");

        EventStreamAttributeDto payloadTo = new EventStreamAttributeDto();
        payloadTo.setAttributeName("to");
        payloadTo.setAttributeType("string");

        EventStreamAttributeDto payloadSentDate = new EventStreamAttributeDto();
        payloadSentDate.setAttributeName("sentDate");
        payloadSentDate.setAttributeType("string");

        EventStreamAttributeDto payloadThreadID = new EventStreamAttributeDto();
        payloadThreadID.setAttributeName("threadID");
        payloadThreadID.setAttributeType("long");

        EventStreamAttributeDto payloadStatus = new EventStreamAttributeDto();
        payloadStatus.setAttributeName("status");
        payloadStatus.setAttributeType("string");

        EventStreamAttributeDto payloadContent = new EventStreamAttributeDto();
        payloadContent.setAttributeName("content");
        payloadContent.setAttributeType("string");


        EventStreamAttributeDto payloadLabels = new EventStreamAttributeDto();
        payloadLabels.setAttributeName("labels");
        payloadLabels.setAttributeType("string");

        EventStreamAttributeDto payloadSoapHeader = new EventStreamAttributeDto();
        payloadSoapHeader.setAttributeName("soap_header");
        payloadSoapHeader.setAttributeType("string");

        EventStreamAttributeDto payloadSoapBody = new EventStreamAttributeDto();
        payloadSoapBody.setAttributeName("soap_body");
        payloadSoapBody.setAttributeType("string");





        EventStreamAttributeDto[] payloadAttributes = new EventStreamAttributeDto[]{payloadMessageDirection, payloadServiceName, payloadOperationName,
                payloadMessageID, payloadTimestamp, payloadEmailMessageID, payloadSubject, payloadFrom, payloadTo, payloadSentDate, payloadThreadID, payloadStatus, payloadContent, payloadLabels, payloadSoapHeader, payloadSoapBody};

        try {
            eventStreamAdminServiceStub.addEventStreamInfo("gmailInputStream", "1.0.0", metaAttributes, correlationAttributes, payloadAttributes, "email information stream", "gmail");

        } catch (RemoteException e) {
            logger.error(e.getMessage());
            throw new EmailMonitorServiceException("Error when creating mailInputStream", e);
        }


    }




    public void createMailOutputStream() {
        EventStreamAttributeDto payloadThreadID = new EventStreamAttributeDto();
        payloadThreadID.setAttributeName("threadID");
        payloadThreadID.setAttributeType("long");

        EventStreamAttributeDto payloadLabel = new EventStreamAttributeDto();
        payloadLabel.setAttributeName("label");
        payloadLabel.setAttributeType("string");


        EventStreamAttributeDto[] payloadAttributes = new EventStreamAttributeDto[]{payloadThreadID, payloadLabel};

        try {
            eventStreamAdminServiceStub.addEventStreamInfo("gmailOutputStream", "1.0.0", null, null, payloadAttributes, "analyzed email stream", "gmail_output");

        } catch (RemoteException e) {
            logger.error(e.getMessage());
        }



    }




    }

