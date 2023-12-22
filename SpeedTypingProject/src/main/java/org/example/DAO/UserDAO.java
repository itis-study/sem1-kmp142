package org.example.DAO;

import lombok.SneakyThrows;
import org.example.Entities.TypingProcess;
import org.example.Entities.User;
import org.example.UtilityClasses.ConnectionContainer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserDAO implements DAO<User, String> {

    public static DAO<User, String> dao = new UserDAO();

    Connection connection = ConnectionContainer.getConnection();


    @Override
    @SneakyThrows
    public void save(User user) {
        PreparedStatement statement = connection.prepareStatement("INSERT INTO users(is_admin, login, score, user_password)" +
                " VALUES(?, ?, ?, ?)");
        statement.setBoolean(1, user.isAdmin());
        statement.setString(2, user.getLogin());
        statement.setInt(3, user.getScore());
        statement.setString(4, user.getPassword());
        statement.execute();
    }

    @Override
    @SneakyThrows
    public void delete(User user) {
        PreparedStatement statement = connection.prepareStatement(
                "DELETE FROM users WHERE login=?");
        statement.setString(1, user.getLogin());
        statement.execute();
    }

    @Override
    @SneakyThrows
    public User getById(String id) {
        PreparedStatement statement = connection.prepareStatement(
                "SELECT * FROM users WHERE login=?");
        statement.setString(1, id);
        ResultSet rs = statement.executeQuery();
        User user = new User();
        while(rs.next()) {
            user.setLogin(rs.getString("login"));
            System.out.println(rs.getString("login"));
            user.setPassword(rs.getString("user_password"));
            System.out.println(rs.getString("user_password"));
            user.setScore(rs.getInt("score"));
            System.out.println(rs.getString("score"));
            user.setAdmin((rs.getBoolean("is_admin")));
            System.out.println("" + rs.getBoolean("is_admin"));
        }
        ArrayList<TypingProcess> arr = (ArrayList<TypingProcess>) TypingProcessDAO.getAllForUser(user);
        user.setTypingHistory(arr);
        return user;
    }

    @Override
    @SneakyThrows
    public List<User> getAll() {
        PreparedStatement statement = connection.prepareStatement(
                "SELECT * FROM users"
        );
        ResultSet rs = statement.executeQuery();
        ArrayList<User> users = new ArrayList<>();
        while (rs.next()) {
            users.add(new User(rs.getBoolean("is_admin"), rs.getString("login"),
                    new ArrayList<>(),
                    rs.getInt("score"), rs.getString("user_password")));
        }
        return users;
    }

    @Override
    @SneakyThrows
    public void update(User user) {
        PreparedStatement statement = connection.prepareStatement(
                "UPDATE users SET user_password=?, score=?, is_admin=? " +
                        "WHERE login=?");
        statement.setString(1,user.getPassword());
        statement.setInt(2,user.getScore());
        statement.setBoolean(3, user.isAdmin());
        statement.execute();
    }
}
