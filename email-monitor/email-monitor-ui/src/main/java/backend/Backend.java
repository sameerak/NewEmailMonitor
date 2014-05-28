package backend;

/**
 * Created by sameerak on 5/28/14.
 */

import org.wso2.cep.email.esb.ProxyAdminClient;

public class Backend {

    public static String test(String ip, String port){
        String out = "false";
        ProxyAdminClient proxyAdminClient = new ProxyAdminClient(ip, port);
        proxyAdminClient.addMailProxy("admin", "admin");
        out = "true";
        return out;
    }
}
