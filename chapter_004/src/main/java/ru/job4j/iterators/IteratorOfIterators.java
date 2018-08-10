package ru.job4j.iterators;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Класс конвертирует итератор итераторов в один итератор.
 */
public class IteratorOfIterators {
    Iterator<Integer> convert(Iterator<Iterator<Integer>> it) {
        return new Iterator<Integer>() {
            Iterator<Integer> temp = it.next();

            @Override
            public boolean hasNext() {
                boolean result = false;
                if (temp.hasNext()) {
                    result = true;
                } else {
                    while (it.hasNext()) {
                        temp = it.next();
                        result = temp.hasNext();
                        if (result) {
                            break;
                        }
                    }
                }
                return result;
            }

            @Override
            public Integer next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return temp.next();
            }
        };
    }
}
