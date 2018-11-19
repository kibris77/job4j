package ru.job4j.crudservlet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ValidateStub implements Validate {
    private final Map<Integer, User> store = new HashMap<>();

    public ValidateStub() {
        store.put(5, new User(5, "ivan", "ivan", "ivan", "1234", "user", 1321));
        store.put(6, new User(6, "petr", "petr", "petr", "1234", "user", 1321));
    }

    @Override
    public boolean add(String id, String name, String login, String email, String password, String role) throws WrongDataException {
        User user = new User(1, name, login, email, password, role, 1234567);
        store.put(1, user);
        return true;
    }

    @Override
    public boolean update(String id, String name, String login, String email, String password, String role) throws WrongDataException {
        int index = Integer.parseInt(id);
        User user = new User(index, name, login, email, password, role, 1234567);
        store.replace(index, user);
        return true;
    }

    @Override
    public boolean delete(String id) throws WrongDataException {
        int index = Integer.parseInt(id);
        store.remove(index);
        return true;
    }

    @Override
    public User findById(String id, boolean checkId) throws WrongDataException {
        return store.get(1);
    }

    @Override
    public List<User> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public User findByLogin(String login) {
        return null;
    }

    @Override
    public boolean isAuthorized(String login, String password) {
        return false;
    }
}
