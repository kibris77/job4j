package ru.job4j.tracker;

import java.util.Date;

public class StartUI {
    private static final String ADD = "0";
    private static final String SHOW = "1";
    private static final String EDIT = "2";
    private static final String DELETE = "3";
    private static final String FINDID = "4";
    private static final String FINDNAME = "5";
    private static final String EXIT = "6";

    private final Tracker tracker;
    private final Input input;

    public StartUI(Tracker tracker, Input input) {
        this.tracker = tracker;
        this.input = input;
    }

    public static void main(String[] args) {
        new StartUI(new Tracker(), new ConsoleInput()).init();

    }

    /**
     * Инициазиция данных программы.
     */
    protected void init() {
        boolean exit = true;
        while (exit) {
            this.showMenu();
            String answer = this.input.ask("Введите номер пункта меню:");
            if (ADD.equals(answer)) {
                this.createItem();
            } else if (SHOW.equals(answer)) {
                showAllItems();
            } else if (EDIT.equals(answer)) {
                editItem();
            } else if (DELETE.equals(answer)) {
                deleteItem();
            } else if (FINDID.equals(answer)) {
                findItemId();
            } else if (FINDNAME.equals(answer)) {
                findItemName();
            } else if (EXIT.equals(answer)) {
                exit = false;
            } else {
                System.out.println("Вы ввели не существующий номер!");
                System.out.println("-------------------------------");
            }
        }
    }

    /**
     * Метод отображает пункты меню.
     */
    private void showMenu() {
        String[] menu = new String[]{"MENU",
                                    "0. Add new Item",
                                    "1. Show all items",
                                    "2. Edit item",
                                    "3. Delete item",
                                    "4. Find item by Id",
                                    "5. Find items by name",
                                    "6. Exit Program"};
        for (int index = 0; index < menu.length; index++) {
            System.out.println(menu[index]);
        }
    }

    /**
     * Метод добавляет заявку к хранилищу.
     */
    private void createItem() {
        System.out.println("------------ Добавление новой заявки --------------");
        String name = this.input.ask("Введите имя заявки :");
        String desc = this.input.ask("Введите описание заявки :");
        Item item = new Item(name, desc, System.currentTimeMillis());
        this.tracker.addItem(item);
        System.out.println("------------ Новая заявка с getId : " + item.getId() + "-----------");
    }

    /**
     * Метод отображает все заявки.
     */
    private void showAllItems() {
        Item[] items = tracker.findAll();
        if (items != null) {
            for (Item item : items) {
                System.out.println(item);
            }
        } else {
            System.out.println("Нет текущих заявок.");
            System.out.println("---------------------------------------------------");
        }
    }

    /**
     * Метод редактирует заявку.
     */
    private void editItem() {
        System.out.println("------------ Редактирование заявки --------------");
        String id = this.input.ask("Введите номер заявки :");
        String name = this.input.ask("Введите имя заявки :");
        String desc = this.input.ask("Введите описание заявки :");
        Item item = new Item(name, desc, System.currentTimeMillis());
        if (this.tracker.replace(id, item)) {
            System.out.println("-------- Отредактирована заявка с getId : " + id + "-----------");
        } else {
            System.out.println("------------ Ззаявка с getId : " + id + " не найдена-----------");
        }
    }

    /**
     * Метод удаляет заявку.
     */
    private void deleteItem() {
        System.out.println("------------ Удаление заявки --------------");
        String id = this.input.ask("Введите номер заявки :");
        if (this.tracker.delete(id)) {
            System.out.println("------------ Заявка с getId : " + id + " удалена-----------");
        } else {
            System.out.println("------------ Ззаявка с getId : " + id + " не найдена-----------");
        }
    }

    /**
     * Метод находит заявку по id.
     */
    private void findItemId() {
        System.out.println("---------- Поиск заявки по ID ----------");
        String id = this.input.ask("Введите номер заявки :");
        Item item = tracker.findById(id);
        if (item != null) {
            System.out.println(item);
        } else {
            System.out.println("------------ Ззаявка с getId : " + id + " не найдена-----------");
        }
    }

    /**
     * Метод находит заявку по имени.
     */
    private void findItemName() {
        System.out.println("---------- Поиск заявки по имени ----------");
        String name = this.input.ask("Введите имя заявки :");
        Item[] items = tracker.findByName(name);
        if (items.length > 0) {
            for (Item item : items) {
                System.out.println(item);
            }
        } else {
            System.out.println("------------ Ззаявка с getName : " + name + " не найдена-----------");
        }
    }
}
