package org.wso2.cep.email.monitor.query.api.actions;


import org.wso2.cep.email.monitor.query.api.attribute.EmailAddress;

public class SendMail extends Action {

    private EmailAddress emailAddress;
    private String subject;
    private String body;

    public EmailAddress getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(EmailAddress emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "send mail (to " + emailAddress.toString() + " subject " + subject + " body" + body + ")";
    }
}
