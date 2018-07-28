package ru.job4j.list;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс для конвертации массива в List.
 */
public class ConvertMatrix2List {
    /**
     * Метод конвертирует массив в ArrayList.
     * @param array - массив.
     * @return - ArrayList.
     */
    public List<Integer> toList(int[][] array) {
        List<Integer> list = new ArrayList<>();
        for (int[] subArray : array) {
            for (int number : subArray) {
                list.add(number);
            }
        }
        return list;
    }
}

