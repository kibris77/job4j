package ru.job4j.map;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Класс реализующий простой Хэш Мэп.
 * @param <K>
 * @param <V>
 */
class SimpleMap<K, V> implements Iterable {
    private int capacity = 16;
    private int size = 0;
    private int overSize = 75;
    private Object[] hashmap = new Object[16];

    /**
     * Метод добавляет пару ключ значение в коллекцию.
     * @param key - ключ.
     * @param value - значение.
     * @return - boolean.
     */
    public boolean insert(K key, V value) {
        checkCapacity();
        boolean result = false;
        int index = hash(key);
        if (hashmap[index] == null) {
            hashmap[index] = new Entry<>(key, value);
            size++;
            result = true;
        }
        return result;
    }

    /**
     * Метод возвращает значение по ключу.
     * @param key - ключ.
     * @return - значение.
     */
    public V get(K key) {
        return ((Entry<K, V>) hashmap[hash(key)]).getValue();
    }

    /**
     * Мтеод удаляет элемент из коллекции.
     * @param key - ключ.
     * @return - boolean.
     */
    public boolean delete(K key) {
        boolean result = false;
        int index = hash(key);
        if (hashmap[index] != null) {
            hashmap[index] = null;
            size--;
            result = true;
        }
        return result;
    }

    /**
     * Метод проверяет заполненность хэш таблицы.
     * При заполнении больше 75%. величивает размер в 2 раза.
     */
    private void checkCapacity() {
        if (size > (capacity * overSize / 100)) {
            Object[] temp = hashmap.clone();
            capacity *= 2;
            hashmap = new Object[capacity];
            for (int i = 0; i < temp.length; i++) {
                if (temp[i] != null) {
                    K key = ((Entry<K, V>) temp[i]).getKey();
                    hashmap[hash(key)] = temp[i];
                }
            }
        }
    }

    /**
     * Метод возвращает адресс ячейки в Хэш таблице.
     * @param key - ключ.
     * @return индекс ячейки.
     */
    private int hash(Object key) {
        int h = key.hashCode();
        h ^= (h >>> 20) ^ (h >>> 12);
        return (h ^ (h >>> 7) ^ (h >>> 4)) % capacity;
    }

    /**
     * Метод возвращает кол-во заполненных элементов в Хэш таблице.
     * @return - количество элементов.
     */
    public int getSize() {
        return size;
    }

    /**
     * Метод возвращает итератор.
     * @return - итератор.
     */
    @Override
    public Iterator<Entry<K, V>> iterator() {
        return new MapIterator();
    }

    /**
     * Класс реализует элемент хранящийся в Хэш таблице.
     * @param <K>
     * @param <V>
     */
     class Entry<K, V> {
        private K key;
        private V value;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return this.key;
        }

        public V getValue() {
            return this.value;
        }
    }

    /**
     * Класс реализует итератор для Хэш таблицы.
     */
    class MapIterator implements Iterator<Entry<K, V>> {
        int index = 0;
        @Override
        public boolean hasNext() {
            boolean result = false;
            for (int i = index; i < capacity; i++) {
                if (hashmap[i] != null) {
                    result = true;
                    break;
                }
            }
            return result;
        }

        @Override
        public Entry<K, V> next() {
            Entry<K, V> result = null;
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            for (int i = index; i < capacity; i++) {
                if (hashmap[i] != null) {
                    result = ((Entry<K, V>) hashmap[i]);
                    index = ++i;
                    break;
                }
            }
            return result;
        }
    }
}
