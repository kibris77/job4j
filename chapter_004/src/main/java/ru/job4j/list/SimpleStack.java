package ru.job4j.list;

import java.util.NoSuchElementException;

/**
 * Класс реализующий простой Стек.
 * @param <E>
 */
public class SimpleStack<E> extends DynamicLinkedList<E> {
    /**
     * Метод возвращает элемент из стека.
     * @return - значение элемента.
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
     * Метод дообавляет элемент в Стек.
     * @param value - значение.
     */
    public void push(E value) {
        NodeList<E> newLink = new NodeList<>(value);
        newLink.next = this.first;
        first = newLink;
        size++;
    }
}
