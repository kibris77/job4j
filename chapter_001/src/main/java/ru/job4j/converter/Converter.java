package ru.job4j.converter;

/**
 * Конвертер валюты
 */
public class Converter {
    private final int dollarCourse = 60;
    private final int euroCourse = 70;

    /**
     * Конвертертируем рубли в евро.
     * @param value рубли.
     * @return Евро.
     */
    public int rubleToEuro(int value) {
        return value / euroCourse;
    }

    /**
     * Конвертертируем рубли в евро.
     * @param value рубли.
     * @return Доллары.
     */
    public int rubleToDollar(int value) {
        return value / dollarCourse;
    }

    /**
     * Конвертертируем евро в рубли.
     * @param value Евро.
     * @return рубли.
     */
    public int euroToRuble(int value) {
        return value * euroCourse;
    }

    /**
     * Конвертертируем евро в рубли.
     * @param value Доллары.
     * @return рубли.
     */
    public int dollarToRuble(int value) {
        return value * dollarCourse;
    }

}