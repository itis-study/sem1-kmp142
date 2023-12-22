<%--
  Created by IntelliJ IDEA.
  User: cisco
  Date: 05.11.2023
  Time: 22:19
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
<form method="post" id="form">
    <label for="login">Имя пользователя:</label>
    <input type="text" id="login" name="login">
    <label for="password">Пароль:</label>
    <input type="password" id="password" name="password">
    <input type="submit" value="Войти" id="submitButton">
</form>
<div id="resultDiv"></div>
</body>
<script>
    const resultDiv = document.getElementById("resultDiv");
    const form = document.getElementById("form");
    const login = document.getElementById("login");
    login.


    form.addEventListener("submit", async function(event) {
        event.preventDefault();

        const login = document.getElementById("login").value;
        const password = document.getElementById("password").value;
        const json = JSON.stringify({
            login: login,
            password: password
        })

        const response = await fetch("/SpeedTypingProject/login", {
            method: "POST",
            body: json,
            headers: {
                "Content-type": "application/json"
            }
        });

        if (response.ok) {
            const data = await response.text();
            if (data === "Неверный пароль" || data === "Неверный логин") {
                alert(data);
            } else if (data === "success") {
                window.location.href = "/SpeedTypingProject/typing";
            }
        } else {
            alert("Произошла ошибка: " + response.status);
        }
    })
</script>
</html>
