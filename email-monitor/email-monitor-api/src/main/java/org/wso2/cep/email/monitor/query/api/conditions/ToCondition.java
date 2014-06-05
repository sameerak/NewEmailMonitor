package org.wso2.cep.email.monitor.query.api.conditions;


import org.wso2.cep.email.monitor.query.api.attribute.set.EmailAddressSet;

public class ToCondition extends Condition {

    private EmailAddressSet emailAddressSet;


    public ToCondition() {

    }

    public ToCondition(EmailAddressSet emailAddressSet) {
        this.emailAddressSet = emailAddressSet;
    }

    public EmailAddressSet getEmailAddressSet() {
        return emailAddressSet;
    }

    public void setEmailAddressSet(EmailAddressSet emailAddressSet) {
        this.emailAddressSet = emailAddressSet;
    }

    @Override
    public String toString() {
        return "to = " + emailAddressSet.toString();
    }
}
