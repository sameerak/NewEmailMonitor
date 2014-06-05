package org.wso2.cep.email.monitor.query.api.conditions;


import org.wso2.cep.email.monitor.query.api.operators.Operator;
import org.wso2.cep.email.monitor.query.api.utils.Constants;

public class FrequencyCondition extends Condition {

    private String type;
    private Operator operator;

    public FrequencyCondition() {

    }

    public FrequencyCondition(String type, Operator operator) {
        this.type = type;
        this.operator = operator;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Operator getOperator() {
        return operator;
    }

    public void setOperator(Operator operator) {
        this.operator = operator;
    }


    @Override
    public String toString() {
        if (type.equals(Constants.THREAD)) {
            return "thread frequency per " + operator.toString();
        } else {
            return "frequency per " + operator.toString();
        }
    }
}
