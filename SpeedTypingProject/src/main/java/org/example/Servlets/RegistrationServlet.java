package org.example.Servlets;

import lombok.SneakyThrows;
import org.example.DAO.UserDAO;
import org.example.UtilityClasses.ConnectionContainer;
import org.example.UtilityClasses.Cryptographer;
import org.example.Entities.User;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@WebServlet("/reg")
public class RegistrationServlet extends HttpServlet {
    @Override
    @SneakyThrows
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();
        String userLogin = (String) session.getAttribute("userLogin");
        if (userLogin == null) {
            req.getRequestDispatcher("/registration.jsp").forward(req, resp);
        } else {
            resp.getWriter().write("you are already registered");
        }
    }

    @Override
    @SneakyThrows
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        String userLogin = req.getParameter("login");
        String userPassword = req.getParameter("password");
        System.out.println(userLogin);
        Connection connection  = ConnectionContainer.getConnection();
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM users " +
                "WHERE login=?");
        statement.setString(1,userLogin);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            resp.sendRedirect("/SpeedTypingProject/login");
        } else {
            User user = new User();
            user.setPassword(Cryptographer.hashPassword(userPassword));
            user.setLogin(userLogin);
            user.setAdmin(userLogin.equals("dima"));
            user.setScore(0);

            UserDAO.dao.save(user);

            req.getSession().setAttribute("userLogin", userLogin);
            resp.sendRedirect("/SpeedTypingProject/typing");
        }


    }
}
