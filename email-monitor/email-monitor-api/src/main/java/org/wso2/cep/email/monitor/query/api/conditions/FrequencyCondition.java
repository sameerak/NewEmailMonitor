package org.wso2.cep.email.monitor.query.api.conditions;


import org.wso2.cep.email.monitor.query.api.operators.Operator;
import org.wso2.cep.email.monitor.query.api.utils.Constants;

public class FrequencyCondition extends Condition {



    public FrequencyCondition() {

    }

    public FrequencyCondition(String type, Operator operator) {
       setType(type);
        setOperator(operator);
    }




    @Override
    public String toString() {
        if (getType().equals(Constants.THREAD)) {
            return "thread frequency per " + getOperator().toString();
        } else {
            return "frequency per " + getOperator().toString();
        }
    }
}
