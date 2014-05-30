package org.wso2.carbon.gmail.mediator;

import org.apache.axiom.om.*;
import org.apache.axiom.soap.SOAPBody;
import org.apache.synapse.MessageContext;
import org.apache.synapse.mediators.AbstractMediator;
import org.apache.synapse.util.xpath.SynapseXPath;
import org.jaxen.JaxenException;
import org.wso2.carbon.gmail.mediator.util.MailSessionInfoStore;
import org.wso2.carbon.gmail.mediator.util.MediatorConstants;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;


public class MessageFilter extends AbstractMediator {


    public boolean mediate(MessageContext messageContext) {
        int messageCount = MediatorConstants.DEFAULT_VALUE_ZERO;
        MailSessionInfoStore.incrementBundleCount();
        SOAPBody soapBody = messageContext.getEnvelope().getBody();
        OMElement mailOM = soapBody.getFirstElement();
        OMElement mailfirst = mailOM.getFirstElement();
        OMFactory omfactory = OMAbstractFactory.getOMFactory();
        OMElement selectedMails = omfactory.createOMElement(MediatorConstants.MESSAGES, MediatorConstants.GMAIL_NAME_SPACE_URL, MediatorConstants.NAME_SPACE_PREFIX);

        Iterator iterator = mailfirst.getChildElements();
        while (iterator.hasNext()) {
            OMElement omelement = (OMElement) iterator.next();
            long timeS = getTimeStamp(omelement);
            messageCount++;
            if (MailSessionInfoStore.getBundleCount() == MediatorConstants.MESSAGE_COUNT_STARTING_VALUE && messageCount == MediatorConstants.MESSAGE_COUNT_STARTING_VALUE) {
                MailSessionInfoStore.setPreviouseBundleFirstMailTS(timeS);
                selectedMails.addChild(omelement);

            } else if (MailSessionInfoStore.getBundleCount() > MediatorConstants.MESSAGE_COUNT_STARTING_VALUE) {
                if (MailSessionInfoStore.getBundleCount() > MediatorConstants.MESSAGE_COUNT_STARTING_VALUE) {
                    if (timeS <= MailSessionInfoStore.getPreviouseBundleFirstMailTS()) {
                        MailSessionInfoStore.setPreviouseMessageTS(timeS);
                    } else {
                        selectedMails.addChild(omelement);
                    }


                    if (MailSessionInfoStore.getPreviouseMessageTS() != MediatorConstants.DEFAULT_VALUE_ZERO && MailSessionInfoStore.getPreviouseMessageTS() < timeS) {
                        MailSessionInfoStore.setPreviouseBundleFirstMailTS(MailSessionInfoStore.getNextBundleFirstMailTs());
                    }
                }
                if (messageCount == MediatorConstants.MESSAGE_COUNT_STARTING_VALUE) {
                    MailSessionInfoStore.setNextBundleFirstMailTs(timeS);
                }

            } else {
                MailSessionInfoStore.setPreviouseMessageTS(timeS);
                selectedMails.addChild(omelement);
            }

        }
        mailfirst.detach();
        mailOM.addChild(selectedMails);
        return true;
    }

    private long getTimeStamp(OMElement mailOM) {
        mailOM.build();
        mailOM.detach();
        long timstamp = MediatorConstants.DEFAULT_VALUE_ZERO;
        try {
            SynapseXPath synapseXPath = new SynapseXPath(MediatorConstants.XPATH_SEND_DATE);
            OMFactory omFactory = OMAbstractFactory.getOMFactory();
            OMNamespace omNamespace = omFactory.createOMNamespace(MediatorConstants.GMAIL_NAME_SPACE_URL, MediatorConstants.NAME_SPACE_PREFIX);
            synapseXPath.addNamespace(omNamespace);
            String result = (String) synapseXPath.stringValueOf(mailOM);
            SimpleDateFormat sdf = new SimpleDateFormat(MediatorConstants.DATE_FORMAT);
            try {
                Date date = sdf.parse(result);
                Calendar c = Calendar.getInstance();
                c.setTime(date);
                timstamp = c.getTimeInMillis();
            } catch (ParseException e) {
                log.error(e.getMessage());
            }
        } catch (JaxenException e) {
            log.error(e.toString());
        }
        return timstamp;
    }


}