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
        int fi = 0, si = 0, ri = 0;
        while (fi < fist.length && si < second.length) {
            if (fist[fi] < second[si]) {
                result[ri++] = fist[fi++];
            } else {
                result[ri++] = second[si++];
            }
        }
        while (fi < fist.length) {
            result[ri++] = fist[fi++];
        }
        while (si < second.length) {
            result[ri++] = second[si++];
        }
        return result;
    }
}
