package ru.job4j.bank.exeptions;

public class NotEnougthMoneyExeption extends Exception {
    public NotEnougthMoneyExeption(String message) {
        super(message);
    }
}
