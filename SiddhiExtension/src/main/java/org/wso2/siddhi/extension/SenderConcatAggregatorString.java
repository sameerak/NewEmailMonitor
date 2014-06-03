package org.wso2.siddhi.extension;

import org.wso2.siddhi.core.query.selector.attribute.handler.OutputAttributeAggregator;
import org.wso2.siddhi.query.api.definition.Attribute.Type;
import java.text.DecimalFormat;

public class SenderConcatAggregatorString implements OutputAttributeAggregator {
    private static final long serialVersionUID = 1358667438272544590L;
    private String aggregatedSender = "";

    @Override
    public Type getReturnType() {
        return Type.STRING;
    }


    @Override
    public Object processAdd(Object obj) {

        if (obj instanceof String) {

            String sender = (String) obj;
            aggregatedSender = aggregatedSender+sender;
        }
        return aggregatedSender;
    }


    @Override
    public Object processRemove(Object obj) {

        if (obj instanceof String) {

            String sender = (String)obj;
            aggregatedSender = aggregatedSender.replace(sender, "");
        }
        return aggregatedSender;
    }


    @Override
    public OutputAttributeAggregator newInstance() {
        return new SenderConcatAggregatorString();
    }


    @Override
    public void destroy() {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}