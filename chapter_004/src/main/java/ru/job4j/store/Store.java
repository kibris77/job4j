package ru.job4j.store;

import java.util.List;

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
        Info info = new Info();
        int privLength = previous.size();
        int currLength = current.size();
        int[][] lcsMatrix = new int[(privLength + 1)][(currLength + 1)];
        for (int i = 0; i < privLength; i++) {
            for (int j = 0; j < currLength; j++) {
                if (previous.get(i).id == current.get(j).id) {
                    lcsMatrix[i + 1][j + 1] = lcsMatrix[i][j] + 1;
                } else {
                    lcsMatrix[i + 1][j + 1] = Math.max(lcsMatrix[i + 1][j], lcsMatrix[i][j + 1]);
                }
            }
        }
        int i = 0;
        int j = 0;
        for (i = privLength - 1, j = currLength - 1; i >= 0 && j >= 0;) {
            if (previous.get(i).id == current.get(j).id) {
                if (!previous.get(i).name.equals(current.get(j).name)) {
                    info.changed++;
                }
                --i;
                --j;
            } else if (lcsMatrix[i + 1][j] > lcsMatrix[i][j + 1]) {
                --j;
                info.added++;
            } else {
                --i;
                info.deleted++;
            }
        }
        if (i > -1) {
            info.deleted = info.deleted + i + 1;
        }
        if (j > -1) {
            info.added = info.added + j + 1;
        }
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
