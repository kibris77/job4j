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
        for (int i = 0; i < newArrLength; i++) {
            for (int j = i + 1; j < newArrLength; j++) {
                if (array[i].equals(array[j])) {
                    array[j] = array[newArrLength - 1];
                    newArrLength--;
                    j--;
                }
            }
        }
        return Arrays.copyOf(array, newArrLength);
    }
}
