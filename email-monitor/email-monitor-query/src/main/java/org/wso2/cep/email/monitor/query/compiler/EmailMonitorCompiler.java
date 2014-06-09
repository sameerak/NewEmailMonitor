package org.wso2.cep.email.monitor.query.compiler;


import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.tree.CommonTree;
import org.antlr.runtime.tree.CommonTreeNodeStream;
import org.wso2.cep.email.monitor.query.api.Query;
import org.wso2.cep.email.monitor.query.compiler.siddhi.SiddhiTemplate;
import org.wso2.cep.email.monitor.query.compiler.siddhi.TemplatePopulator;


public class EmailMonitorCompiler {


    public static void main(String[] args) {
        try {
          Query query =  parse("if to = (sachini or isuru and sameer) or label = (marketing and support or presales) and  from =(ppp) and thread frequency per 1 days > 10  then add label olp");
            SiddhiTemplate siddhiTemplate = TemplatePopulator.convert(query);

        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }


    public static Query parse(String source) throws Throwable {
        try {
            emailMonitorLexer lexer = new emailMonitorLexer();
            lexer.setCharStream(new ANTLRStringStream(source));
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            emailMonitorParser parser = new emailMonitorParser(tokens);

            emailMonitorParser.prog_return r = parser.prog();

            CommonTree t = (CommonTree) r.getTree();

            CommonTreeNodeStream nodes = new CommonTreeNodeStream(t);
            nodes.setTokenStream(tokens);
            emailMonitorGrammerWalker walker = new emailMonitorGrammerWalker(nodes);
            return walker.prog().query;
        } catch (Throwable e) {
            throw e;
        }
    }
}
