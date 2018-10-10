package ru.job4j.tracker;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Config {
    public final String dbUrl;
    public final String user;
    public final String pass;

    public Config() {
        dbUrl = "jdbc:postgresql://127.0.0.1:5432/job4j_tracker";
        user = "postgres";
        pass = "123456";
    }

    public Config(String dbUrl, String user, String pass) {
        this.dbUrl = dbUrl;
        this.user = user;
        this.pass = pass;
    }

    public Connection connectToDB() {
        try {
            Class.forName("org.postgresql.Driver");

        } catch (ClassNotFoundException e) {
            System.out.println("PostgreSQL JDBC Driver is not found. Include it in your library path ");
            e.printStackTrace();
        }

        System.out.println("PostgreSQL JDBC Driver successfully connected");
        Connection connection = null;

        try {
            connection = DriverManager
                    .getConnection(dbUrl, user, pass);
            Statement statement = connection.createStatement();
            statement.executeUpdate("CREATE TABLE  IF NOT EXISTS tracker("
                    + "id serial primary key, "
                    + "name varchar(256), "
                    + "item text, "
                    + "date timestamp"
                    + ")");
        } catch (SQLException e) {
            System.out.println("Connection Failed");
            e.printStackTrace();
        }

        if (connection != null) {
            System.out.println("You successfully connected to database now");
        } else {
            System.out.println("Failed to make connection to database");
        }
        return connection;
    }
}
