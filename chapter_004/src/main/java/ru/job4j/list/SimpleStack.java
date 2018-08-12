package ru.job4j.list;

import java.util.NoSuchElementException;

/**
 * Класс реализующий простой Стек.
 * @param <E>
 */
public class SimpleStack<E> {
    private DynamicLinkedList<E> list = new DynamicLinkedList<>();
    /**
     * Метод возвращает элемент из стека.
     * @return - значение элемента.
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
     * Метод дообавляет элемент в Стек.
     * @param value - значение.
     */
    public void push(E value) {
        DynamicLinkedList.NodeList<E> newLink = new DynamicLinkedList.NodeList<>(value);
        newLink.next = list.first;
        list.first = newLink;
        list.size++;
    }
}
