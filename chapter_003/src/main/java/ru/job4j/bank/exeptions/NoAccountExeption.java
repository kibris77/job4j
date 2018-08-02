package ru.job4j.bank.exeptions;

public class NoAccountExeption extends Exception {
    public NoAccountExeption(String message) {
        super(message);
    }
}
