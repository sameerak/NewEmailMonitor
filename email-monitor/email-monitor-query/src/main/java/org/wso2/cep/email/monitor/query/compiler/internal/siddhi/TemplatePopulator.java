package org.wso2.cep.email.monitor.query.compiler.internal.siddhi;


import org.apache.log4j.Logger;
import org.wso2.cep.email.monitor.query.api.Query;
import org.wso2.cep.email.monitor.query.api.actions.Action;
import org.wso2.cep.email.monitor.query.api.actions.AddLabel;
import org.wso2.cep.email.monitor.query.api.actions.SendMail;
import org.wso2.cep.email.monitor.query.api.attribute.EmailAddress;
import org.wso2.cep.email.monitor.query.api.attribute.Label;
import org.wso2.cep.email.monitor.query.api.attribute.set.EmailAddressSet;
import org.wso2.cep.email.monitor.query.api.attribute.set.LabelSet;
import org.wso2.cep.email.monitor.query.api.conditions.*;
import org.wso2.cep.email.monitor.query.api.expressions.CompareVal;
import org.wso2.cep.email.monitor.query.api.expressions.TimeExpr;
import org.wso2.cep.email.monitor.query.api.operators.*;
import org.wso2.cep.email.monitor.query.api.utils.Constants;


public class TemplatePopulator {
    private static Logger logger = Logger.getLogger(TemplatePopulator.class);


    public static SiddhiTemplate convert(Query query) {
        logger.info("populating siddhi template");
        SiddhiTemplate siddhiTemplate = new SiddhiTemplate();
        Action action = query.getAction();
        convertAction(action, siddhiTemplate);
        ConditionAttribute conditionAttribute = query.getConditionAttribute();
        convertCondition(conditionAttribute, siddhiTemplate);
        return siddhiTemplate;
    }

    public static SiddhiTemplate convertAction(Action action, SiddhiTemplate siddhiTemplate) {
        if (action != null && action instanceof AddLabel) {
            AddLabel addLabel = (AddLabel) action;
            siddhiTemplate.setSendMailEnabled(false);
            siddhiTemplate.setLabelName(addLabel.getLabel());
            siddhiTemplate.setCmpAction(Constants.APPLY_LABEL);
        } else if (action != null && action instanceof SendMail) {
            SendMail sendMail = (SendMail) action;
            siddhiTemplate.setSendMailEnabled(true);
            siddhiTemplate.setCmpAction(Constants.SEND_MAIL);
        } else {
        }
        return siddhiTemplate;
    }

    public static SiddhiTemplate convertCondition(ConditionAttribute conditionAttribute, SiddhiTemplate siddhiTemplate) {
        if (conditionAttribute instanceof FrequencyCondition) {
            siddhiTemplate.setFreqEnabled(true);
            convertFrequencyCondition((FrequencyCondition) conditionAttribute, siddhiTemplate);
        } else if (conditionAttribute instanceof AndCondition && conditionAttribute.getRight() instanceof FrequencyCondition) {
            siddhiTemplate.setFromFrequency("and");
            siddhiTemplate.setFreqEnabled(true);
            AndCondition andCondition = (AndCondition) conditionAttribute;
            convertLabelFromToCondition(andCondition.getLeft(), siddhiTemplate);
            convertFrequencyCondition((FrequencyCondition) andCondition.getRight(), siddhiTemplate);
        } else if (conditionAttribute instanceof ORCondition && conditionAttribute.getRight() instanceof FrequencyCondition) {
            siddhiTemplate.setFromFrequency("or");
            siddhiTemplate.setFreqEnabled(true);
            ORCondition orCondition = (ORCondition) conditionAttribute;
            convertLabelFromToCondition(orCondition.getLeft(), siddhiTemplate);
            convertFrequencyCondition((FrequencyCondition) orCondition.getRight(), siddhiTemplate);
        } else {
            convertLabelFromToCondition(conditionAttribute, siddhiTemplate);
        }
        return siddhiTemplate;
    }


