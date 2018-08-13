package ru.job4j.set;

import ru.job4j.list.DynamicLinkedList;

import java.util.Iterator;

/**
 * Класс реализующий простой Set на связанном списке.
 * @param <E>
 */
public class SimpleLinkedSet<E> implements Iterable<E> {
    DynamicLinkedList<E> list = new DynamicLinkedList<>();

    /**
     * Метод проверяет дублирование значений.
     * @param value - значение.
     * @return - boolean.
     */
    private boolean noDoubles(E value) {
        boolean result = true;
        for (E data : list) {
            if (value.equals(data)) {
                result = false;
                break;
            }
        }
        return result;
    }

    /**
     * ДОбавляет значение в Set.
     * @param value
     */
    public void add(E value) {
        if (noDoubles(value)) {
            list.add(value);
        }
    }

    /**
     * Возвращет итератор.
     * @return - итератор.
     */
    @Override
    public Iterator<E> iterator() {
        return list.iterator();
    }
}
