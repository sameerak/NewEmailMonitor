package org.wso2.cep.email.monitor.query.api.conditions;


import org.wso2.cep.email.monitor.query.api.attribute.set.EmailAddressSet;

public class ToCondition extends Condition {




    public ToCondition() {

    }



    @Override
    public String toString() {
        return "to = " + getEmailAddressSet().toString();
    }
}
