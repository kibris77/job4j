package ru.job4j.tracker;

import java.util.Date;
import java.util.Random;

/**
 * Класс заявка.
 */
public class Item {
    private String id;
    private String name;
    private String description;
    private long create;

    public Item(String name, String description, long create) {
        this.name = name;
        this.description = description;
        this.create = create;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public long getCreate() {
        return create;
    }

    protected void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("------------ Заявка номер-").append(this.getId()).append(" --------------\n");
        result.append("Имя: ").append(this.getName()).append("\n");
        result.append("Описание: ").append(this.getDescription()).append("\n");
        result.append("Дата: ").append(new Date(this.getCreate())).append("\n");
        result.append("-------------------------------------------------------\n");
        return result.toString();
    }
}
