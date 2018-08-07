package ru.job4j.iterators;

import java.util.Iterator;

/**
 * Класс конвертирует итератор итераторов в один итератор.
 */
public class IteratorOfIterators {
    Iterator<Integer> convert(Iterator<Iterator<Integer>> it) {
        return new Iterator<Integer>() {
            Iterator<Integer> temp = it.next();

            @Override
            public boolean hasNext() {
                boolean result;
                if (temp.hasNext()) {
                    result = true;
                } else {
                    if (it.hasNext()) {
                        temp = it.next();
                    }
                    result = temp.hasNext();
                }
                return result;
            }

            @Override
            public Integer next() {
                int result;
                if (temp.hasNext()) {
                    result = temp.next();
                } else {
                    temp = it.next();
                    result = temp.next();
                }
                return result;
            }
        };
    }
}
