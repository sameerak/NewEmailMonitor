package org.wso2.cep.email.monitor.query.api.attribute.set;


import org.wso2.cep.email.monitor.query.api.actions.Action;
import org.wso2.cep.email.monitor.query.api.attribute.Attribute;
import org.wso2.cep.email.monitor.query.api.conditions.ConditionAttribute;

public class LabelSet extends Attribute{




    public LabelSet() {
    }


    @Override
    public String toString() {
     return    getConditionAttribute().toString();
    }
}
