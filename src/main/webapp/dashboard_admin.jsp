<%@ page import="fit.iuh.edu.vn.repositories.RoleRepository" %>
<%@ page import="fit.iuh.edu.vn.entities.Role" %>
<%@ page import="fit.iuh.edu.vn.entities.Account" %>
<%@ page import="java.util.List" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>DashBoard</title>
    <style>
        body {
            text-align: center;
        }

        .all-button {
            display: flex;
            justify-content: center;
        }

        .link {
            color: darkorchid;
            font-size: 18px;
        }

        .LinkAll {
            color: red;
            font-size: 22px;
        }

        /* CSS cho nút logout */
        input[type="submit"] {
            background-color: #ff0000;
            color: #fff;
            padding: 10px 20px;
            border: none;
            cursor: pointer;
        }
    </style>
</head>
<body>
<%
    Account account = (Account) request.getAttribute("account");

    if(account != null) {
%>

<table>
    <tbody>
    <tr>
        <h2>Welcome Admin <%= account.getFull_name() %></h2>
        <h4>Account Id: <%= account.getAccount_id() %></h4>
        <h4>Account email: <%= account.getEmail() %></h4>
        <h4>Account phone: <%= account.getPhone() %></h4>
        <h4>Account status: <%= account.getStatus() == 1 ? "hoạt động" : "không hoạt động" %></h4>
    </tr>
    </tbody>
</table>

<%
    } else {
        // Handle the case where account is null, for example, redirect to an error page or login page.
        System.out.println("Account is null. Redirecting to error page or login page.");

    }
%>

<p>Xem danh sách tài khoảng hiện có</p>
<a class="LinkAll" href="account-list.jsp">Danh sách tài khoản</a>

<%
    if (account != null) {
        List<Role> rolePermissions = new RoleRepository().getRolesForAccount(account.getAccount_id());

        if (rolePermissions != null && !rolePermissions.isEmpty()) {
            for (Role role : rolePermissions) {
%>
<!-- Your existing role information display code -->
<%
    }
} else {
%>
<p>No role found for this roleId.</p>
<%
        }
    }
%>

<h3>Log đăng nhập:</h3>
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

<div class="all-button">
    <div class="link">
        <a href="add-account.jsp">Thêm Tài khoản</a>
        <a href="update-account.jsp">Sửa Tài khoản</a>
        <a href="delete-account.jsp">Xóa Tài khoản</a>
    </div>
    <form action="ControllerServlet" method="post">
        <input type="submit" value="logout" name="logout">
        <input type="hidden" name="action" value="logout">
        <div>
            <%--        <%=log.toString()%>--%>
        </div>
    </form>
</div>

</body>
</html>
