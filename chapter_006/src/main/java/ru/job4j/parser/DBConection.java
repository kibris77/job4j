package ru.job4j.parser;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.sql.*;

/**
 * Класс для работы с базой данных.
 */
public class DBConection implements AutoCloseable {
    private Connection connection;
    private static final Logger LOGGER = LogManager.getLogger(DBConection.class);

    /**
     * Метод осуществляющий подключение к базе данных.
     */
    public void connectToDB() {
        try {
            Class.forName(PropLoader.dbDriver);

        } catch (ClassNotFoundException e) {
            System.out.println("PostgreSQL JDBC Driver is not found. Include it in your library path ");
            LOGGER.error(e);
        }
        System.out.println("PostgreSQL JDBC Driver successfully connected");
        Connection connection = null;
        try {
            connection = DriverManager
                    .getConnection(PropLoader.dbUrl, PropLoader.user, PropLoader.pass);
            Statement statement = connection.createStatement();
            statement.executeUpdate("CREATE TABLE  IF NOT EXISTS parser("
                    + "id serial primary key, "
                    + "description text, "
                    + "link text, "
                    + "date timestamp"
                    + ")");
        } catch (SQLException e) {
            System.out.println("Connection Failed");
            LOGGER.error(e);
        }
        if (connection != null) {
            System.out.println("You successfully connected to database now");
        } else {
            System.out.println("Failed to make connection to database");
            LOGGER.error("Failed to make connection to database");
        }
        this.connection = connection;
    }

    /**
     * Метод возвращет дату последней записи в базе данных.
     * @return Timestamp.
     */
    public Timestamp lastDate() {
        Timestamp last = null;
        if (connection == null) {
            connectToDB();
        }
        try (PreparedStatement statement = connection.prepareStatement("SELECT MAX(date)"
                + "FROM parser");) {
            ResultSet set = statement.executeQuery();
            while (set.next()) {
                last = set.getTimestamp(1);
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return last;
    }

    /**
     * Метод добавляет запись в базу данных.
     * @param desc - описание вакансии.
     * @param link - ссылка на вакнасию.
     * @param data - Timestamp даты публикации вакансии.
     */
    public void insertData(String desc, String link, long data) {
        if (connection == null) {
            connectToDB();
        }
        try (PreparedStatement statement = connection.prepareStatement("INSERT INTO parser(description, link, date) "
                + "VALUES (?, ?, ?);");) {
            statement.setString(1, desc);
            statement.setString(2, link);
            statement.setTimestamp(3, new Timestamp(data));
            statement.executeUpdate();
            LOGGER.info(desc + " " + link);
        } catch (SQLException e) {
            e.printStackTrace();
            LOGGER.error(e);
        }
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }
}
