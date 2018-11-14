package ru.job4j.parser;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Класс определяет кофигурацию подключения к базе данных.
 */
public class PropLoader {
    public static String dbDriver;
    public static String dbUrl;
    public static String user;
    public static String pass;
    public static String cron;

    static {
        try {
            Properties properties = new Properties();
            properties.load(PropLoader.class.getClassLoader().getResourceAsStream("app.properties"));
            dbDriver = properties.getProperty("jdbc.driver");
            dbUrl = properties.getProperty("jdbc.url");
            user = properties.getProperty("jdbc.username");
            pass = properties.getProperty("jdbc.password");
            cron = properties.getProperty("cron.time");
        } catch (IOException e) {
            System.out.println("Settings file Error");
        }
    }

    /**
     * Метод считывает файл с настройками.
     * @param file - путь к файлу.
     */
    public static void loadProps(String file) {
        try {
            FileInputStream input = new FileInputStream(file);
            Properties properties = new Properties();
            properties.load(input);
            dbDriver = properties.getProperty("jdbc.driver");
            dbUrl = properties.getProperty("jdbc.url");
            user = properties.getProperty("jdbc.username");
            pass = properties.getProperty("jdbc.password");
            cron = properties.getProperty("cron.time");
        } catch (IOException e) {
            System.out.println("Settings file Error");
        }
    }
}
