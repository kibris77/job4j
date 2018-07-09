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
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                result[i][j] = (i + 1) * (j + 1);
            }
        }
        return result;
    }
}
