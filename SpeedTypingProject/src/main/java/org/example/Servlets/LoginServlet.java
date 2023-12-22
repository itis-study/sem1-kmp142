package org.example.Servlets;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.SneakyThrows;
import org.example.DAO.UserDAO;
import org.example.Entities.User;
import org.example.UtilityClasses.Cryptographer;
import org.example.UtilityClasses.JSONReader;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String userLogin = (String) session.getAttribute("userLogin");
        if (userLogin == null) {
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
        } else {
            resp.getWriter().write("you have already logged in");
        }
    }

    @Override
    @SneakyThrows
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {

        JsonNode json = JSONReader.readJSON(req);

        String userLogin = json.get("login").asText();
        String userPassword = json.get("password").asText();

        User user = UserDAO.dao.getById(userLogin);

        PrintWriter out = resp.getWriter();

        resp.setContentType("text/plain");
        resp.setCharacterEncoding("UTF-8");

        if (user.getLogin() != null) {
            String password = user.getPassword();
            if (password.equals(Cryptographer.hashPassword(userPassword))) {
                req.getSession().setAttribute("userLogin", userLogin);
                out.write("success");
            } else {
                out.write("Неверный пароль");
            }
        } else {
            out.write("Неверный логин");
        }
        out.close();
    }
}
