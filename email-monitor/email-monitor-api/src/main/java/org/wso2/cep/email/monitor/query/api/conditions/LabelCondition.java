package org.wso2.cep.email.monitor.query.api.conditions;


import org.wso2.cep.email.monitor.query.api.attribute.set.LabelSet;

public class LabelCondition extends Condition {




    @Override
    public String toString() {
        return "label = " + getLabelSet().toString();
    }

}
