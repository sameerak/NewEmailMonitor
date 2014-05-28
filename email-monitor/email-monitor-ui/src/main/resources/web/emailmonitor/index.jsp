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
            ESB IP: <input type="text" name="esbIP"><br>
            ESB Port: <input type="text" name="esbPort"><br>
            <input type="submit" value="Submit">
        </form>

            <%
            String ip = request.getParameter("esbIP");
            String port = request.getParameter("esbPort");
            %>

            <%if(ip !=null || port != null){%>

            <h2> <%=Backend.test(ip, port)%> </h2>
            <% } %>
    </div>
</div>