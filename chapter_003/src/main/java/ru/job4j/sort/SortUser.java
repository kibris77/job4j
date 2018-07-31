package ru.job4j.sort;

import java.util.*;

/**
 * Класс сортирует User по возрасту.
 */
public class SortUser {
    /**
     * Метод возвращает TreeSet отсортированный по возрасту.
     * @param list - список пользователей.
     * @return - TreeSet.
     */
    public Set<User> sort(List<User> list) {
         return new TreeSet<>(list);
    }

    /**
     * Метод сортирует List по длинне имени.
     * @param list - List.
     * @return - отстортированный List.
     */
    public List<User> sortNameLength(List<User> list) {
        List<User> result = new ArrayList<>(list);
        Collections.sort(result, new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                return Integer.compare(o1.getName().length(), o2.getName().length());
            }
        });
        return result;
    }

    /**
     * Метод сортирует сначала по имени, потом по возрасту.
     * @param list -List.
     * @return отсортированный List.
     */
    public List<User> sortByAllFields(List<User> list) {
        List<User> result = new ArrayList<>(list);
        Collections.sort(result, new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                int compareRes = 0;
                compareRes = o1.getName().compareTo(o2.getName());
                if (compareRes == 0) {
                    compareRes = Integer.compare(o1.getAge(), o2.getAge());
                }
                return compareRes;
            }
        });
        return result;
    }
}
