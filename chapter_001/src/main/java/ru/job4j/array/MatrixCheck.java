package ru.job4j.array;

/**
 * Проверка диагоналей массива
 */
public class MatrixCheck {
    /**
     * Проверяет идентичность элементов по дагонали массива.
     * @param data - массив.
     * @return - true или false.
     */
    public boolean mono(boolean[][] data) {
        boolean result = true;
        for (int i = 1; i < data.length; i++) {
            if ((data[i][i] != data[0][0])
                    || (data[i][data.length - i - 1] != data[0][data.length - 1])) {
                result = false;
                break;
            }
        }
        return result;
    }
}
