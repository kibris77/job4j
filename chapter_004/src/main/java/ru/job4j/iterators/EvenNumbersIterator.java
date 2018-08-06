package ru.job4j.iterators;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Итератор для получпения четных чисел из массива int.
 */
public class EvenNumbersIterator implements Iterator {
    private int[] array;
    private int index = 0;

    public EvenNumbersIterator(int[] array) {
        this.array = array;
    }

    @Override
    public boolean hasNext() {
        boolean result = false;
        for (int i = index; i < array.length; i++) {
            if (array[i] % 2 == 0) {
                result = true;
                break;
            }
        }
        return result;
    }

    @Override
    public Object next() {
        Object result = null;
        if (array.length > 0) {
            while (index < array.length) {
                if (array[index] % 2 == 0) {
                    result = array[index++];
                    break;
                } else {
                    index++;
                }
            }
        }
        if (result == null) {
            throw new NoSuchElementException();
        }
        return result;
    }
}
