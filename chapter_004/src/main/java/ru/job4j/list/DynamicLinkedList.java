package ru.job4j.list;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Класс коллекция на основе связанного списка.
 * @param <E>
 */
public class DynamicLinkedList<E> implements SimpleContainer<E>, Iterable<E> {
    NodeList<E> first;
    NodeList<E> last;
    static int modCount = 0;
    int size = 0;

    /**
     * Метод добавляет эллемент в коллекцию.
     * @param value - значение.
     */
    @Override
    public void add(E value) {
        NodeList<E> newLink = new NodeList<>(value);
        if (this.size == 0) {
            first = newLink;
            last = newLink;
        } else {
            last.next = newLink;
            last = newLink;
        }
        this.size++;
        modCount++;
    }

    /**
     * Метод находит эллемент в коллекции по индексу.
     * @param index - индекс.
     * @return - значение.
     */
    @Override
    public E get(int index) {
        chechIndex(index);
        NodeList<E> result = this.first;
        for (int i = 0; i < index; i++) {
            result = result.next;
        }
        return result.date;
    }

    /**
     * Метод проверяет индекс.
     * @param index - индекс.
     */
    private void chechIndex(int index) {
        if (index < 0 || index > this.size - 1) {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    /**
     * Класс определяет элемент входящий в список.
     * @param <E>
     */
    static class NodeList<E> {
        E date;
        NodeList<E> next;

        NodeList(E date) {
            this.date = date;
        }
    }

    /**
     * Метод возвращает интератор.
     * @return
     */
    @Override
    public Iterator<E> iterator() {
        return new DynmicLinkedIterator();
    }

    /**
     * Класс итератор для коллекции.
     */
    private class DynmicLinkedIterator implements Iterator<E> {
        private int index = 0;
        private int expectedModCount;

        public DynmicLinkedIterator() {
            this.expectedModCount = modCount;
        }

        @Override
        public boolean hasNext() {
            chechModifications();
            return index < size;
        }

        @Override
        public E next() {
            chechModifications();
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return get(index++);
        }

        /**
         * Метод проверяет измнение коллекции из других потоков.
         */
        private void chechModifications() {
            if (expectedModCount != modCount) {
                throw new ConcurrentModificationException();
            }
        }
    }
}
