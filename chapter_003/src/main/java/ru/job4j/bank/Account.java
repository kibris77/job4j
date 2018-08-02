package ru.job4j.bank;

/**
 * Класс описывает счет клиента.
 */
public class Account {
    private double value;
    private String requisites;

    public Account(int value, String requisites) {
        this.value = value;
        this.requisites = requisites;
    }

    public double getValue() {
        return value;
    }

    public String getRequisites() {
        return requisites;
    }

    /**
     * Метод добавляет деньги на счет.
     * @param money - количечтво денег.
     */
    public void addMoney(double money) {
        this.value += money;
    }

    /**
     * Метод вычитает деньги со счета.
     * @param money - количество денег.
     */
    public void subMoney(double money) {
        this.value -= money;
    }
}

