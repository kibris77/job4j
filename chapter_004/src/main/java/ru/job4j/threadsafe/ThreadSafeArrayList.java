package ru.job4j.threadsafe;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;
import ru.job4j.list.DynamicArrayList;

import java.util.Iterator;

/**
 * Класс реализющую потокабезопасную коллекци на основе ArrayList.
 * @param <E>
 */
@ThreadSafe
public class ThreadSafeArrayList<E> implements Iterable<E> {
    @GuardedBy("this")
    private final DynamicArrayList<E> arrayList = new DynamicArrayList<>();

    /**
     * Метод добваляет значение в коллекцию.
     * @param value
     */
    public synchronized void add(E value) {
        arrayList.add(value);
    }

    /**
     * Метод возвращает значение по индексу.
     * @param index
     */
    public synchronized E get(int index) {
        return arrayList.get(index);
    }

    /**
     * Метод возвращает итератор.
     * @return
     */
    @Override
    public synchronized Iterator<E> iterator() {
        return copy(this.arrayList).iterator();
    }

    private DynamicArrayList<E> copy(DynamicArrayList<E> arrayList) {
        DynamicArrayList<E> result = new DynamicArrayList<>();
        for (E unit : arrayList) {
            result.add(unit);
        }
        return result;
    }
}
