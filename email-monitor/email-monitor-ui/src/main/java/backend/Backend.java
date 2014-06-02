package backend;


public class Backend {

    public static String test(String ip, String port){
        String out = "false";

//        BAMMediatorAdminClient bamMediatorAdminClient = new BAMMediatorAdminClient(ip, port);
//        bamMediatorAdminClient.addMailProxy("admin", "admin");
//
//        ProxyAdminClient proxyAdminClient = new ProxyAdminClient(ip, port);
//        proxyAdminClient.addMailProxy("admin", "admin");
//
//        TaskAdminClient taskAdminClient = new TaskAdminClient(ip, port);
//        try {
//            taskAdminClient.addScheduledTask("admin", "admin");
//        } catch (XMLStreamException e) {
//            e.printStackTrace();
//        } catch (TaskManagementException e) {
//            e.printStackTrace();
//        } catch (RemoteException e) {
//            e.printStackTrace();
//        }

//        ESBConfigurationHelper esbConfigurationHelper = new ESBConfigurationHelper(ip, port);
//        esbConfigurationHelper.addConfigurations("admin", "admin");

        out = "true";
        return out;
    }
}
