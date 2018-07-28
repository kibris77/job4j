package ru.job4j.list;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс конвертирует коллекцию List в двумерный массив с заданным количеством рядов.
 */
public class ConvertList2Array {

    /**
     * Метод конвертирует List в дыумерный массив.
     * @param list - коллекция типа List.
     * @param rows - число строк в выходном массиве.
     * @return двумерный массив.
     */
    public int[][] toArray(List<Integer> list, int rows) {
        int cells = list.size() % rows == 0 ? list.size() / rows : list.size() / rows + 1;
        int[][] array = new int[rows][cells];
        int in = 0;
        int out = 0;
        for (Integer num : list) {
            array[out][in] = num;
            if (in < cells - 1) {
                in++;
            } else {
                out++;
                in = 0;
            }
            if (out == rows) {
                break;
            }
        }
        return array;
    }

    /**
     * Метод конвретирует список массивов в один список.
     * @param list - список массивов.
     * @return - список.
     */
    public List<Integer> convert(List<int[]> list) {
        List<Integer> result = new ArrayList<>();
        for (int[] array : list) {
            for (int number : array) {
                result.add(number);
            }
        }
        return result;
    }
}
