package ru.job4j.tracker;

public class ShowAllItems implements UserAction {
    @Override
    public int key() {
        return 1;
    }

    @Override
    public void execute(Input input, Tracker tracker) {
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

    @Override
    public String info() {
        return String.format("%s. %s", this.key(), "Show all items");
    }
}
