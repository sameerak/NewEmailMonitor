package org.wso2.cep.email.monitor.query.api.attribute.set;


import org.wso2.cep.email.monitor.query.api.conditions.ConditionAttribute;

public class LabelSet {

    private ConditionAttribute conditionAttribute;


    public LabelSet() {
    }

    public LabelSet(ConditionAttribute conditionAttribute) {
        this.conditionAttribute = conditionAttribute;
    }

    public ConditionAttribute getConditionAttribute() {
        return conditionAttribute;
    }

    public void setConditionAttribute(ConditionAttribute conditionAttribute) {
        this.conditionAttribute = conditionAttribute;
    }

    @Override
    public String toString() {
        return conditionAttribute.toString();
    }
}
