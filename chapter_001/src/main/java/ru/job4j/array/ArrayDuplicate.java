package ru.job4j.array;

import java.util.Arrays;

/**
 * Дубликаты в массиве
 */
public class ArrayDuplicate {
    /**
     * Возвращает массив без дубликатов.
     * @param array - исходный массив.
     * @return массив.
     */
    public String[] remove(String[] array) {
        int newArrLength = array.length;
        for (int out = 0; out < newArrLength; out++) {
            for (int in = out + 1; in < newArrLength; in++) {
                if (array[out].equals(array[in])) {
                    array[in] = array[newArrLength - 1];
                    newArrLength--;
                    in--;
                }
            }
        }
        return Arrays.copyOf(array, newArrLength);
    }
}
