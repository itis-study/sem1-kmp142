package org.example.DAO;

import lombok.SneakyThrows;
import org.example.Entities.Category;
import org.example.Entities.Task;
import org.example.Entities.Text;
import org.example.Entities.TypingProcess;
import org.example.UtilityClasses.ConnectionContainer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class TextDAO implements DAO<Text, String> {

    Connection connection = ConnectionContainer.getConnection();
    public static DAO<Text, String> dao = new TextDAO();
    @Override
    @SneakyThrows
    public void save(Text text) {
        PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO texts(text, category, id) " +
                        "VALUES (?,?,?)");
        statement.setString(1, text.getText());
        statement.setString(2, text.getCategory().toString());
        statement.setString(3, text.getId());
        statement.execute();
    }

    @Override
    @SneakyThrows
    public void delete(Text text) {
        PreparedStatement statement = connection.prepareStatement(
                "DELETE FROM texts WHERE id=?");
        statement.setString(1, text.getId());
        statement.execute();
    }

    @Override
    @SneakyThrows
    public Text getById(String id) {
        PreparedStatement statement = connection.prepareStatement(
                "SELECT * FROM texts WHERE id=?");
        statement.setString(1, id);
        ResultSet rs = statement.executeQuery();
        Text text = new Text();
        while (rs.next()) {
            text.setId(rs.getString("id"));
            text.setText(rs.getString("text"));
            text.setCategory(Category.valueOf(rs.getString("category")));
        }
        return text;
    }

    @Override
    @SneakyThrows
    public List<Text> getAll() {
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM texts");
        ResultSet rs = statement.executeQuery();
        ArrayList<Text> arr = new ArrayList<>();
        while (rs.next()) {
            arr.add( new Text(rs.getString("text"),
                    Category.valueOf(rs.getString("category"))));
        }
        return arr;
    }

    @Override
    @SneakyThrows
    public void update(Text text) {

    }
}
