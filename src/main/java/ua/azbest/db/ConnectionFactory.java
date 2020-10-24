package ua.azbest.db;

import ua.azbest.model.Subject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class ConnectionFactory {

    public static Connection getConnection() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Connection connection = null;

        /*Properties connectionProperties = new Properties();

        connectionProperties.load(DentWebInitializer.class.
                getResourceAsStream(
                        "db.properties"));*/

        List<Subject> list = null;

        try {
            connection = DriverManager.getConnection(
//                    connectionProperties.getProperty("jdbc:postgresql://localhost:5432/webdb"),
//                    connectionProperties.getProperty("postgres"),
//                    connectionProperties.getProperty("postgres")
                    "jdbc:postgresql://localhost:5432/webdb",
                    "postgres",
                    "postgres"
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
