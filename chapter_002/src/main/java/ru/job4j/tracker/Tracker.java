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
    public void replace(String id, Item item) {
        for (int index = 0; index != position; index++) {
            if (items[index].getId().equals(id)) {
                item.setId(id);
                items[index] = item;
                break;
            }
        }
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
    public Item findByName(String name) {
        Item result = null;
        for (int index = 0; index != position; index++) {
            if (items[index].getName().equals(name)) {
                result = items[index];
                break;
            }
        }
        return result;
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
    public void delete(String id) {
        for (int index = 0; index != position; index++) {
            if (items[index].getId().equals(id)) {
                System.arraycopy(items, index + 1, items, index, position - index - 1);
                position--;
                break;
            }
        }
    }

    /**
     * Метод генерирует уникальный ключ.
     * @return - уникальный ID завки.
     */
    String generateId() {
        return String.valueOf(System.currentTimeMillis() + RN.nextInt());
    }
}
