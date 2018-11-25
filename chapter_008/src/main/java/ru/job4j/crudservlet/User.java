package ru.job4j.crudservlet;

import java.util.Objects;

/**
 * Класс описывает модель пользователя в сервлете.
 */
public class User {
    private int id;
    private String name;
    private String login;
    private String email;
    private String password;
    private String role;
    private long date;
    private String country;
    private String city;

    public User(int id, String name, String login, String email, String password, String role, long date) {
        this.id = id;
        this.name = name;
        this.login = login;
        this.email = email;
        this.password = password;
        this.role = role;
        this.date = date;
    }

    public User(int id, String name, String login, String email, String password, String role, long date, String country, String city) {
        this.id = id;
        this.name = name;
        this.login = login;
        this.email = email;
        this.password = password;
        this.role = role;
        this.date = date;
        this.country = country;
        this.city = city;
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

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public long getDate() {
        return date;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof User)) {
            return false;
        }
        User user = (User) o;
        return id == user.id
                && Objects.equals(name, user.name)
                && Objects.equals(login, user.login)
                && Objects.equals(email, user.email)
                && Objects.equals(role, user.role)
                && Objects.equals(country, user.country)
                && Objects.equals(city, user.city);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, login, email, role, country, city);
    }
}
