package ru.job4j.userconvert;

import java.util.HashMap;
import java.util.List;

/**
 * Класс конвертирует список в HashMap.
 */
public class UserConvert {
    /**
     * Метод конвертирует список User в HashMap.
     * @param list -список User.
     * @return - HashMap.
     */
    public HashMap<Integer, User> process(List<User> list) {
        HashMap<Integer, User> result = new HashMap<>();
        for (User user: list) {
            result.put(user.getId(), user);
        }
        return result;
    }
}
