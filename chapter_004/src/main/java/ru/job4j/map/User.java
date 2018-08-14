package ru.job4j.map;

import java.util.Calendar;
import java.util.Objects;

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

    @Override
    public int hashCode() {
        return Objects.hash(name, children, birthay);
    }
}
