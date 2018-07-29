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
        new StartUI(new Tracker(), new ValidateInput(new ConsoleInput())).init();

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
            menu.show();
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
