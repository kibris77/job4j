package ru.job4j.loop;

/**
 * @author Gorunov Alexandr(mailto:gorunov.sasha85@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class Factorial {
    /**
     * Метод вычисляет факториал числа
     * @param n - число
     * @return Факторил
     */
    public int calc(int n) {
        int fact = 1;
        for (int index = 1; index < n; index++) {
            fact *= index;
        }
        return fact;
    }
}
