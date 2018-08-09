package ru.job4j.generic;

/**
 * Класс хранилище элементов типа User.
 */
public class UserStore extends AbstractStore<User> {
    public UserStore() {
        super(new SimpleArray<User>());
    }
}
