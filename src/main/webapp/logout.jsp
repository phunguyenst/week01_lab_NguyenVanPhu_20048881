<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Logout</title>
</head>
<body>
<h1>Logout Successful</h1>
<p>You have been successfully logged out.</p>
<h3>Log đăng xuất:</h3>
<ul>
    <%
        List<String> logList = (List<String>) request.getSession().getAttribute("logList");
        if (logList != null) {
            for (String log : logList) {
    %>
    <li><%= log %></li>
    <%
            }
        }
    %>
</ul>
<p><a href="index.jsp">Login</a></p>
</body>
</html>
