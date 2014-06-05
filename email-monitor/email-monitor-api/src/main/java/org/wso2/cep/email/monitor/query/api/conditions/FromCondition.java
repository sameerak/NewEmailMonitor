package org.wso2.cep.email.monitor.query.api.conditions;


import org.wso2.cep.email.monitor.query.api.attribute.set.EmailAddressSet;

public class FromCondition extends Condition {
    private EmailAddressSet emailAddressSet;


    public FromCondition() {

    }

    public FromCondition(EmailAddressSet emailAddressSet) {
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
        return "from = " + emailAddressSet.toString();
    }
}
