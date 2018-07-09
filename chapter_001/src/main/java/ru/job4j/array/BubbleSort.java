package ru.job4j.array;

/**
 * Сортировка пузырьком.
 */
public class BubbleSort {
    /**
     * Меттод сортировки пузырьком.
     * @param array - входной массив.
     * @return - отсортированный массив.
     */
    public int[] sort(int[] array) {
        for (int i = array.length - 1; i > 1; i--) {
            for (int j = 0; j < i; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
        return array;
    }
}
