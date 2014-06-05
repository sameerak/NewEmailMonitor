package org.wso2.cep.email.monitor.query.api.conditions;


public abstract class ConditionAttribute {

    private ConditionAttribute left;
    private ConditionAttribute right;

    public ConditionAttribute getLeft() {
        return left;
    }

    public void setLeft(ConditionAttribute left) {
        this.left = left;
    }

    public ConditionAttribute getRight() {
        return right;
    }

    public void setRight(ConditionAttribute right) {
        this.right = right;
    }
}
