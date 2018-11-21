package ru.job4j.crudservlet;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.postgresql.Driver;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс для хранения данных в базе данных на серврере. Рализует шаблон Синглтон.
 */
public class DbStore implements Store {
    private static final BasicDataSource SOURCE = new BasicDataSource();
    private static final DbStore INSTANCE = new DbStore();
    private static final Logger LOGGER = LogManager.getLogger(DbStore.class);

    private DbStore() {
        ProperstiesStore.loadProps("/home/alexander/projects/job4j/chapter_008/src/main/resources/app.poperties");
        SOURCE.setDriver(new Driver());
        SOURCE.setUrl(ProperstiesStore.getDbUrl());
        SOURCE.setUsername(ProperstiesStore.getUser());
        SOURCE.setPassword(ProperstiesStore.getPass());
        SOURCE.setMinIdle(5);
        SOURCE.setMaxIdle(10);
        SOURCE.setMaxOpenPreparedStatements(100);    }

    public static DbStore getInstance() {
        return INSTANCE;
    }

    /**
     * Метод добавляет пользователя в базу данных.
     * @param user - класс пользователя User.
     */
    @Override
    public void add(User user) {
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement st = connection.prepareStatement("INSERT INTO servlet(id, name, login, email, password, role, data) "
                     + "VALUES (?, ?, ?, ?, ?, ?, ?);")
        ) {
            st.setInt(1, user.getId());
            st.setString(2, user.getName());
            st.setString(3, user.getLogin());
            st.setString(4, user.getEmail());
            st.setString(5, user.getPassword());
            st.setString(6, user.getRole());
            st.setTimestamp(7, new Timestamp(System.currentTimeMillis()));
            st.executeUpdate();
        } catch (Exception e) {
            LOGGER.error(e);
            e.printStackTrace();
        }
    }

    /**
     * Метод редактирует пользователя в базе данных.
     * @param id - id пользователя.
     * @param user - класс пользователя User.
     */
    @Override
    public void update(int id, User user) {
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement st = connection.prepareStatement("UPDATE servlet "
                     + "SET name = ?, login = ?, email=?, data = ?, role = ? "
                     + "WHERE id = ?")
        ) {
            st.setString(1, user.getName());
            st.setString(2, user.getLogin());
            st.setString(3, user.getEmail());
            st.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
            st.setString(5, user.getRole());
            st.setInt(6, id);
            st.executeUpdate();
        } catch (Exception e) {
            LOGGER.error(e);
            e.printStackTrace();
        }
    }

    /**
     * Метод удаляет пользователя из базы.
     * @param id - id пользователя.
     * @return - класс пользователя User.
     */
    @Override
    public User delete(int id) {
        User user = findById(id);
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement st = connection.prepareStatement("DELETE  FROM servlet WHERE id = ?")
        ) {
            st.setInt(1, id);
            st.executeUpdate();
        } catch (Exception e) {
            LOGGER.error(e);
            e.printStackTrace();
        }
        return user;
    }

    /**
     * Метод находит пользователя по id.
     * @param id - id пользователя.
     * @return - класс пользователя User.
     */
    @Override
    public User findById(int id) {
        User result = null;
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement st = connection.prepareStatement("SELECT id, name, login, "
                     + "email, password, role, data FROM servlet WHERE id = ?")
        ) {
            st.setInt(1, id);
            ResultSet set = st.executeQuery();
            while (set.next()) {
                result = new User(set.getInt("id"),
                        set.getString("name"),
                        set.getString("login"),
                        set.getString("email"),
                        set.getString("password"),
                        set.getString("role"),
                        set.getTimestamp("data").getTime());
            }

        } catch (Exception e) {
            LOGGER.error(e);
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public User findByLogin(String login) {
        User result = null;
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement st = connection.prepareStatement("SELECT id, name, login, "
                     + "email, password, role, data FROM servlet WHERE login = ?")
        ) {
            st.setString(1, login);
            ResultSet set = st.executeQuery();
            while (set.next()) {
                result = new User(set.getInt("id"),
                        set.getString("name"),
                        set.getString("login"),
                        set.getString("email"),
                        set.getString("password"),
                        set.getString("role"),
                        set.getTimestamp("data").getTime());
            }

        } catch (Exception e) {
            LOGGER.error(e);
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Метод возвращет всех пользователей из базы данных.
     * @return = ArrayList(User).
     */
    @Override
    public List<User> findAll() {
        List<User> result = new ArrayList<>();
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement st = connection.prepareStatement("SELECT id, name, login, email, "
                     + "password, role, data FROM servlet ORDER BY id")) {
            ResultSet set = st.executeQuery();
            while (set.next()) {
               User user = new User(set.getInt("id"),
                       set.getString("name"),
                       set.getString("login"),
                       set.getString("email"),
                       set.getString("password"),
                       set.getString("role"),
                       set.getTimestamp("data").getTime());
               result.add(user);
            }
        } catch (Exception e) {
            LOGGER.error(e);
            e.printStackTrace();
        }
        return result;
    }
}
