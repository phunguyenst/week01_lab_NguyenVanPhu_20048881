<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Đăng nhập</title>
    <style>
        body{
            text-align: center;
            background-color: #fff;
            padding: 50px;
        }
        .login-container{
            background-color: aquamarine;
            padding: 20px;
            width: 300px;
            margin: 0 auto;
        }
        h1{
            text-align: center;
            font-size: 16px;
            color: red;
            margin-bottom: 20px;
        }
        label{
            display: block;
            text-align: left;
            margin-bottom: 10px;
        }
        input[type="text"], input[type="password"]{
            width: 100%;
            padding: 10px;
            margin-top: 20px;
            border: 1px solid black;
        }
        input[type="submit"]{
            margin-top: 10px;
            cursor: pointer;
            background-color: #007bff;
            border: none;
        }
    </style>
</head>
<body>

    <div class="login-container">
        <h1>Đăng nhập</h1>
        <form action="ControllerServlet" method="post">
            <label for="txtText" >Email:</label>
            <input type="text" name="txtText" id="txtText" required>
            <label for="txtPass">Mật khẩu:</label>
            <input type="password" name="txtPass" id="txtPass" required>
            <input type="hidden" name="action" value="Login">
            <input name="Login" type="submit" value="Login">
        </form>

    </div>

</body>
</html>