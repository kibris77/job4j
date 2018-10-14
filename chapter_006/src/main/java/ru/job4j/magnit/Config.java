package ru.job4j.magnit;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Класс конфигурирующий подключения к базе данных.
 */
public class Config {
    public final String dbUrl;

    public Config() {
        dbUrl = "jdbc:sqlite:/home/alexander/database/sqlite_magnit";
    }

    public Config(String dbUrl) {
        this.dbUrl = dbUrl;
    }

    /**
     * Метод осуществляет подключыение к базе данных.
     * @return подключение.
     */
    public Connection connectToDB() {
        try {
            Class.forName("org.sqlite.JDBC");

        } catch (ClassNotFoundException e) {
            System.out.println("SQLITE JDBC Driver is not found. Include it in your library path ");
            e.printStackTrace();
        }

        System.out.println("SQLITE JDBC Driver successfully connected");
        Connection connection = null;

        try {
            connection = DriverManager
                    .getConnection(dbUrl);
            Statement statement = connection.createStatement();
            statement.executeUpdate("CREATE TABLE  IF NOT EXISTS entry("
                    + "field integer"
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
