<%--
  Created by IntelliJ IDEA.
  User: cisco
  Date: 20.10.2023
  Time: 23:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Регистрация</title>
    <meta charset="UTF-8">
    <style>
        body {
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }
        form {
            display: flex;
            flex-direction: column;
            align-items: center;
            border: 2px solid #ccc;
            padding: 20px;
            border-radius: 10px;
        }
        label {
            font-weight: bold;
            margin-bottom: 10px;
        }
        input[type="text"],
        input[type="password"] {
            width: 100%;
            padding: 10px;
            margin-bottom: 20px;
            border-radius: 5px;
            border: 1px solid #ccc;
        }
        input[type="submit"] {
            background-color: #4CAF50;
            color: white;
            padding: 10px 20px;
            border-radius: 5px;
            border: none;
            cursor: pointer;
        }
        input[type="submit"]:hover {
            background-color: #3e8e41;
        }
    </style>
</head>
<body>
<form method="post">
    <label for="login">Имя пользователя:</label>
    <input type="text" id="login" name="login">
    <label for="password">Пароль:</label>
    <input type="password" id="password" name="password">
    <input type="submit" value="Зарегистрироваться">
</form>
</body>
</html>
