package ru.job4j.pseudo;

/**
 * Класс фигуры квдрат.
 */
public class Square implements Shape {
    /**
     * Метод рисует квадрат.
     * @return строку с квадратом.
     */
    @Override
    public String draw() {
        StringBuilder result = new StringBuilder();
        result.append("*****\n");
        result.append("*   *\n");
        result.append("*   *\n");
        result.append("*   *\n");
        result.append("*****");
        return result.toString();
    }
}
