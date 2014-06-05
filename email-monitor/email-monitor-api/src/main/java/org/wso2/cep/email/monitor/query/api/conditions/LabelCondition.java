package org.wso2.cep.email.monitor.query.api.conditions;


import org.wso2.cep.email.monitor.query.api.attribute.set.LabelSet;

public class LabelCondition extends Condition {


    private LabelSet labelSet;

    public LabelCondition() {

    }

    public LabelCondition(LabelSet labelSet) {
        this.labelSet = labelSet;
    }

    public LabelSet getLabelSet() {
        return labelSet;
    }

    public void setLabelSet(LabelSet labelSet) {
        this.labelSet = labelSet;
    }

    @Override
    public String toString() {
        return "label = " + labelSet.toString();
    }

}
