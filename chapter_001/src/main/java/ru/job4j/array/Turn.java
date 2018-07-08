package ru.job4j.array;

/**
 * @author Gorunov Alexandr(mailto:gorunov.sasha85@gmail.com)
 * @version $Id$
 * @since 0.1
 */public class Turn {

    /**
     * Возвращает перевернутый задом напред массив.
     * @param array - массив .
     * @return перевернутый массив.
     */
    public int[] turn(int[] array) {
        for (int i = 0; i < array.length / 2; i++) {
            int temp = array[i];
            array[i] = array[array.length - i - 1];
            array[array.length - i - 1] = temp;
        }
        return array;
    }
}
