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

    private void init() {
        boolean exit = true;
        while (exit) {
            this.showMenu();
            String answer = this.input.ask("Введите номер пункта меню:");
            if (ADD.equals(answer)) {
                this.createItem();
            } else if (SHOW.equals(answer)) {
                showAllItems();
            } else if (EDIT.equals(answer)) {

            } else if (DELETE.equals(answer)) {

            } else if (FINDID.equals(answer)) {

            } else if (FINDNAME.equals(answer)) {

            } else if (EXIT.equals(answer)) {
                exit = false;
            } else {
                System.out.println("Вы ввели не существующий номер!");
                System.out.println("-------------------------------");
            }
        }

    }

    private void showMenu(){
        String[] menu = new String[]{"MENU",
                                    "0. Add new Item",
                                    "1. Show all items",
                                    "2. Edit item",
                                    "3. Delete item",
                                    "4. Find item by Id",
                                    "5. Find items by name",
                                    "6. Exit Program"};
        for (int index = 0; index < menu.length; index++){
            System.out.println(menu[index]);
        }
    }

    private void createItem() {
        System.out.println("------------ Добавление новой заявки --------------");
        String name = this.input.ask("Введите имя заявки :");
        String desc = this.input.ask("Введите описание заявки :");
        Item item = new Item(name, desc, System.currentTimeMillis());
        this.tracker.addItem(item);
        System.out.println("------------ Новая заявка с getId : " + item.getId() + "-----------");
    }

    private void showAllItems() {
        Item[] items = tracker.findAll();
        if (items != null) {
            for (Item item : items) {
                System.out.println("------------ Заявка номер-" + item.getId() + " --------------");
                System.out.println("Имя: " + item.getName());
                System.out.println("Описание: " + item.getDescription());
                System.out.println("Дата: " + new Date(item.getCreate()));
                System.out.println("-------------------------------------------------------");
            }
        } else {
            System.out.println("Нет текущих заявок.");
            System.out.println("---------------------------------------------------");

        }
    }
}
