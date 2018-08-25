package ru.job4j.multithread;

/**
 * Класс проверяет балланс аккаунта.
 */
public class BankCheckBallance implements Runnable {
    private Account account;

    public BankCheckBallance(Account account) {
        this.account = account;
    }

    /**
     * Метод выводит на консольл значение балланса.
     */
    public void checkBallance() {
        System.out.println(Thread.currentThread().getName() + " Ballance is " + account.getAmount());
    }

    @Override
    public void run() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        checkBallance();
    }
}
