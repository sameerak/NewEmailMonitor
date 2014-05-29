package org.wso2.cep.config;


import org.apache.log4j.Logger;
import org.wso2.carbon.event.stream.manager.stub.EventStreamAdminServiceStub;
import org.wso2.carbon.event.stream.manager.stub.types.EventStreamAttributeDto;

import java.rmi.RemoteException;

public class StreamAdminClient {

    private static Logger logger = Logger.getLogger(StreamAdminClient.class);
    private EventStreamAdminServiceStub stub;


    public void createMailInputStream(){

        EventStreamAttributeDto metaEventStreamAttributeHttpMethod = new EventStreamAttributeDto();
        metaEventStreamAttributeHttpMethod.setAttributeName("tenant_id");
        metaEventStreamAttributeHttpMethod.setAttributeType("int");

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

        EventStreamAttributeDto[] metaAttributes = new EventStreamAttributeDto[]{metaHttpMethod, metaHttpMethod,metaCharacterSet,metaRemoteAddress,metaTransportIn,metaMessageType,metaRemoteHost,metaServicePrefix};

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

//        EventStreamAttributeDto[] payloadEventStreamAttributeDtos = new EventStreamAttributeDto[]{payloadEventStreamAttributeDto1, payloadEventStreamAttributeDto2};

//        try {
//            stub.addEventStreamInfo("gmailInputStream", "1.0.0", metaAttributes, null, payloadEventStreamAttributeDtos, "email information stream", "gmail");
//        } catch (RemoteException e) {
//            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
//        }

    }



}
//        {
//        "name": "message_id",
//        "type": "STRING"
//        },
//        {
//        "name": "timestamp",
//        "type": "LONG"
//        },
//        {
//        "name": "messagerID",
//        "type": "LONG"
//        },
//        {
//        "name": "soap_header",
//        "type": "STRING"
//        },
//        {
//        "name": "soap_body",
//        "type": "STRING"
//        }
//        ]
//        }

//        "correlationData": [
//        {
//        "name": "activity_id",
//        "type": "STRING"
//        }
//        ],
//        "payloadData": [
//        {
//        "name": "message_direction",
//        "type": "STRING"
//        },
//        {
//        "name": "service_name",
//        "type": "STRING"
//        },
//        {
//        "name": "operation_name",
//        "type": "STRING"
//        },
//        {
//        "name": "message_id",
//        "type": "STRING"
//        },
//        {
//        "name": "timestamp",
//        "type": "LONG"
//        },
//        {
//        "name": "messagerID",
//        "type": "LONG"
//        },
//        {
//        "name": "soap_header",
//        "type": "STRING"
//        },
//        {
//        "name": "soap_body",
//        "type": "STRING"
//        }
//        ]
//        }