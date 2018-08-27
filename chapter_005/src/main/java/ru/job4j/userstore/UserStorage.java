package ru.job4j.userstore;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.HashMap;
import java.util.Map;

/**
 * Класс хранит потокобезопасную коллекцию пользователей.
 */
@ThreadSafe
public class UserStorage {
    @GuardedBy("this")
    private Map<Integer, User> users = new HashMap<>();

    /**
     * Метод добавляет поьзователя в коллекцию.
     * @param user - пользователь.
     * @return boolean.
     */
    public synchronized boolean add(User user) {
        return users.put(user.getId(), user) == null;
    }

    /**
     * метод удаляет пользователя из коллекци.
     * @param user - пользователь.
     * @return boolean.
     */
    public synchronized boolean delete(User user) {
        return users.remove(user.getId()) != null;
    }

    /**
     * Метод редактирует ammount пользователя.
     * @param user - пользователь.
     * @param ammount - сумма.
     * @return boolean.
     */
    public synchronized boolean update(User user, int ammount) {
        boolean result = false;
        User updtUser = users.get(user.getId());
        if (updtUser != null) {
            updtUser.setAmount(ammount);
            result = true;
        }
        return result;
    }

    /**
     * Метод переводит деньги с одного счета на другой.
     * @param fromId - id пользователя откуда переводим.
     * @param toId - id пользователя откуда переводим.
     * @param amount - сумма перевода.
     * @return boolean.
     */
    public synchronized boolean transfer(int fromId, int toId, int amount) {
        boolean result = false;
        User userFrom = users.get(fromId);
        User userTo = users.get(toId);
        if (userFrom != null && userTo != null && userFrom.getAmount() >= amount) {
            userFrom.setAmount(userFrom.getAmount() - amount);
            userTo.setAmount(userTo.getAmount() + amount);
            result = true;
        }
        return result;
    }
}
