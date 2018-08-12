package ru.job4j.list;

import java.util.NoSuchElementException;

/**
 * Класс реализующий простую Очредь.
 * @param <E>
 */
public class SimpleQueue<E> extends DynamicLinkedList<E> {
    /**
     * Метод возвращает элемент из очереди.
     * @return - значение.
     */
    public E poll() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        E result = first.date;
        first = first.next;
        size--;
        return result;
    }

    /**
     * Метод добавляет значение в очередь.
     * @param value - значение.
     */
    public void push(E value) {
        add(value);
    }
}
