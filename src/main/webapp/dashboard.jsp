<%--
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
</head>
<body>

    <h2>User Information</h2>
    <p>AccountId</p>
    <p>FullName</p>
    <p>Email</p>
    <p>Phone</p>
    <h2>User Permisson</h2>
    <ul>
        <li>Permission 1</li>
        <li>Permission 2</li>

    </ul>

    <!-- Nút Đăng Nhập -->
    <form action="ControllerServlet" method="POST">
        <input type="hidden" name="action" value="login">
        <input type="submit" value="Login">
    </form>

    <!-- Nút Thêm Account -->
    <form action="ControllerServlet" method="POST">
        <input type="hidden" name="action" value="addAccount">
        <input type="submit" value="Add Account">
    </form>

    <!-- Nút Cập Nhật Account -->
    <form action="ControllerServlet" method="POST">
        <input type="hidden" name="action" value="updateAccount">
        <input type="submit" value="Update Account">
    </form>

    <!-- Nút Xóa Account -->
    <form action="ControllerServlet" method="POST">
        <input type="hidden" name="action" value="deleteAccount">
        <input type="submit" value="Delete Account">
    </form>

    <!-- Nút Hiển Thị Account Info -->
    <form action="ControllerServlet" method="POST">
        <input type="hidden" name="action" value="displayAccountInfo">
        <input type="submit" value="Display Account Info">
    </form>

    <!-- Nút Hiển Thị Quyền của Account -->
    <form action="ControllerServlet" method="POST">
        <input type="hidden" name="action" value="displayRolePermissions">
        <input type="submit" value="Display Role Permissions">
    </form>

    <!-- Nút Hiển Thị Account theo Role -->
    <form action="ControllerServlet" method="POST">
        <input type="hidden" name="action" value="displayAccountsByRole">
        <input type="submit" value="Display Accounts by Role">
    </form>

    <!-- Nút Cấp Quyền cho Account -->
    <form action="ControllerServlet" method="POST">
        <input type="hidden" name="action" value="grantPermissions">
        <input type="submit" value="Grant Permissions">
    </form>

    <!-- Nút Ghi Log Đăng Nhập -->
    <form action="ControllerServlet" method="POST">
        <input type="hidden" name="action" value="logLogin">
        <input type="submit" value="Log Login">
    </form>

    <!-- Nút Ghi Log Đăng Xuất -->
    <form action="ControllerServlet" method="POST">
        <input type="hidden" name="action" value="logLogout">
        <input type="submit" value="Log Logout">
    </form>

    <!-- Nút Đăng Xuất -->
    <form action="ControllerServlet" method="POST">
        <input type="hidden" name="action" value="logout">
        <input type="submit" value="Logout">
    </form>
</body>
</html>
