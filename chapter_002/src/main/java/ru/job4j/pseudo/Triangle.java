package ru.job4j.pseudo;

/**
 * Класс фигуры треуголльник.
 */
public class Triangle implements Shape {
    /**
     * Метод рисует треугольник.
     * @return строку с треугольником.
     */
    @Override
    public String draw() {
        StringBuilder result = new StringBuilder();
        result.append("  *  ");
        result.append(System.lineSeparator());
        result.append(" *** ");
        result.append(System.lineSeparator());
        result.append("*****");
        result.append(System.lineSeparator());
        return result.toString();
    }
}
