package ru.job4j.crudservlet;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Класс для хранения данных. Рализует шаблон Синглетон.
 */
public enum  MemoryStore implements Store {
    INSTANCE;

    private final ConcurrentHashMap<Integer, User> store = new ConcurrentHashMap<Integer, User>();

    /**
     * Метод добавляет пользователя в память.
     * @param user - класс пользователя User.
     */
    public void add(User user) {
        store.put(user.getId(), user);
    }

    /**
     * Метод редактирует пользователя в памяти.
     * @param id - id пользователя.
     * @param user - класс пользователя User.
     */
    public void update(int id, User user) {
        store.replace(id, user);
    }

    /**
     * Метод удаляет пользователя из память.
     * @param id - id пользователя.
     * @return - класс пользователя User.
     */
    public User delete(int id) {
        return store.remove(id);
    }

    /**
     * Метод находит пользователя по id.
     * @param id - id пользователя.
     * @return - класс пользователя User.
     */
    public User findById(int id) {
        return store.get(id);
    }

    /**
     * Метод возвращет всех пользователей из памяти.
     * @return = ArrayList(User).
     */
    public List<User> findAll() {
        return new ArrayList<>(store.values());
    }
}
