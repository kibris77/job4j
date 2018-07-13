package ru.job4j.loop;

/**
 * @author Gorunov Alexnadr(mailto:gorunov.sasha85@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class Counter {
    /**
     * Метод возвращает сумму всех чётных значений
     * @param start - первый элемент
     * @param finish - последний элемент
     * @return -Сумма четных значений
     */
    public int add(int start, int finish) {
        int sum = 0;
        for (int index = start; index <= finish; index++) {
            if (index % 2 == 0) {
                sum += index++;
            }
        }
        return sum;
    }
}
