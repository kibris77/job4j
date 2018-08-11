package ru.job4j.list;

public interface SimpleContainer<E> {
    void add(E value);
    E get(int index);
}
