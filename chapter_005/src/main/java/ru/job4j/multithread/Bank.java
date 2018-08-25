package ru.job4j.multithread;

public class Bank {
    public static void main(String[] args) {
        Account account = new Account(10000);


        for (int i = 0; i < 10; i++) {
            new Thread(new BankTransaction(account)).start();
            new Thread(new BankCheckBallance(account)).start();
        }
    }
}
