<%@ page import="org.apache.axis2.context.ConfigurationContext" %>
<%@ page import="org.wso2.carbon.CarbonConstants" %>
<%@ page import="org.wso2.carbon.ui.CarbonUIUtil" %>
<%@ page import="org.wso2.carbon.utils.ServerConstants" %>
<%@ page import="org.wso2.carbon.ui.CarbonUIMessage" %>
<%@ page import="org.wso2.cep.email.ui.ConfigUtils" %>
<%@ page import="org.wso2.cep.email.ui.ESBConfigUtils" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://wso2.org/projects/carbon/taglibs/carbontags.jar" prefix="carbon" %>
 
<div id="middle">
    <h2>Email Monitoring </h2>
 
    <div id="workArea">

        <form method="POST">
        <table>
            <tr>
                <td>email address:</td> <td><input type="text" name="emailAddress"></td>
            </tr>
            <tr>
                <td>email password:</td> <td><input type="password" name="emailPassword"></td>
            </tr>
            <tr>
                <td>ESB IP:</td> <td><input type="text" name="esbIP"></td>
            </tr>
            <tr>
                <td>ESB Port:</td> <td><input type="text" name="esbPort"></td>
            </tr>
            <tr>
                <td>ESB Username:</td> <td><input type="text" name="esbUserName"></td>
            </tr>
            <tr>
                <td>ESB Password:</td> <td><input type="password" name="esbPassword"></td>
            </tr>
            <tr>
                <td>CEP IP:</td> <td><input type="text" name="cepIP"></td>
            </tr>
            <tr>
                <td>CEP Port:</td> <td><input type="text" name="cepPort"></td>
            </tr>
            <tr>
                <td>CEP Username:</td> <td><input type="text" name="cepUserName"></td>
            </tr>
            <tr>
                <td>CEP Password:</td> <td><input type="password" name="cepPassword"></td>
            </tr>
            <tr>
                <td>CEP Queries:</td> <td><textarea name="cepQueries" rows="4" cols="50" style="margin: 2px; width: 415px; height: 115px;"></textarea></td>
            </tr>
            <tr>
                <td><input type="submit" value="Submit"></td>
            </tr>
        </table>
        </form>

            <%
            String emailAddress = request.getParameter("emailAddress");
            String emailPassword = request.getParameter("emailPassword");
            String esbIP = request.getParameter("esbIP");
            String esbPort = request.getParameter("esbPort");
            String esbUserName = request.getParameter("esbUserName");
            String esbPassword = request.getParameter("esbPassword");
            String cepIP = request.getParameter("cepIP");
            String cepPort = request.getParameter("cepPort");
            String cepUserName = request.getParameter("cepUserName");
            String cepPassword = request.getParameter("cepPassword");

            ConfigUtils configUtils = new ConfigUtils();

            if(configUtils.isNotNullOrEmpty(emailAddress) && configUtils.isNotNullOrEmpty(emailPassword) &&
                 configUtils.isNotNullOrEmpty(esbIP) && configUtils.isNotNullOrEmpty(esbPort) &&
                 configUtils.isNotNullOrEmpty(esbUserName) && configUtils.isNotNullOrEmpty(esbPassword) &&
                 configUtils.isNotNullOrEmpty(cepIP) && configUtils.isNotNullOrEmpty(cepPort) &&
                 configUtils.isNotNullOrEmpty(cepUserName) && configUtils.isNotNullOrEmpty(cepPassword)){

                ESBConfigUtils esbConfigUtils = new ESBConfigUtils(esbIP, esbPort, esbUserName, esbPassword);
                esbConfigUtils.AddConfigurations(cepUserName, cepPassword, emailAddress, emailPassword, cepIP, cepPort);
            %>
            <script>
                CARBON.showInfoDialog("ESB server configurations stored successfully");
            </script>
            <% }
            else {%>
            <script>
                CARBON.showInfoDialog("Please fill all the detail fields!");
            </script>
            <% } %>
    </div>
</div>