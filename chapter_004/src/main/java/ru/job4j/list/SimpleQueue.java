package ru.job4j.list;

import java.util.NoSuchElementException;

/**
 * Класс реализующий простую Очредь.
 * @param <E>
 */
public class SimpleQueue<E> {
    private DynamicLinkedList<E> list = new DynamicLinkedList<>();

    /**
     * Метод возвращает элемент из очереди.
     * @return - значение.
     */
    public E poll() {
        if (list.size == 0) {
            throw new NoSuchElementException();
        }
        E result = list.first.date;
        list.first = list.first.next;
        list.size--;
        return result;
    }

    /**
     * Метод добавляет значение в очередь.
     * @param value - значение.
     */
    public void push(E value) {
        list.add(value);
    }
}
