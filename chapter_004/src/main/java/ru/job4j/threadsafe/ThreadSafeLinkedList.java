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
    public synchronized Iterator<E> iterator() {
        return copy(this.linkedList).iterator();
    }

    private DynamicLinkedList<E> copy(DynamicLinkedList<E> linkedList) {
        DynamicLinkedList<E> result = new DynamicLinkedList<>();
        for (E unit : linkedList) {
            result.add(unit);
        }
        return result;
    }
}
