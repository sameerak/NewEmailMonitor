package org.wso2.cep.email.monitor.config;


import org.apache.axis2.AxisFault;
import org.apache.axis2.client.Options;
import org.apache.axis2.client.ServiceClient;
import org.apache.axis2.context.ConfigurationContext;
import org.apache.log4j.Logger;
import org.wso2.carbon.event.stream.manager.stub.EventStreamAdminServiceStub;
import org.wso2.carbon.event.stream.manager.stub.types.EventStreamAttributeDto;

import java.rmi.RemoteException;

public class StreamDeployer {

    private static Logger logger = Logger.getLogger(StreamDeployer.class);
    private EventStreamAdminServiceStub eventStreamAdminServiceStub;

    public StreamDeployer(String cookie, String backendServerURL,
                          ConfigurationContext configCtx){

       String endPoint = backendServerURL + "EventStreamAdminService";


        try {
            eventStreamAdminServiceStub = new EventStreamAdminServiceStub(configCtx, endPoint);
        } catch (AxisFault axisFault) {
            logger.error(axisFault.getMessage());
        }
        ServiceClient client = eventStreamAdminServiceStub._getServiceClient();
        Options option = client.getOptions();
        option.setManageSession(true);
        option.setProperty(org.apache.axis2.transport.http.HTTPConstants.COOKIE_STRING, cookie);

    }


    public void createMailInputStream(){

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

        EventStreamAttributeDto[] metaAttributes = new EventStreamAttributeDto[]{metaTenantID,metaHttpMethod,metaCharacterSet,metaRemoteAddress,metaTransportIn,metaMessageType,metaRemoteHost,metaServicePrefix,metaHost};

        EventStreamAttributeDto correlationActivityID = new EventStreamAttributeDto();
        correlationActivityID.setAttributeName("activity_id");
        correlationActivityID.setAttributeType("string");

        EventStreamAttributeDto[] correlationAttributes = new EventStreamAttributeDto[] {correlationActivityID};

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
        payloadEmailMessageID.setAttributeName("messagerID");
        payloadEmailMessageID.setAttributeType("long");

        EventStreamAttributeDto payloadSoapHeader = new EventStreamAttributeDto();
        payloadSoapHeader.setAttributeName("soap_header");
        payloadSoapHeader.setAttributeType("string");

        EventStreamAttributeDto payloadSoapBody = new EventStreamAttributeDto();
        payloadSoapBody.setAttributeName("soap_body");
        payloadSoapBody.setAttributeType("string");


        EventStreamAttributeDto[] payloadAttributes = new EventStreamAttributeDto[]{payloadMessageDirection, payloadServiceName,payloadOperationName,payloadMessageID,payloadTimestamp,payloadEmailMessageID,payloadSoapHeader,payloadSoapBody};

        try {
            eventStreamAdminServiceStub.addEventStreamInfo("gmailInputStream", "1.0.0", metaAttributes, correlationAttributes, payloadAttributes, "email information stream", "gmail");

        } catch (RemoteException e) {
            logger.error(e.getMessage());
        }



    }





}