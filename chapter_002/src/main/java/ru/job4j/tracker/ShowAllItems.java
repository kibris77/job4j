package ru.job4j.tracker;

import java.util.List;

public class ShowAllItems extends BaseAction {
    public ShowAllItems(int key, String name) {
        super(key, name);
    }

    @Override
    public void execute(Input input, Tracker tracker) {
        List<Item> items = tracker.findAll();
        if (!items.isEmpty()) {
            for (Item item : items) {
                System.out.println(item);
            }
        } else {
            System.out.println("Нет текущих заявок.");
            System.out.println("---------------------------------------------------");
        }
    }
}
