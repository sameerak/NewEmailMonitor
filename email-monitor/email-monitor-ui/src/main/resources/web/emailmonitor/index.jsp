<%@ page import="org.apache.axis2.context.ConfigurationContext" %>
<%@ page import="org.wso2.carbon.CarbonConstants" %>
<%@ page import="org.wso2.carbon.ui.CarbonUIUtil" %>
<%@ page import="org.wso2.carbon.utils.ServerConstants" %>
<%@ page import="org.wso2.carbon.ui.CarbonUIMessage" %>
<%@ page import="backend.Backend" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://wso2.org/projects/carbon/taglibs/carbontags.jar" prefix="carbon" %>
 
<div id="middle">
    <h2>Email Monitoring </h2>
 
    <div id="workArea">

        <form method="POST">
        <table>
            <tr>
                <td>email address:</td> <td><input type="text" name="email"></td>
            </tr>
            <tr>
                <td>password Port:</td> <td><input type="password" name="password"></td>
            </tr>
            <tr>
                <td>ESB IP:</td> <td><input type="text" name="esbIP"></td>
            </tr>
            <tr>
                <td>ESB Port:</td> <td><input type="text" name="esbPort"></td>
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
            String ip = request.getParameter("esbIP");
            String port = request.getParameter("esbPort");
            %>

            <%if(ip !=null || port != null){%>

            <%


            %>
            <h2> <%=Backend.test(ip, port)%> </h2>
            <script>
                CARBON.showInfoDialog("ESB server configurations stored successfully");
            </script>
            <% } %>
    </div>
</div>