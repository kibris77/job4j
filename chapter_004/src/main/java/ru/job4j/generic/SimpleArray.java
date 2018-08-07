package ru.job4j.generic;


import java.util.Iterator;

/**
 * Класс обертка для массива.
 * @param <T> тип элемента хранящегося в массиве.
 */
public class SimpleArray<T> implements Iterable<T> {
    private Object[] array = new Object[10];
    private int index = 0;

    /**
     * Метод добавляет элемнет в массив.
     * @param model - значение элемента.
     */
    public void add(T model) {
        if (index == array.length) {
            throw new UnsupportedOperationException();
        }
        array[index++] = model;
    }

    /**
     * Метод устанавливает элемент в массиве.
     * @param index - инжекс элемнета.
     * @param model - значенние элемента.
     */
    public void set(int index, T model) {
        if (index > this.index - 1) {
            throw new UnsupportedOperationException();
        }
        array[index] = model;
    }

    /**
     * Метод удаляет элемент из массива.
     * @param index - индес элемнета.
     */
    public void delete(int index) {
        if (index > this.index - 1) {
            throw new UnsupportedOperationException();
        }
        System.arraycopy(array, index + 1, array, index, this.array.length - index - 1);
        this.index--;
    }

    /**
     * Метод возвращает элемент из массива.
     * @param index - индекс элента.
     * @return - значение элемента.
     */
    public T get(int index) {
        if (index > this.index - 1) {
            throw new UnsupportedOperationException();
        }
        return (T) array[index];
    }

    /**
     * Метод возвращает итератор коллкции.
     * @return - итератор.
     */
    @Override
    public Iterator<T> iterator() {
        return new SimpleIterator();
    }

    /**
     * Класс итератор для коллекции.
     */
    private class SimpleIterator implements Iterator<T> {
        int innerIndex = 0;
        @Override
        public boolean hasNext() {
            return innerIndex < index;
        }

        @Override
        public T next() {
            return (T) array[innerIndex++];
        }
    }
}
