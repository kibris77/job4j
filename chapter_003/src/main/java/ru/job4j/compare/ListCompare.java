package ru.job4j.compare;

import java.util.Comparator;

/**
 * Класс реализует компаратор строк.
 */
public class ListCompare implements Comparator<String> {
    @Override
    public int compare(String left, String right) {
        int result = 0;
        int length = left.length() < right.length() ? left.length() : right.length();
        for (int index = 0; index < length; index++) {
            result = Character.compare(left.charAt(index), right.charAt(index));
            if (result != 0) {
                break;
            }
        }
        if (result == 0) {
            result = Integer.compare(left.length(), right.length());
        }
        return result;
    }
}


