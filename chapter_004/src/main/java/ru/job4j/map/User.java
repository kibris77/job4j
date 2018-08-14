package ru.job4j.map;

import java.util.Calendar;

/**
 * Класс пользователь.
 */
public class User {
    String name;
    int children;
    Calendar birthay;

    public User(String name, int children, Calendar birthay) {
        this.name = name;
        this.children = children;
        this.birthay = birthay;
    }
}
