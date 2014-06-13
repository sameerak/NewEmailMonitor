package org.wso2.cep.email.monitor.query.api.conditions;


import org.wso2.cep.email.monitor.query.api.attribute.set.EmailAddressSet;

public class FromCondition extends Condition {


    public FromCondition() {

    }


    @Override
    public String toString() {
        return "senders = " +getEmailAddressSet().toString();
    }
}
