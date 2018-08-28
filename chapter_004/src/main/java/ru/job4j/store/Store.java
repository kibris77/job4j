package ru.job4j.store;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

/**
 * Класс для вычисления измнениний в массиве.
 */
public class Store {
    /**
     * Метод возвращает кол-во изменений в текущей коллекции по сравению с исходной.
     * @param previous - исходный список.
     * @param current - текущий список.
     * @return - объект класса Info.
     */
    public Info diff(List<User> previous, List<User> current) {
        HashMap<Integer, User> usersMap = new HashMap<>();
        Info info = new Info();
        for (User user : previous) {
            usersMap.put(user.id, user);
        }
        for (User user : current) {
            if (usersMap.containsKey(user.id)) {
                if (!usersMap.get(user.id).equals(user)) {
                    info.changed++;
                }
            } else  {
                info.added++;
            }
        }
        info.deleted = previous.size() + info.added - current.size();
        return info;
    }

    /**
     * Тестовый класс для объекта коллекции.
     */
    public static class User {
        int id;
        String name;

        public User(int id, String name) {
            this.id = id;
            this.name = name;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (!(o instanceof User)) {
                return false;
            }
            User user = (User) o;
            return id == user.id
                    && Objects.equals(name, user.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, name);
        }
    }

    /**
     * Класс содержит информацию об изменениях в коллекции.
     */
    public static class Info {
        int added;
        int deleted;
        int changed;

        @Override
        public String toString() {
            return String.format("Добавлено: %s, Удалено: %s, Изменено %s", added, deleted, changed);
        }
    }
}
