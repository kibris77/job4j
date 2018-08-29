package ru.job4j.threadsafe;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;
import ru.job4j.list.DynamicLinkedList;

import java.util.Iterator;

/**
 * Класс реализющую потокабезопасную коллекци на основе LinkedList.
 * @param <E>
 */
@ThreadSafe
public class ThreadSafeLinkedList<E> implements Iterable<E> {
    @GuardedBy("this")
    private final DynamicLinkedList<E> linkedList = new DynamicLinkedList<>();

    /**
     * Метод добваляет значение в коллекцию.
     * @param value
     */
    public synchronized void add(E value) {
        linkedList.add(value);
    }

    /**
     * Метод возвращает значение по индексу.
     * @param index
     */
    public synchronized E get(int index) {
        return linkedList.get(index);
    }

    /**
     * Метод возвращает итератор.
     * @return
     */
    @Override
    public  Iterator<E> iterator() {
        return new ThreadSafeIterator();
    }

    private class ThreadSafeIterator implements Iterator<E> {
        private final Iterator<E> iterator;

        private ThreadSafeIterator() {
            this.iterator = ThreadSafeLinkedList.this.linkedList.iterator();
        }

        @Override
        public boolean hasNext() {
            synchronized (ThreadSafeLinkedList.this.linkedList) {
                return iterator.hasNext();
            }
        }

        @Override
        public E next() {
            synchronized (ThreadSafeLinkedList.this.linkedList) {
                return iterator.next();
            }
        }
    }
}
