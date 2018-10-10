package ru.job4j.tracker;

import java.util.Date;

public class StartUI {
    private Tracker tracker;
    private Input input;
    private int[] range;
    private boolean stopProgram = false;

    public StartUI(Tracker tracker, Input input) {
        this.tracker = tracker;
        this.input = input;
    }

    public static void main(String[] args) {
        try (Tracker tracker = new Tracker(new Config());) {
            new StartUI(tracker, new ValidateInput(new ConsoleInput())).init();
        }
    }

    /**
     * Инициазиция данных программы.
     */
    protected void init() {
        MenuTracker menu = new MenuTracker(this.input, this.tracker);
        menu.fillActions(this);
        range = menu.getMenuNumbers();
        int answer = -1;
        do {
            System.out.println("MENU");
            menu.show(menuitem -> System.out.println(menuitem));
            answer = input.ask("Выберите пункт меню.", range);
            menu.select(answer);
        } while (!stopProgram);
    }

    /**
     * Метод завершения программы.
     */
    public void stop() {
        this.stopProgram = true;
    }
}
