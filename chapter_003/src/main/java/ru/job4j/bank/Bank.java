package ru.job4j.bank;

import ru.job4j.bank.exeptions.NoAccountExeption;
import ru.job4j.bank.exeptions.NotEnougthMoneyExeption;
import ru.job4j.bank.exeptions.UserExistExeption;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Класс реализует работу Банка.
 */
public class Bank {
    private final HashMap<User, ArrayList<Account>> map = new HashMap<>();

    public HashMap<User, ArrayList<Account>> getMap() {
        return map;
    }

    /**
     * Метод добавляет нового клиента.
     * @param user - клиент.
     * @throws UserExistExeption - если такой клиент уже есть.
     */
    public void addUser(User user) throws UserExistExeption {
        if (this.map.putIfAbsent(user, new ArrayList<>()) != null) {
            throw new UserExistExeption("Пользователь уже существует");
        }
    }

    /**
     * Метод удаляет клиента.
     * @param user - клиент.
     */
    public void deleteUser(User user) {
        this.map.remove(user);
    }

    /**
     * Метод добавляет счет клиенту.
     * @param passport - номер пасспорта.
     * @param account - счет.
     */
    public void addAccountToUser(String passport, Account account) {
        for (Map.Entry<User, ArrayList<Account>> userAcc : map.entrySet()) {
            if (userAcc.getKey().getPassport().equals(passport)) {
                userAcc.getValue().add(account);
                break;
            }
        }
    }

    /**
     * Метод удаляет счет у клиента.
     * @param passport - номер паспорта.
     * @param account - счет.
     * @throws NoAccountExeption - такого счета нет.
     */
    public void deleteAccountFromUser(String passport, Account account) throws NoAccountExeption {
        for (Map.Entry<User, ArrayList<Account>> userAcc : map.entrySet()) {
            if (userAcc.getKey().getPassport().equals(passport)) {
                int index = userAcc.getValue().lastIndexOf(account);
                if (index == -1) {
                    throw new NoAccountExeption("Такого аккаунта не существует");
                }
                userAcc.getValue().remove(index);
                break;
            }
        }
    }

    /**
     * Метод возвращает список счетов клиента.
     * @param passport - номер паспорта.
     * @return - спиок счетов.
     */
    public List<Account> getUserAccounts(String passport) {
        List<Account> result = new ArrayList<>();
        for (Map.Entry<User, ArrayList<Account>> userAcc : map.entrySet()) {
            if (userAcc.getKey().getPassport().equals(passport)) {
                result.addAll(userAcc.getValue());
                break;
            }
        }
        return result;
    }

    /**
     * Метод возвращает конкретный счет клиента.
     * @param passport - номер паспорта.
     * @param requisites - реквизиты.
     * @return - счет.
     */
    public Account getCurrentAccount(String passport, String requisites) {
        List<Account> accounts = getUserAccounts(passport);
        Account result = null;
        for (Account account : accounts) {
            if (account.getRequisites().equals(requisites)) {
                result = account;
                break;
            }
        }
        return result;
    }

    /**
     * Метод переводит деньги с одного счяета на другой.
     * @param srcPassport - нмер паспорта клиента который переводит.
     * @param srcRequisite - номер счета откуда переводим.
     * @param destPassport - номер паспорта клиента кому переводим.
     * @param dstRequisite - номер счета куда переводим.
     * @param amount - количестао денег.
     * @return - boolean.
     * @throws NoAccountExeption - одного из аккаунтов не существует.
     * @throws NotEnougthMoneyExeption - не достаточно денег на счету.
     */
    public boolean transferMoney(String srcPassport, String srcRequisite,
                                  String destPassport, String dstRequisite,
                                  double amount) throws NoAccountExeption, NotEnougthMoneyExeption {
        Account srcAccount = getCurrentAccount(srcPassport, srcRequisite);
        Account dstAccount = getCurrentAccount(destPassport, dstRequisite);
        if (srcAccount == null || dstAccount == null) {
            throw new NoAccountExeption("Операция невозможна. Одного из счетов не существует");
        }
        if (srcAccount.getValue() < amount) {
            throw new NotEnougthMoneyExeption("Операция невозможна. Недостаточно денег на счету.");
        }
        double dstMoney = dstAccount.getValue();
        srcAccount.subMoney(amount);
        dstAccount.addMoney(amount);
        return (dstAccount.getValue() == (dstMoney + amount));
    }
}
