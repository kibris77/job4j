package ru.job4j.bank.exeptions;

public class UserExistExeption extends Exception {
    public UserExistExeption(String message) {
        super(message);
    }
}
