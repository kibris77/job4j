package ru.job4j.sort;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

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
        Set<User> set = new TreeSet<>();
        for (User user : list) {
            set.add(user);
        }
        return set;
    }
}
