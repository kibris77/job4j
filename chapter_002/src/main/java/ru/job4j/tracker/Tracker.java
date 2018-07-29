package ru.job4j.tracker;

import java.util.*;

/**
 * Класс трекер заявок.
 */
public class Tracker {
    private List<Item> items = new ArrayList<>();
    private static final Random RN = new Random();

    /**
     * Метод добавляет заявку в хранилище.
     * @param item - заявка
     * @return - добавленная заявка.
     */
    public Item addItem(Item item) {
        item.setId(this.generateId());
        this.items.add(item);
        return item;
    }

    /**
     * Метод заменяет завку на новую по ID.
     * @param id - идентификатор заявки.
     * @param item - заявка.
     */
    public boolean replace(String id, Item item) {
        boolean result = false;
        for (int index = 0; index < this.items.size(); index++) {
            if (this.items.get(index).getId().equals(id)) {
                item.setId(id);
                this.items.set(index, item);
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
        for (Item item : items) {
            if (item.getId().equals(id)) {
                result = item;
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
    public List<Item> findByName(String name) {
        List<Item> result = new ArrayList<>();
        for (Item item : items) {
            if (item.getName().equals(name)) {
                result.add(item);
            }
        }
        return result;
    }

    /**
     * Метод возвращает все заявки.
     * @return - массив заявок.
     */
    public List<Item> findAll() {
        return this.items;
    }

    /**
     * Метод удаляет заявку по ID.
     * @param id - ID заявки.
     */
    public boolean delete(String id) {
        boolean result = false;
        Iterator iterator = this.items.iterator();
        while (iterator.hasNext()) {
            Item item = (Item) iterator.next();
            if (item.getId().equals(id)) {
                iterator.remove();
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
    private String generateId() {
        return String.valueOf(System.currentTimeMillis() + RN.nextInt());
    }
}
