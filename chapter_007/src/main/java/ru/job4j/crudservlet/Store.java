package ru.job4j.crudservlet;

import java.util.List;

/**
 * Интерфейс слоя хранения данных.
 */
public interface Store {
    void add(User user);
    void update(int id, User user);
    User delete(int id);
    User findById(int id);
    User findByLogin(String login);
    List<User> findAll();
}
