package ru.job4j.tracker;

import java.sql.*;
import java.util.*;
import java.util.function.Predicate;

/**
 * Класс трекер заявок.
 */
public class Tracker implements AutoCloseable {
    private static final Random RN = new Random();
    private Connection connection;

    public Tracker(Config config) {
        connection = config.connectToDB();
    }

    /**
     * Метод добавляет заявку в хранилище.
     * @param item - заявка
     * @return - добавленная заявка.
     */
    public Item addItem(Item item) {
        try (PreparedStatement statement = connection.prepareStatement("INSERT INTO tracker(name, item, date) "
                + "VALUES (?, ?, ?);");) {
            statement.setString(1, item.getName());
            statement.setString(2, item.getDescription());
            statement.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return item;
    }

    /**
     * Метод заменяет завку на новую по ID.
     * @param id - идентификатор заявки.
     * @param item - заявка.
     */
    public boolean replace(String id, Item item) {
        boolean result = false;
        try (PreparedStatement statement = connection.prepareStatement("UPDATE tracker  "
                + "SET name = ?, item = ?, date = ? WHERE id = ?");) {
            statement.setString(1, item.getName());
            statement.setString(2, item.getDescription());
            statement.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
            statement.setInt(4, Integer.parseInt(id));
            int changes = statement.executeUpdate();
            if (changes > 0) {
                result = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Метод возвращает заявку по ID.
     * @param id - ID заявки.
     * @return - заявка.
     */
    public Item findById(String id) {
        Item result = null;
        try (PreparedStatement statement = connection.prepareStatement("SELECT id, name, item, date "
                + "FROM tracker WHERE id = ?");) {
            statement.setInt(1, Integer.parseInt(id));
            ResultSet set = statement.executeQuery();
            while (set.next()) {
                result = new Item(set.getString("name"),
                        set.getString("item"), set.getTimestamp("date").getTime());
                result.setId(set.getString("id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    /**
     * Метод возвращает заявку по имени.
     * @param name - имя заявки.
     * @return - заявка.
     */
    public List<Item> findByName(String name) {
        List<Item> result = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement("SELECT id, name, item, date "
                + "FROM tracker WHERE name = ?");) {
            statement.setString(1, name);
            ResultSet set = statement.executeQuery();
            while (set.next()) {
                Item item = new Item(set.getString("name"),
                        set.getString("item"), set.getTimestamp("date").getTime());
                item.setId(set.getString("id"));
                result.add(item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Метод возвращает все заявки.
     * @return - массив заявок.
     */
    public List<Item> findAll() {
        List<Item> result = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement("SELECT id, name, item, date "
                + "FROM tracker");) {
            ResultSet set = statement.executeQuery();
            while (set.next()) {
                Item item = new Item(set.getString("name"),
                        set.getString("item"), set.getTimestamp("date").getTime());
                item.setId(set.getString("id"));
                result.add(item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Метод удаляет заявку по ID.
     * @param id - ID заявки.
     */
    public boolean delete(String id) {
        boolean result = false;
        try (PreparedStatement statement = connection.prepareStatement("DELETE FROM tracker WHERE id = ?");) {
            statement.setInt(1, Integer.parseInt(id));
            int changes = statement.executeUpdate();
            if (changes > 0) {
                result = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Метод генерирует уникальный ключ.
     * @return - уникальный ID завки.
     */
    private String generateId() {
        return String.valueOf(System.currentTimeMillis() + RN.nextInt());
    }

    @Override
    public void close() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
