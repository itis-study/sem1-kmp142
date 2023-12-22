package org.example.DAO;

import lombok.SneakyThrows;
import org.example.Entities.Task;
import org.example.UtilityClasses.ConnectionContainer;
import org.w3c.dom.Text;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TaskDAO implements DAO<Task, String> {

    public static DAO<Task, String> dao = new TaskDAO();

    Connection connection = ConnectionContainer.getConnection();

    @Override
    @SneakyThrows
    public void save(Task task) {
        PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO tasks(text_id, creation_date, id) " +
                        "VALUES (?, ?, ?)");
        statement.setString(1, task.getText().getId());
        statement.setString(2, task.getCreationDate().toString());
        statement.setString(3, task.getId());
        statement.execute();
    }

    @Override
    @SneakyThrows
    public void delete(Task task) {
        PreparedStatement statement = connection.prepareStatement(
                "DELETE FROM tasks WHERE id=?");
        statement.setString(1, task.getId());
        statement.execute();
    }

    @Override
    @SneakyThrows
    public Task getById(String id) {
        PreparedStatement statement = connection.prepareStatement(
                "SELECT * FROM tasks WHERE id=?");
        statement.setString(1, id);
        ResultSet rs = statement.executeQuery();
        Task task = new Task();
        while (rs.next()) {
            task.setId(id);
            task.setText(TextDAO.dao.getById(rs.getString("text_id")));
            task.setCreationDate(LocalDate.parse(rs.getString("creation_date")));
        }
        return null;
    }

    @Override
    @SneakyThrows
    public List<Task> getAll() {
        PreparedStatement statement = connection.prepareStatement(
                "SELECT * FROM tasks");
        ResultSet rs = statement.executeQuery();
        Task task = new Task();
        ArrayList<Task> tasks = new ArrayList<>();
        while (rs.next()) {
            tasks.add(new Task(TextDAO.dao.getById(rs.getString("text_id"))));
        }
        return tasks;
    }

    @Override
    public void update(Task task) {

    }
}
