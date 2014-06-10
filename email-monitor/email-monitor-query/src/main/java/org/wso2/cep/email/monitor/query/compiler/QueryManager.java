package org.wso2.cep.email.monitor.query.compiler;


import org.wso2.cep.email.monitor.query.api.Query;
import org.wso2.cep.email.monitor.query.compiler.exeception.EmailMonitorCompilerException;
import org.wso2.cep.email.monitor.query.compiler.siddhi.SiddhiQueryWriter;

public class QueryManager {


    public String[] getSiddhiQuery(String query) throws EmailMonitorCompilerException {

        Query queryTwo = null;
        try {
            queryTwo = EmailMonitorCompiler.parse("if to = (dfg and thryg) and label = (marketing) and thread frequency per 1 day > 3  then send mail ( to : irjanith subject : test body :ppp )");
            return SiddhiQueryWriter.getInstance().writeQuery(queryTwo);
        } catch (Throwable throwable) {
            throw new EmailMonitorCompilerException(throwable.getMessage(), throwable);
        }
    }
}
