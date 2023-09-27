<%@ page import="fit.iuh.edu.vn.repositories.RoleRepository" %>
<%@ page import="fit.iuh.edu.vn.entities.Role" %>
<%@ page import="fit.iuh.edu.vn.entities.Account" %>
<%@ page import="java.util.List" %>

<%@ page import="fit.iuh.edu.vn.repositories.RoleRepository" %><%--
  Created by IntelliJ IDEA.
  User: Student
  Date: 9/10/2023
  Time: 10:24 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>DashBoard</title>
    <style>
        body{
            text-align: center;
        }
        .all-button{
            display: flex;
            justify-content: center;
        }
        .link{
            color: darkorchid;
            font-size: 18px;
        }
        .LinkAll{
            color: red;
            font-size: 22px;
        }
    </style>

</head>
<body>

<%
    Account account = (Account) request.getAttribute("account");

    if(account!= null){
%>

<table>
    <tbody>
    <tr>
        <h2>Welcome Admin <%= account.getFull_name()%></h2>
        <h4>Account Id: <%= account.getAccount_id()%></h4>

        <h4>Account email: <%= account.getEmail()%></h4>

        <h4>Account phone: <%=account.getPhone()%></h4>

        <h4>Account status: <%=account.getStatus()==1?"hoạt động": "không hoạt động"%></h4>


    </tr>
    </tbody>


</table>
<%
    }
%>
    <p>Xem danh sách tài khoảng hiện có</p>
    <a class="LinkAll" href="account-list.jsp">Danh sách tài khoản</a>

<%-- ... --%>
<%

    List<Role> rolePermissions = new RoleRepository().getRolesForAccount(account.getAccount_id());

//    for (Role role:rolePermissions) {
//        System.out.println(role);
//    }
    if (rolePermissions != null && !rolePermissions.isEmpty()) {
        for (Role role : rolePermissions) {
            System.out.println(role);
%>
<h2>Role Information</h2>
<p>Role ID: <%= role.getRole_id() %></p>
<p>Role Name: <%= role.getRole_name() %></p>
<p>Description: <%= role.getDescription() %></p>
<p>Status: <%= role.getStatus() == 1 ? "Hoạt động" : "Không hoạt động" %></p>
<%
    }
} else {
%>
<p>No role found for this roleId.</p>
<%
    }
%>
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