    public static SiddhiTemplate convertFrequencyCondition(FrequencyCondition conditionAttribute, SiddhiTemplate siddhiTemplate) {
        if (Constants.THREAD == conditionAttribute.getType()) {
            siddhiTemplate.setThreadFre(true);
        } else {
            siddhiTemplate.setThreadFre(false);
        }
        if(Constants.COUNT == conditionAttribute.getType()){
            siddhiTemplate.setLabelCount(true);
            siddhiTemplate.setThreadFre(false);
        }else{
            siddhiTemplate.setLabelCount(false);
        }
        Operator operator = conditionAttribute.getOperator();
        if (operator instanceof EqualOP) {
            siddhiTemplate.setCmpAction(Constants.EQUAL);
        } else if (operator instanceof GreaterThanOP) {
            siddhiTemplate.setCmpAction(Constants.GREATERTHAN);
        } else if (operator instanceof GreaterThanOREqualOP) {
            siddhiTemplate.setCmpAction(Constants.GREATAERTHANOREQUAL);
        } else if (operator instanceof LessThanOP) {
            siddhiTemplate.setCmpAction(Constants.LESSTHAN);
        } else if (operator instanceof LessThanOREqualOP) {
            siddhiTemplate.setCmpAction(Constants.LESSTHANOREQUAL);
        } else if (operator instanceof NOTEqualOP) {
            siddhiTemplate.setCmpAction(Constants.NOTEQUAL);
        }
        siddhiTemplate.setCountValue(((CompareVal) operator.getRight()).getValue());
        TimeExpr timeExpr = ((TimeExpr) operator.getLeft());
        if(timeExpr!=null) {
            siddhiTemplate.setTimeExpr(timeExpr.toString());
        }
        return siddhiTemplate;
    }

    public static String convertEmailAddressSet(ConditionAttribute emailAddressSet, String key) {
        StringBuffer bf = new StringBuffer();
        if (emailAddressSet instanceof EmailAddressSet) {
            EmailAddressSet emailAddressSet1 = (EmailAddressSet) emailAddressSet;
            bf.append(key + " " + "contains ");
            bf.append('"');
            bf.append(((EmailAddress) emailAddressSet1.getConditionAttribute()).toString());
            bf.append('"');
        } else if (emailAddressSet instanceof AndCondition) {
            AndCondition andCondition = (AndCondition) emailAddressSet;
            EmailAddress emailAddress = (EmailAddress) andCondition.getLeft();
            bf.append(key + " " + "contains ");
            bf.append('"');
            bf.append(emailAddress.toString());
            bf.append('"');
            bf.append(" and ");
            ConditionAttribute emailAddressSet1 = andCondition.getRight();
            String result = convertEmailAddressSet(emailAddressSet1, key);
            bf.append(result);

        } else if (emailAddressSet instanceof ORCondition) {
            ORCondition orCondition = (ORCondition) emailAddressSet;
            EmailAddress emailAddress = (EmailAddress) orCondition.getLeft();
            bf.append(key + " " + "contains ");
            bf.append('"');
            bf.append(emailAddress.toString());
            bf.append('"');
            bf.append(" or ");
            ConditionAttribute emailAddressSet1 = orCondition.getRight();
            String result = convertEmailAddressSet(emailAddressSet1, key);
            bf.append(result);
        }
        return bf.toString();
    }

    public static String convertLabelSet(ConditionAttribute labelSet, SiddhiTemplate siddhiTemplate) {
        StringBuffer bf = new StringBuffer();
        if (labelSet instanceof LabelSet) {
            LabelSet labelSet1 = (LabelSet) labelSet;
            bf.append("labels contains ");
            bf.append('"');
            bf.append(((Label) labelSet1.getConditionAttribute()).toString());
            bf.append('"');


        } else if (labelSet instanceof AndCondition) {
            AndCondition andCondition = (AndCondition) labelSet;
            Label lb = (Label) andCondition.getLeft();
            bf.append("labels contains ");
            bf.append('"');
            bf.append(lb.toString());
            bf.append('"');
            bf.append(" and ");
            ConditionAttribute lbSet = andCondition.getRight();
            String result = convertLabelSet(lbSet, siddhiTemplate);
            bf.append(result);

        } else if (labelSet instanceof ORCondition) {
            ORCondition orCondition = (ORCondition) labelSet;
            Label lb = (Label) orCondition.getLeft();
            bf.append("labels contains ");
            bf.append('"');
            bf.append(lb.toString());
            bf.append('"');
            bf.append(" or ");
            ConditionAttribute lbSet = orCondition.getRight();
            String result = convertLabelSet(lbSet, siddhiTemplate);
            bf.append(result);
        }
        return bf.toString();
    }

