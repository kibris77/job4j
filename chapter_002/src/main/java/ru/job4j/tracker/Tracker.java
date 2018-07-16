package ru.job4j.tracker;

import java.util.Arrays;
import java.util.Random;

/**
 * Класс трекер заявок.
 */
public class Tracker {
    private Item[] items = new Item[100];
    private int position = 0;
    private static final Random RN = new Random();

    /**
     * Метод добавляет заявку в хранилище.
     * @param item - заявка
     * @return - добавленная заявка.
     */
    public Item addItem(Item item) {
        item.setId(this.generateId());
        this.items[position++] = item;
        return item;
    }

    /**
     * Метод заменяет завку на новую по ID.
     * @param id - идентификатор заявки.
     * @param item - заявка.
     */
    public boolean replace(String id, Item item) {
        boolean result = false;
        for (int index = 0; index != position; index++) {
            if (items[index].getId().equals(id)) {
                item.setId(id);
                items[index] = item;
                result = true;
                break;
            }
        }
        return result;
    }

    /**
     * Метод возвращает заявку по ID.
     * @param id - ID заявки.
     * @return - заявка.
     */
    public Item findById(String id) {
        Item result = null;
        for (int index = 0; index != position; index++) {
            if (items[index].getId().equals(id)) {
                result = items[index];
                break;
            }
        }
        return result;
    }

    /**
     * Метод возвращает заявку по имени.
     * @param name - имя заявки.
     * @return - заявка.
     */
    public Item[] findByName(String name) {
        Item[] result = new Item[position];
        int resIndex = 0;
        for (int index = 0; index != position; index++) {
            if (items[index].getName().equals(name)) {
                result[resIndex++] = items[index];
            }
        }
        return Arrays.copyOf(result, resIndex);
    }

    /**
     * Метод возвращает все заявки.
     * @return - массив заявок.
     */
    public Item[] findAll() {
        Item[] result = null;
        if (position != 0) {
            result = Arrays.copyOf(items, position);
        }
        return result;
    }

    /**
     * Метод удаляет заявку по ID.
     * @param id - ID заявки.
     */
    public boolean delete(String id) {
        boolean result = false;
        for (int index = 0; index != position; index++) {
            if (items[index].getId().equals(id)) {
                System.arraycopy(items, index + 1, items, index, position - index - 1);
                position--;
                result = true;
                break;
            }
        }
        return result;
    }

    /**
     * Метод генерирует уникальный ключ.
     * @return - уникальный ID завки.
     */
    String generateId() {
        return String.valueOf(System.currentTimeMillis() + RN.nextInt());
    }
}
