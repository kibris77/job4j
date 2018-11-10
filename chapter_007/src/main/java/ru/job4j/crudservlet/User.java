package ru.job4j.crudservlet;

/**
 * Класс описывает модель пользователя в сервлете.
 */
public class User {
    private int id;
    private String name;
    private String login;
    private String email;
    private long date;

    public User(int id, String name, String login, String email, long date) {
        this.id = id;
        this.name = name;
        this.login = login;
        this.email = email;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLogin() {
        return login;
    }

    public String getEmail() {
        return email;
    }

    public long getDate() {
        return date;
    }
}
