package ru.job4j.nonblocking;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Класс реаоизует неблокирующийся кэш.
 */
public class NonBlockingCache {
    private final ConcurrentHashMap<Integer, Base> cache = new ConcurrentHashMap<>();

    /**
     * Метод добавляет объект в кэш.
     * @param model
     */
    public void add(Base model) {
        cache.putIfAbsent(model.getId(), model);
    }

    /**
     * Метод удаляет объект из кэша.
     * @param model
     */
    public void delete(Base model) {
        cache.computeIfPresent(model.getId(), ((integer, base) -> null));
    }

    /**
     * Метод изменеяет версию объекта в кэше.
     * @param model
     */
    public void update(Base model) {
        cache.computeIfPresent(model.getId(), (Integer integer, Base base) -> {
                if (model.getVersion() != base.getVersion()) {
                    throw new OptimisticException("Data not valid");
                }
            model.updateVersion();
            return model;
        });
    }
}
