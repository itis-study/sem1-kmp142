<%@ page import="org.example.Entities.TypingProcess" %><%--
  Created by IntelliJ IDEA.
  User: cisco
  Date: 23.10.2023
  Time: 00:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>HippoType</title>
    <style>
        #inputText {
            width: 800px;
            height: 200px;
            margin: 20px;
            alignment: center;
            align-items: center;

        }
        #info, #inputBlock{
            text-align: center;
            font-size: 25px;

        }

        #info {
            display: block;
            margin-left: 50px;
        }

        #textBlock {
            border: 5px solid;
            margin: auto;
            width: 50%;
            padding: 5px;
            border-radius: 10px;
            text-align: center;
        }

        #endGame, #startGame {
            padding: 10px;
            border-radius: 10px;
            font-size: 20px;
            margin-bottom: 10px;
        }

        .hide {
            display: none;
        }

        .show {
            display: block;
        }

        #login {
            display: block;
            font-size: 35px;
            text-align: right;
            margin-right: 30px;
            alignment: right;


        }


    </style>
</head>
<body>
<% TypingProcess typingProcess = (TypingProcess) request.getAttribute("typingProcess"); %>
<% String login = (String) request.getAttribute("userLogin"); %>

<div id="upperInfo">
    <button onclick="forwardToLoginServlet()" id="authorizationButton">Авторизоваться</button>
    <button onclick="logOut()" id="LogOutButton">Выйти</button>
    <button onclick="register()" id="registrationBtn">Зарегистрироваться</button>
    <span id="login"><%="Ваш логин: " + login%></span>
</div>

<div id="textBlock">

    <p style="font-size: 30px" id="text" oncopy="return false"><%=typingProcess.getTask().getText().getText()%></p>
</div>

<div id="inputBlock">
    <textarea placeholder="Начните ввод для запуска игры" style="font-size: 30px" onpaste="return false" id="inputText"></textarea>
    <div id="info">
        <span id="errors">Errors: 0</span><br>
        <span id="lastTryErrors">Last try errors: 0</span><br>
        <span id="timer">Timer: 0 seconds</span><br><br>
        <button id="endGame" onclick="gameEnded()">Остановить игру</button><br>
        <button id="startGame" onclick="changeText()">Обновить текст</button>
    </div>
</div>




<script>
    const text = document.getElementById("text").innerText;
    const input = document.getElementById("inputText");
    const timer = document.getElementById("timer");

    let index = 0;
    let errors = 0;
    let timerInterval;
    let timerCounter = 0;

    input.addEventListener("keypress", event => {
        if (index === 0) {
            gameEnded()
            sendRequestWithText("setStartTime");
            timer.innerText = "Timer: 0 seconds";
            timerInterval = setInterval("startTimer()", 1000);
        }
        if (event.key !== text.charAt(index)) {
            errors += 1;
            sendRequestWithText("error");
        }
        console.log("Errors: " + errors + ", index: " + index);
        index++;
    })

    input.addEventListener("keyup", () => {
        if (index === text.length) {
            sendRequestWithText(input.value);
            gameEnded();
        }
        document.getElementById("errors").innerText = "Errors: " + errors;
    })

    input.addEventListener("keydown", () => {
        index = input.value.length;
    })

    function startTimer() {
        timerCounter++;
        timer.innerHTML = "Timer: " + timerCounter + " seconds";
    }

    function gameEnded() {
        clearInterval(timerInterval);
        document.getElementById("errors").innerText = "Errors: " + 0;
        document.getElementById("lastTryErrors").innerText = "Last try errors: " + errors;
        input.value = "";
        errors = 0;
        timerCounter = 0;
    }

    function changeText() {
        window.location.href = "/SpeedTypingProject/typing";
    }

    function sendRequestWithText(text) {
         fetch("/SpeedTypingProject/typing", {
            method: "POST",
            body: text,
            headers: {
                "Content-type": "text/plain"
            }
        })
    }

    function logOut() {
        sendRequestWithText("log out")
        window.location.href = "/SpeedTypingProject/typing"
    }

    function register(){
        window.location.href = "/SpeedTypingProject/reg"
    }
</script>

<script>
    const login = document.getElementById("login");
    const authButton = document.getElementById("authorizationButton");
    const logOutBtn = document.getElementById("LogOutButton");
    const regBtn = document.getElementById("registrationBtn");
    if (login.innerText === "Ваш логин: null") {
        login.innerText = "";
        authButton.classList.add("show");
        regBtn.classList.add("show");
        logOutBtn.classList.add("hide");
    } else {
        authButton.classList.add("hide");
        regBtn.classList.add("hide");
        logOutBtn.classList.add("show");
    }

    function forwardToLoginServlet() {
        window.location.href = "/SpeedTypingProject/login";
    }
</script>

</body>
</html>
