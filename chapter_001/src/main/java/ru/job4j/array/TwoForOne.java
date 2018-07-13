package ru.job4j.array;

/**
 * Слияние 2 отсортированных массивов в один.
 */
public class TwoForOne {
    /**
     * Объединяет два остсортированных массива в один
     * @param fist - первый отсортированный массив.
     * @param second - второй отсортированный массив.
     * @return - объединенный массив.
     */
    public static int[] merge(int[] fist, int[] second) {
        int[] result = new int[fist.length + second.length];
        int i = 0, j = 0, k = 0;
        while (i < fist.length && j < second.length) {
            if (fist[i] < second[j]) {
                result[k++] = fist[i++];
            } else {
                result[k++] = second[j++];
            }
        }
        while (i < fist.length) {
            result[k++] = fist[i++];
        }
        while (j < second.length) {
            result[k++] = second[j++];
        }
        return result;
    }
}
