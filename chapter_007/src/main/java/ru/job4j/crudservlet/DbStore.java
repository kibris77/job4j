package ru.job4j.crudservlet;

import org.apache.commons.dbcp2.BasicDataSource;
import org.postgresql.Driver;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DbStore implements Store{
    public static final BasicDataSource SOURCE = new BasicDataSource();
    private static DbStore INSTANCE = new DbStore();

    private DbStore() {
        SOURCE.setDriver(new Driver());
        SOURCE.setUrl("jdbc:postgresql://127.0.0.1:5432/sqlparcer");
        SOURCE.setUsername("postgres");
        SOURCE.setPassword("123456");
        SOURCE.setMinIdle(5);
        SOURCE.setMaxIdle(10);
        SOURCE.setMaxOpenPreparedStatements(100);    }

    public static DbStore getInstance() {
        return INSTANCE;
    }

    @Override
    public void add(User user) {
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement st = connection.prepareStatement("INSERT INTO servlet(id, name, login, email, data) " +
                     "VALUES (?, ?, ?, ?, ?);")
        ) {
            st.setInt(1, user.getId());
            st.setString(2, user.getName());
            st.setString(3, user.getLogin());
            st.setString(4, user.getEmail());
            st.setTimestamp(5, new Timestamp(System.currentTimeMillis()));
            st.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(int id, User user) {
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement st = connection.prepareStatement("UPDATE servlet " +
                     "SET name = ?, login = ?, email=?, data = ? " +
                     "WHERE id = ?")
        ) {
            st.setString(1, user.getName());
            st.setString(2, user.getLogin());
            st.setString(3, user.getEmail());
            st.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
            st.setInt(5, id);
            st.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public User delete(int id) {
        User user = findById(id);
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement st = connection.prepareStatement("DELETE  FROM servlet WHERE id = ?");
        ) {
            st.setInt(1, id);
            st.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public User findById(int id) {
        User result = null;
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement st = connection.prepareStatement("SELECT id, name, login, " +
                     "email, data FROM servlet WHERE id = ?");
        ) {
            st.setInt(1, id);
            ResultSet set = st.executeQuery();
            while (set.next()) {
                result = new User(set.getInt("id"),
                        set.getString("name"),
                        set.getString("login"),
                        set.getString("email"),
                        set.getTimestamp("data").getTime());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public List<User> findAll() {
        List<User> result = new ArrayList<>();
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement st = connection.prepareStatement("SELECT id, name, login, email, data FROM servlet");
        ) {
            ResultSet set = st.executeQuery();
            while (set.next()) {
               User user = new User(set.getInt("id"),
                       set.getString("name"),
                       set.getString("login"),
                       set.getString("email"),
                       set.getTimestamp("data").getTime());
               result.add(user);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
