package ua.azbest.dao;

import org.springframework.stereotype.Component;
import ua.azbest.config.DentWebInitializer;
import ua.azbest.db.ConnectionFactory;
import ua.azbest.model.Subject;

import java.io.IOException;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;

@Component
public class SubjectDAOPostgres implements SubjectDAO {
    @Override
    public List<Subject> index() throws IOException {

        Connection connection = ConnectionFactory.getConnection();
        Statement statement = null;
        List<Subject> list = new LinkedList<>();

        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * from subjects");

            while (resultSet.next()) {
                Subject subject = new Subject();
                subject.setId(resultSet.getLong("id"));
                subject.setTitle(resultSet.getString("title"));
                subject.setLecturer(resultSet.getString("lecturer"));
                subject.setCredits(resultSet.getInt("credits"));
                list.add(subject);
            }
            statement.close();
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return list;
    }

    @Override
    public Subject show(long id) {
        Subject subject = null;
        Connection connection = ConnectionFactory.getConnection();

        PreparedStatement pst = null;
        try {
            pst = connection.prepareStatement("SELECT * from subjects WHERE id = ?");
            pst.setLong(1, id);
            ResultSet resultSet = pst.executeQuery();

            subject = new Subject();
            if (resultSet.next()) {
                subject.setId(resultSet.getLong("id"));
                subject.setTitle(resultSet.getString("title"));
                subject.setLecturer(resultSet.getString("lecturer"));
                subject.setCredits(resultSet.getInt("credits"));
            }

            pst.close();
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return subject;
    }

    @Override
    public void save(Subject subject) {
        try {
            Connection connection = ConnectionFactory.getConnection();
            PreparedStatement pst = connection.prepareStatement("insert into subjects(title, lecturer, credits) values (?,?,?)");
            pst.setString(1, subject.getTitle());
            pst.setString(2, subject.getLecturer());
            pst.setInt(3, subject.getCredits());
            pst.executeUpdate();
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void update(long id, Subject subject) {
        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement pst;
        try {
            pst = connection.prepareStatement("update subjects set title = ?, lecturer = ?, credits = ? where id = ?");
            pst.setString(1, subject.getTitle());
            pst.setString(2, subject.getLecturer());
            pst.setInt(3, subject.getCredits());
            pst.setLong(4, subject.getId());
            pst.executeUpdate();
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void delete(long id) {
        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement pst;

        try {
            pst = connection.prepareStatement("delete from subjects where id = ?");
            pst.setLong(1, id);
            pst.executeUpdate();
            connection.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
