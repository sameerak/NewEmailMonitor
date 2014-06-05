package org.wso2.cep.email.monitor.query.api.conditions;


public class AndCondition extends Condition {

    private ConditionAttribute left;
    private ConditionAttribute right;


    public AndCondition() {

    }

    public AndCondition(ConditionAttribute left, ConditionAttribute right) {
        this.left = left;
        this.right = right;
    }


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

    @Override
    public String toString() {
        return getLeft().toString() + "and" + getRight().toString();
    }
}
