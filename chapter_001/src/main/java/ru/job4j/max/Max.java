package ru.job4j.max;

/**
 * @author Gorunov Alexandr (mailto:gorunov.sasha85@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class Max {
    /**
     * Возвращает максимум из двух чисел.
     * @param first - первое число.
     * @param second - второе число.
     * @return Максимум.
     */
    public int max(int first, int second) {
        return first > second ? first : second;
    }

    /**
     * Возвращает максимум из трех чисел.
     * @param first - первое число.
     * @param second - второе число.
     * @param third - третье число
     * @return Максимум.
     */
    public int max(int first, int second, int third) {
        int temp = max(first, second);
        temp = max(temp, third);
        return temp;
    }
}
