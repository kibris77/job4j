package ru.job4j.servlets;

/**
 * Тестовый класс для создания и сохраниения JSON объектов.
 */
public class TestUser {
    private String name;
    private String surname;
    private String sex;
    private String description;

    @Override
    public String toString() {
        return "TestUser{"
                + "name='" + name + '\''
                + ", surname='" + surname + '\''
                + ", sex='" + sex + '\''
                + ", description='" + description + '\''
                + '}';
    }

    public TestUser() {
    }

    public TestUser(String name, String surename, String sex, String description) {
        this.name = name;
        this.surname = surename;
        this.sex = sex;
        this.description = description;

    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getSex() {
        return sex;
    }

    public String getDescription() {
        return description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
