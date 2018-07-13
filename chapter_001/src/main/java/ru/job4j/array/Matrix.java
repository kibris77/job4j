package ru.job4j.array;

/**
 * Таблица умножения
 */
public class Matrix {
    /**
     * Метод возвращает таблицу умножения размера size().
     * @param size - размер таблицы.
     * @return - двумерный массив.
     */
    public int[][] multiple(int size) {
        int[][] result = new int[size][size];
        for (int out = 0; out < size; out++) {
            for (int in = 0; in < size; in++) {
                result[out][in] = (out + 1) * (in + 1);
            }
        }
        return result;
    }
}
