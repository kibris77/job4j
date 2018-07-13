package ru.job4j.array;

/**
 * @author Gorunov Alexandr(mailto:gorunov.sasha85@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class Check {
    /**
     * Проверяет являються ли эллементы массива одинаковыми.
     * @param data - массив booleqn.
     * @return - boolean.
     */
    public boolean mono(boolean[] data) {
        boolean result = true;
        for (int index = 1; index < data.length; index++) {
            if (data[index] != data[0]) {
                result = false;
                break;
            }
        }
        return result;
    }
}
