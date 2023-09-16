<%@ page import="fit.iuh.edu.vn.repositories.AccountRepository" %>
<%@ page import="java.util.List" %>
<%@ page import="fit.iuh.edu.vn.entities.Account" %>
<%@ page import="fit.iuh.edu.vn.entities.Role" %>
<%@ page import="fit.iuh.edu.vn.repositories.RoleRepository" %>
<%@ page import="fit.iuh.edu.vn.repositories.AccountRepository" %>
<%@ page import="fit.iuh.edu.vn.repositories.RoleRepository" %><%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 9/14/2023
  Time: 4:03 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">

<%--    <style>--%>
<%--        body{--%>
<%--            text-align: center;--%>
<%--        }--%>
<%--        .all-button{--%>
<%--            display: flex;--%>
<%--            justify-content: center;--%>
<%--        }--%>
<%--    </style>--%>

</head>
<body>
<%
    Account account = (Account) request.getAttribute("account");

   if(account!= null){
%>

<table>
    <tbody>
    <tr>
        <h2>Welcome <%= account.getFull_name()%></h2>
        <h4>Account Id: <%= account.getAccount_id()%></h4>

        <h4>Account email: <%= account.getEmail()%></h4>

        <h4>Account phone: <%=account.getPhone()%></h4>

        <h4>Account status: <%=account.getStatus()==1?"hoạt động": "không hoạt động"%></h4>


    </tr>
    </tbody>


</table>
//xem log
<%
    }
%>
<div>
    <h3>Log:</h3>
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
</div>



//lấy roll
<%

    Account account2 = (Account) request.getAttribute("account");

    if (account != null) {

        AccountRepository accountRepository = new AccountRepository();
        RoleRepository roleRepository = new RoleRepository();


        String account_id = accountRepository.getAccountIdByEmail(account.getEmail());


        List<Role> accountRoles = roleRepository.getRolesForAccount(account_id);


        if (!accountRoles.isEmpty()) {
%>
<h2>Roles for Account: <%= account.getFull_name() %></h2>
<table>
    <thead>
    <tr>
        <th>Role ID</th>
        <th>Role Name</th>
        <th>Description</th>
        <th>Status</th>
    </tr>
    </thead>
    <tbody>
    <% for (Role role : accountRoles) { %>
    <tr>
        <td><%= role.getRole_id() %></td>
        <td><%= role.getRole_name() %></td>
        <td><%= role.getDescription() %></td>
        <td><%= role.getStatus() == 1 ? "Active" : "Inactive" %></td>
    </tr>
    <% } %>
    </tbody>
</table>
<%
} else {
%>
<p>No roles found for this account.</p>
<%
        }
    }
%>
<form action="ControllerServlet" method="post">
    <input type="submit" value="logout" name="logout">
    <input type="hidden" name="action" value="logout">
    <div>
        <%--        <%=log.toString()%>--%>
    </div>
</form>
</body>
</html>
