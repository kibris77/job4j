package ru.job4j.crudservlet;

import java.util.List;

public interface Validate {
    boolean add(String id, String name, String login, String email, String password, String role) throws WrongDataException;
    boolean update(String id, String name, String login, String email, String password, String role) throws WrongDataException;
    boolean delete(String id) throws WrongDataException;
    User findById(String id, boolean checkId) throws WrongDataException;
    List<User> findAll();
    User findByLogin(String login);
    boolean isAuthorized(String login, String password);
}
