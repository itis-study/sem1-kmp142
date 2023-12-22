package org.example.DAO;

import lombok.SneakyThrows;
import org.example.Entities.TypingProcess;
import org.example.Entities.User;
import org.example.UtilityClasses.ConnectionContainer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class TypingProcessDAO implements DAO<TypingProcess, String> {

    static Connection connection = ConnectionContainer.getConnection();

    public static DAO<TypingProcess, String> dao = new TypingProcessDAO();

    @Override
    @SneakyThrows
    public void save(TypingProcess tp) {
        PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO typing_process(start_time, end_time, task_id, user_login, id, accuracy) " +
                        "VALUES(?,?,?,?,?, ?)");
        statement.setString(1, tp.getStartTime().toString());
        statement.setString(2, tp.getEndTime().toString());
        statement.setString(3, tp.getTask().getId());
        statement.setString(4, tp.getPerformer().getLogin());
        statement.setString(5, tp.getId());
        statement.setDouble(6, tp.getAccuracy());
        statement.execute();
    }

    @Override
    @SneakyThrows
    public void delete(TypingProcess tp) {
        PreparedStatement statement = connection.prepareStatement(
                "DELETE FROM typing_process WHERE id=?"
        );
        statement.setString(1, tp.getId());
        statement.execute();
    }

    @Override
    @SneakyThrows
    public TypingProcess getById(String id) {
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM typing_process " +
                "WHERE id=?");
        statement.setString(1,id);
        TypingProcess tp = new TypingProcess();

        ResultSet rs = statement.executeQuery();
        while (rs.next()) {
            tp.setAccuracy(rs.getDouble("accuracy"));
            tp.setStartTime(LocalTime.parse(rs.getString("start_time")));
            tp.setEndTime(LocalTime.parse(rs.getString("end_time")));
            tp.setPerformer(UserDAO.dao.getById(rs.getString("user_login")));
            tp.setTask(TaskDAO.dao.getById(rs.getString("task_id")));
        }
        return tp;
    }

    @Override
    @SneakyThrows
    public List<TypingProcess> getAll() {
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM typing_process");
        ResultSet rs = statement.executeQuery();
        ArrayList<TypingProcess> arr = new ArrayList<>();
        while (rs.next()) {
            arr.add(new TypingProcess(rs.getString("id"),
                    LocalTime.parse(rs.getString("start_time")),
                    LocalTime.parse(rs.getString("end_time")),
                    rs.getDouble("accuracy"),
                    TaskDAO.dao.getById(rs.getString("task_id")),
                    UserDAO.dao.getById(rs.getString("user_login"))
                            ));
        }
        return arr;
    }

    @SneakyThrows
    public static List<TypingProcess> getAllForUser(User user) {
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM typing_process " +
                "WHERE user_login=?");

        statement.setString(1, user.getLogin());
        ResultSet rs = statement.executeQuery();

        ArrayList<TypingProcess> arr = new ArrayList<>();

        while (rs.next()) {
            TypingProcess tp = new TypingProcess(rs.getString("id"),
                    LocalTime.parse(rs.getString("start_time")),
                    LocalTime.parse(rs.getString("end_time")),
                    rs.getDouble("accuracy"),
                    TaskDAO.dao.getById(rs.getString("task_id")),
                    user);
        }

        return arr;
    }

    @Override
    @SneakyThrows
    public void update(TypingProcess tp) {
//        PreparedStatement statement = connection.prepareStatement(
//                "UPDATE typing_process SET start_time=?, end_time=?, task_id=?, user_login=?, accuracy " +
//                        "WHERE login=?");
//        statement.setString(1,);
//        statement.setInt(2,user.getScore());
//        statement.setBoolean(3, user.isAdmin());
//        statement.execute();
    }
}
