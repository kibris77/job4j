package ru.job4j.crudservlet;

import java.util.List;

/**
 * Класс уровня логический слой проверки данных. Реализует шаблон Синглтон.
 */
public class ValidateService {
    private final static ValidateService INSTANCE = new ValidateService();
    private final Store memoryStore = MemoryStore.INSTANCE;

    private ValidateService() {

    }

    public static ValidateService getInstance() {
        return INSTANCE;
    }

    /**
     * Метод проверяет данные для для добавления.
     * @param id - id пользователя.
     * @param name - имя пользователя
     * @param login - логин пользователя.
     * @param email - почта пользователя.
     * @return - boolean.
     * @throws WrongDataException - исключение при неправилном вводе данных.
     */
    public boolean add(String id, String name, String login, String email) throws WrongDataException {
        boolean result = false;
        checkData(id, name, login, email);
        int userId = checkId(id, true);
        if (memoryStore.findById(userId) == null) {
            memoryStore.add(new User(userId, name, login, email, System.currentTimeMillis()));
            result = true;
        }
        return result;
    }

    /**
     * Метод проверяет данные для редактирования.
     * @param id - id пользователя.
     * @param name - имя пользователя
     * @param login - логин пользователя.
     * @param email - почта пользователя.
     * @return - boolean.
     * @throws WrongDataException - исключение при неправилном вводе данных.
     */
    public boolean update(String id, String name, String login, String email) throws WrongDataException {
        boolean result = false;
        checkData(id, name, login, email);
        int userId = Integer.parseInt(id);
        if (memoryStore.findById(userId) != null) {
            memoryStore.update(userId, new User(userId, name, login, email, System.currentTimeMillis()));
            result = true;
        }
        return result;
    }

    /**
     * Метод проверяет id пользователя для удаления.
     * @param id - id пользователя.
     * @return - boolean.
     * @throws WrongDataException - исключение при неправилном вводе данных.
     */
    public boolean delete(String id) throws WrongDataException {
        int userId = Integer.parseInt(id);
        return (memoryStore.delete(userId) != null);
    }

    /**
     * Метод проверяет id пользователя для поиска.
     * @param id - id пользователя.
     * @return - boolean.
     * @throws WrongDataException - исключение при неправилном вводе данных.
     */
    public User findById(String id, boolean checkId) throws WrongDataException {
        int userId = checkId(id, checkId);
        return memoryStore.findById(userId);
    }

    /**
     * Метод возращает список всех пользователей.
     * @return - List(User);
     */
    public List<User> findAll() {
        return memoryStore.findAll();
    }

    /**
     * Метод проверяет данные на Null.
     * @param id - id пользователя.
     * @param name - имя пользователя
     * @param login - логин пользователя.
     * @param email - почта пользователя.
     * @return - boolean.
     * @throws WrongDataException - исключение при неправилном вводе данных.
     */
    private void checkData(String id, String name, String login, String email) throws WrongDataException {
        if (id == "" || name == "" || login == "" || email == "") {
            throw new WrongDataException("Введены неверные данные");
        }
    }

    /**
     * Метод возвращет id пльзователя ввиде числа.
     * @param id - id пользователя.
     * @return - int.
     * @throws WrongDataException - исключение при неправилном вводе данных.
     */
    private int checkId(String id, boolean checkId) throws WrongDataException {
        int userId;
        try {
            userId = Integer.parseInt(id);
        } catch (NumberFormatException e) {
            throw new WrongDataException("Неверный ID");
        }
        if (checkId && memoryStore.findById(userId) != null) {
            throw new WrongDataException("Пользователь уже существует");
        }
        return userId;
    }
}
