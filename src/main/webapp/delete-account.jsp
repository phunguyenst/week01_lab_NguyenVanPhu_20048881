<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 9/16/2023
  Time: 7:38 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Delete Account</title>
</head>
<body>
<form action="ControllerServlet" method="post">
    <p>Nhập mã account id cần xóa: </p>
    <label for="account_id">Account ID:</label>
    <input type="text" id="account_id" name="account_id" required maxlength="50"><br><br>
        <input type="hidden" name="action" value="deleteAccount">
        <input type="submit" name="deleteAccount" value="deleteAccount">
</form>
</body>
</html>
