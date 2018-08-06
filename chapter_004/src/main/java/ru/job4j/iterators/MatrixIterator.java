package ru.job4j.iterators;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Итератор для двумерного массива.
 */
public class MatrixIterator implements Iterator {
    private int[][] matrix;
    private int outIndex = 0;
    private int inIndex = 0;

    public MatrixIterator(int[][] array) {
        this.matrix = array;
    }

    @Override
    public boolean hasNext() {
        return outIndex < matrix.length;
    }

    @Override
    public Object next() {
        Object result = null;
        if (matrix.length > 0) {
            result = matrix[outIndex][inIndex++];
            if (inIndex == matrix[outIndex].length) {
                inIndex = 0;
                outIndex++;
            }
        }
        if (result == null) {
            throw new NoSuchElementException();
        }
        return result;
    }
}
