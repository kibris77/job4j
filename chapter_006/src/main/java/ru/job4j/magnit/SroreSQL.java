package ru.job4j.magnit;

import java.sql.*;

/**
 * Класс заболняет данными базу.
 */
public class SroreSQL implements AutoCloseable {
    private Config config;
    private Connection connection;

    public SroreSQL(Config config) {
        this.config = config;
        connection = config.connectToDB();
    }

    /**
     * Метод заполняет таблицу числами от 0 до n
     * @param n - чичсло элементдов базе.
     */
    public void generate(int n) {
        if (clearDB()) {
            try (PreparedStatement statement = connection.prepareStatement("INSERT INTO entry(field) "
                    + "VALUES (?)")) {
                connection.setAutoCommit(false);
                for (int i = 0; i < n; i++) {
                    statement.setInt(1, i);
                    statement.executeUpdate();
                }
                connection.commit();
            } catch (SQLException e) {
                e.printStackTrace();
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        SroreSQL sroreSQL = new SroreSQL(new Config());
        sroreSQL.generate(5);
    }

    @Override
    public void close() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Метод удаляет все элементы из таблицы.
     * @return - boolean.
     */
    private boolean clearDB() {
        boolean result = false;
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("DELETE FROM entry");
            result = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
