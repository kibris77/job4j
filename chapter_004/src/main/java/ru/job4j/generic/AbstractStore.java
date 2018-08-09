package ru.job4j.generic;

import java.util.NoSuchElementException;

/**
 * Абстрактный класс обертка для хранения элементов Base на основе SimpleArray.
 * @param <T> - клаассы наследуемые от Base.
 */
public class AbstractStore<T extends Base> implements Store<T> {
    private SimpleArray<T> array;

    public AbstractStore(SimpleArray<T> array) {
        this.array = array;
    }

    /**
     * Метод добавляет элемент в хранилище.
     * @param model
     */
    @Override
    public void add(T model) {
        array.add(model);
    }

    /**
     * Метод заменяет элемент в хранилище.
     * @param id - идентификатор элемента.
     * @param model - элемент.
     * @return - boolean.
     */
    @Override
    public boolean replace(String id, T model) {
        boolean result = false;
        int index = 0;
        for (T user : array) {
            if (user.getId().equals(id)) {
                array.set(index, model);
                result = true;
                break;
            }
            index++;
        }
        return result;
    }

    /**
     * Метод удаляет элемент из хранилища.
     * @param id - идентификатор элемента.
     * @return - boolean.
     */
    @Override
    public boolean delete(String id) {
        boolean result = false;
        int index = 0;
        for (T user : array) {
            if (user.getId().equals(id)) {
                array.delete(index);
                result = true;
                break;
            }
            index++;
        }
        return result;
    }

    /**
     * Метод находит элемнет по идентификатору.
     * @param id - идентификатор.
     * @return - элемент.
     */
    @Override
    public T findById(String id) {
        T result = null;
        for (T user : array) {
            if (user.getId().equals(id)) {
                result = user;
                break;
            }
        }
        if (result == null) {
            throw  new NoSuchElementException();
        }
        return result;
    }
}
