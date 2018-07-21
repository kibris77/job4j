package ru.job4j.tracker;

/**
 * Класс ощибка пользователь ввел не существующий пункт меню.
 */
public class MenuOutExeption extends RuntimeException {
    public MenuOutExeption(String msg) {
        super(msg);
    }
}
