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
        for (int out = array.length - 1; out > 1; out--) {
            for (int in = 0; in < out; in++) {
                if (array[in] > array[in + 1]) {
                    int temp = array[in];
                    array[in] = array[in + 1];
                    array[in + 1] = temp;
                }
            }
        }
        return array;
    }
}
