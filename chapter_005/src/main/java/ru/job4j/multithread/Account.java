package ru.job4j.multithread;

/**
 * Класс реализующий банковский аккаунт.
 */
public class Account {
    private int amount;

    public Account(int amaount) {
        this.amount = amaount;
    }

    public int getAmount() {
        return amount;
    }

    /**
     * Добавляет значение к аккаунту.
     * @param value - значение.
     */
    public void changeAmount(int value) {
        this.amount += value;
    }
}
