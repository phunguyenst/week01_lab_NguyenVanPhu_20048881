<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Role Permissions</title>
</head>
<body>
<h1>Role Permissions</h1>
<ul>
    <c:forEach var="role" items="${rolePermissions}">
        <li>${role.name}</li>
    </c:forEach>
</ul>
</body>
</html>