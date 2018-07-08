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
        for (int i = 1; i < data.length; i++) {
            if (data[i] != data[0]) {
                return false;
            }
        }
        return true;
    }
}
