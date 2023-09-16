<%@ page import="fit.iuh.edu.vn.repositories.AccountRepository" %>
<%@ page import="java.util.List" %>
<%@ page import="fit.iuh.edu.vn.entities.Account" %><%--
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
    <title>Danh sách tài khoảng</title>
    <style>
        body{
            text-align: center;
        }
        .all-button{
            display: flex;
            justify-content: center;
        }
    </style>

</head>
<body>
    <%
        AccountRepository accountRepository = new AccountRepository();
        List<Account> accountList = accountRepository.getAccountListFromDatabase();
        for(Account account : accountList){
    %>

    <table>
        <tbody>
        <tr>
            <td><%= account.getAccount_id()%></td>
            <td><%= account.getFull_name()%></td>
            <td><%= account.getEmail()%></td>
            <td><%=account.getPhone()%></td>
            <td><%=account.getStatus()==1?"hoạt động": "không hoạt động"%></td>

        </tr>
        </tbody>
    </table>

<%
    }
%>


</body>
</html>
<%!

%>