package ru.job4j.multithread;

/**
 * Класс реализует банковский перевод.
 */
public class BankTransaction implements Runnable {
    private Account account;

    public BankTransaction(Account account) {
        this.account = account;
    }

    /**
     * Метод переводит деньги на аккаунт.
     * @param value - значение.
     */
    public void makeTransation(int value) {
        account.changeAmount(value);
    }

    @Override
    public void run() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        makeTransation(5000);
        System.out.println(Thread.currentThread().getName() + " tranfer - " + 5000);
    }
}
