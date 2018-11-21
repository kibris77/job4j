package ru.job4j.crudservlet;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ProperstiesStore {
    private static String dbUrl;
    private static String user;
    private static String pass;

    public static void loadProps(String filename) {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream(filename));
            dbUrl = properties.getProperty("jdbc.url");
            user = properties.getProperty("jdbc.username");
            pass = properties.getProperty("jdbc.password");
        } catch (IOException e) {
            dbUrl = "jdbc:postgresql://127.0.0.1:5432/sqlparcer";
            user = "postgres";
            pass = "123456";
        }
    }

    public static String getDbUrl() {
        return dbUrl;
    }

    public static String getUser() {
        return user;
    }

    public static String getPass() {
        return pass;
    }
}
