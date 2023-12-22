<%--
  Created by IntelliJ IDEA.
  User: cisco
  Date: 07.11.2023
  Time: 22:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Пример AJAX</title>
    <meta charset="UTF-8">
</head>
<body>
<form id="loginForm">
    <label for="username">Имя пользователя:</label>
    <input type="text" id="username" name="username"><br>

    <label for="password">Пароль:</label>
    <input type="password" id="password" name="password"><br>

    <input type="submit" value="Войти">
</form>

<div id="result"></div>

<script>
    const username = document.getElementById("username").value;
    const password = document.getElementById("password").value;
    console.log(username);

    // Создаем объект FormData для отправки данных формы
    const form = document.getElementById("loginForm");
    const resultDiv = document.getElementById("result");

    form.addEventListener("submit", async function(e) {
        e.preventDefault(); // Предотвращает отправку формы по умолчанию

        const json = JSON.stringify({
            name: "Dmitry",
            surname: "Alekseev"
        })

        fetch("/SpeedTypingProject/gpt", {
            method: "POST",
            body: json,
            headers: {
                "Content-type" : "application/json"
            }
        })
            .then(response => response.text())
            .then(data => {
                resultDiv.innerHTML = data; // Отображаем ответ на странице
            })
            .catch(error => {
                resultDiv.innerHTML = "Произошла ошибка: " + error;
            });
    });



        // Отправляем AJAX-запрос с использованием Fetch API

</script>
</body>
</html>

