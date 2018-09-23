package ru.job4j.diapason;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 * Возвращает список результатов в заданном диапазоне.
 */
public class Calculate {
    /**
     * Метод возвращает результат вычислений заданной функции.
     * @param start начаело дивазона.
     * @param end - конец диапазона.
     * @param func - функция
     * @return - List.
     */
    public List<Double> diapason(int start, int end, Function<Double, Double> func) {
        List<Double> result = new ArrayList<>();
        for (int i  = start; i <= end; i++) {
            result.add(func.apply((double) i));
        }
        return result;
    }
}
