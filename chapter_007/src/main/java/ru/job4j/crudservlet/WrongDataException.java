package ru.job4j.crudservlet;

/**
 * Клас исключение вызывается при непрвалином http запросе.
 */
public class WrongDataException extends Exception {
    public WrongDataException(String message) {
        super(message);
    }
}