    public static SiddhiTemplate convertToCondition(ConditionAttribute conditionAttribute, SiddhiTemplate siddhiTemplate) {
        if (conditionAttribute instanceof ToCondition) {
            ToCondition toCondition = (ToCondition) conditionAttribute;
            ConditionAttribute conditionAttribute1 = toCondition.getEmailAddressSet();
            String result = convertEmailAddressSet(conditionAttribute1, "to");
            siddhiTemplate.setToMails(result);
        }
        return siddhiTemplate;
    }

    public static SiddhiTemplate convertFromCondition(ConditionAttribute conditionAttribute, SiddhiTemplate siddhiTemplate) {
        if (conditionAttribute instanceof FromCondition) {
            FromCondition fromCondition = (FromCondition) conditionAttribute;
            ConditionAttribute conditionAttribute1 = fromCondition.getEmailAddressSet();
            String result = convertEmailAddressSet(conditionAttribute1, "from");
            siddhiTemplate.setFromMails(result);
        }
        return siddhiTemplate;
    }

    public static SiddhiTemplate convertLabelCondition(ConditionAttribute conditionAttribute, SiddhiTemplate siddhiTemplate) {
        if (conditionAttribute instanceof LabelCondition) {
            LabelCondition labelCondition = (LabelCondition) conditionAttribute;
            ConditionAttribute conditionAttribute1 = labelCondition.getLabelSet();
            String result = convertLabelSet(conditionAttribute1, siddhiTemplate);
            siddhiTemplate.setLabelMails(result);
        }
        return siddhiTemplate;
    }


    public static SiddhiTemplate convertLabelToCondition(ConditionAttribute conditionAttribute, SiddhiTemplate siddhiTemplate) {
        if (conditionAttribute instanceof LabelCondition) {
            convertLabelCondition(conditionAttribute, siddhiTemplate);
        } else if (conditionAttribute instanceof AndCondition) {
            siddhiTemplate.setTolabel("and");
            AndCondition andCondition = (AndCondition) conditionAttribute;
            convertToCondition(andCondition.getLeft(), siddhiTemplate);
            convertLabelCondition(andCondition.getRight(), siddhiTemplate);
        } else if (conditionAttribute instanceof ORCondition) {
            siddhiTemplate.setTolabel("or");
            ORCondition orCondition = (ORCondition) conditionAttribute;
            convertToCondition(orCondition.getLeft(), siddhiTemplate);
            convertLabelCondition(orCondition.getRight(), siddhiTemplate);
        } else if (conditionAttribute instanceof ToCondition) {
            convertToCondition(conditionAttribute, siddhiTemplate);
        }
        return siddhiTemplate;
    }


    public static SiddhiTemplate convertLabelFromToCondition(ConditionAttribute conditionAttribute, SiddhiTemplate siddhiTemplate) {
        if (conditionAttribute instanceof FromCondition) {
            convertFromCondition(conditionAttribute, siddhiTemplate);
        } else if (conditionAttribute instanceof AndCondition && conditionAttribute.getRight() instanceof FromCondition) {
            siddhiTemplate.setLabelFrom("and");
            AndCondition andCondition = (AndCondition) conditionAttribute;
            convertLabelToCondition(andCondition.getLeft(), siddhiTemplate);
            convertFromCondition(andCondition.getRight(), siddhiTemplate);
        } else if (conditionAttribute instanceof ORCondition && conditionAttribute.getRight() instanceof FromCondition) {
            siddhiTemplate.setLabelFrom("or");
            ORCondition orCondition = (ORCondition) conditionAttribute;
            convertLabelToCondition(orCondition.getLeft(), siddhiTemplate);
            convertFromCondition(orCondition.getRight(), siddhiTemplate);
        } else {
            convertLabelToCondition(conditionAttribute, siddhiTemplate);
        }
        return siddhiTemplate;
    }


}


