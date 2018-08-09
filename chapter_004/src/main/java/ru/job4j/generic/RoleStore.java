package ru.job4j.generic;

/**
 * Класс хранилище элементов типа Role.
 */
public class RoleStore extends AbstractStore<Role> {
    public RoleStore() {
        super(new SimpleArray<Role>());
    }
}
