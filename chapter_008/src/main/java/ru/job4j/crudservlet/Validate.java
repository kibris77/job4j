package ru.job4j.crudservlet;

import java.util.List;

/**
 * Интрефейс логического слоя проверки даныых.
 */
public interface Validate {
    boolean add(String id, String name, String login, String email, String password, String role, String country, String city) throws WrongDataException;
    boolean update(String id, String name, String login, String email, String password, String role, String country, String city) throws WrongDataException;
    boolean delete(String id) throws WrongDataException;
    User findById(String id, boolean checkId) throws WrongDataException;
    List<User> findAll();
    User findByLogin(String login);
    boolean isAuthorized(String login, String password);
}
