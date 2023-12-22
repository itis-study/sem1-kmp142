package org.example.Servlets;


import lombok.SneakyThrows;
import org.example.DAO.TaskDAO;
import org.example.DAO.TextDAO;
import org.example.DAO.TypingProcessDAO;
import org.example.DAO.UserDAO;
import org.example.Entities.*;
import org.example.UtilityClasses.ConnectionContainer;
import org.example.UtilityClasses.GetRandomText;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.PreparedStatement;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/typing")
public class TypingServlet extends HttpServlet {
    User user;
    TypingProcess typingProcess = null;
    Integer errors = 0;
    @Override
    @SneakyThrows
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        user = UserDAO.dao.getById((String) req.getSession().getAttribute("userLogin"));
        typingProcess = new TypingProcess();
        System.out.println(typingProcess.getId());
        typingProcess.setTask(new Task(GetRandomText.getText()));
        req.setAttribute("typingProcess", typingProcess);
        req.setAttribute("userLogin", user.getLogin());
        req.getRequestDispatcher("/typing.jsp").forward(req, resp);
    }

    @Override
    @SneakyThrows
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(req.getInputStream()));
            req.setCharacterEncoding("UTF-8");
            StringBuilder requestBody = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                requestBody.append(line);
            }

            String receivedText = requestBody.toString();

            if (receivedText.equals("setStartTime")) {
                typingProcess.setStartTime(LocalTime.now());
                errors = 0;
            }  else if (receivedText.equals("error")) {
                errors++;
            } else if (receivedText.equals("log out")) {
                req.getSession().setAttribute("userLogin", null);
            } else if (typingProcess.getStartTime() != null
                    && typingProcess
                            .getTask()
                            .getText()
                            .getText()
                            .length() == receivedText.length()
                    && req.getSession().getAttribute("userLogin") != null) {

                typingProcess.setEndTime(LocalTime.now());

                typingProcess.setAccuracy(100 - ((double)(errors) / (double) typingProcess
                        .getTask()
                        .getText()
                        .getText()
                        .length() * 100));

                typingProcess.setPerformer(UserDAO.dao
                        .getById((String)req.getSession().getAttribute("userLogin")));

                TypingProcessDAO.dao.save(typingProcess);
                typingProcess = new TypingProcess();
            }
        }
    }



