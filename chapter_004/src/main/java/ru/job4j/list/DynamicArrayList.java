package ru.job4j.list;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Динамическая рпсширяемая коллекция на основе для массива.
 * @param <E>
 */
public class DynamicArrayList<E> implements SimpleContainer<E>, Iterable<E> {
    private Object[] array = new Object[10];
    private int index = 0;
    private int modCount = 0;

    /**
     * Метод добавляет эллемент в коллекцию.
     * @param value - значение.
     */
    @Override
    public void add(E value) {
        modCount++;
        checkArray();
        array[index++] = value;
    }

    /**
     * Метод возвращает значение по индексу.
     * @param index - индекс.
     * @return - значение.
     */
    @Override
    public E get(int index) {
        chechIndex(index);
        return (E) array[index];
    }

    /**
     * Метод возвращает размер коллекции.
     * @return - размер коллекции.
     */
    public int getSize() {
        return index;
    }

    /**
     * Метод проверяет исходный массив, если он заполнен увеличивает его в 2 раза.
     */
    private void checkArray() {
        if (index == array.length) {
            Object[] temp = array.clone();
            array = new Object[index * 2];
            System.arraycopy(temp, 0, array, 0, index);
        }
    }

    /**
     * Метод проверяет попадние индекса в границы коллекции.
     */
    private void chechIndex(int ind) {
        if (ind < 0 || ind > this.index - 1) {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    /**
     * Метод возвращет итератор коллеккции.
     * @return - итератор.
     */
    @Override
    public Iterator<E> iterator() {
        return new DynamicArrayIterator();
    }

    /**
     * Итератор для коллекции.
     */
    private class DynamicArrayIterator implements Iterator<E> {
        private int iteratorIndex = 0;
        private int expectedModCount;

        public DynamicArrayIterator() {
            expectedModCount = modCount;
        }

        @Override
        public boolean hasNext() {
            chechModifications();
            return iteratorIndex < index;
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return (E) array[iteratorIndex++];
        }

        /**
         * Метод проверяет изменения в коллкции из других потоков.
         */
        private void chechModifications() {
            if (expectedModCount != modCount) {
                throw new ConcurrentModificationException();
            }
        }
    }
}
