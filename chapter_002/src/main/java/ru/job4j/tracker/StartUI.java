package ru.job4j.tracker;

import java.util.Date;

public class StartUI {
    private Tracker tracker;
    private Input input;

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
        MenuTracker menu = new MenuTracker(this.input, this.tracker);
        menu.fillActions();
        String answer = null;
        do {
            System.out.println("MENU");
            menu.show();
            answer = input.ask("Выберите пункт меню.");
            menu.select(Integer.valueOf(answer));
        } while (!answer.equals("6"));
    }
}
