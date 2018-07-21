package ru.job4j.tracker;

/**
 * Класс ошибка пользовтаель ввел не номер пункта меню.
 */
public class WrongInputExeption extends RuntimeException {
    public WrongInputExeption(String message) {
        super(message);
    }
}
