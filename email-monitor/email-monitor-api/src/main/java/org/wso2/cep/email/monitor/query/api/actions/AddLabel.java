package org.wso2.cep.email.monitor.query.api.actions;


public class AddLabel extends Action {

    private String to;

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    @Override
    public String toString() {
        return "add label " + to;
    }
}
