package ru.job4j.store;

import org.junit.Test;
import org.junit.Before;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class StoreTest {
    Store store = new Store();
    List<Store.User> previous = new ArrayList<>();
    List<Store.User> current = new ArrayList<>();

    @Before
    public void setUp() {
        previous.add(new Store.User(1, "A"));
        previous.add(new Store.User(3, "C"));
        previous.add(new Store.User(4, "D"));
        previous.add(new Store.User(6, "E"));
        previous.add(new Store.User(9, "L"));

        current.add(new Store.User(2, "A"));
        current.add(new Store.User(3, "C"));
        current.add(new Store.User(4, "DE"));
        current.add(new Store.User(5, "F"));
        current.add(new Store.User(6, "E"));
        current.add(new Store.User(7, "H"));
    }

    @Test
    public void whenAddedThreeThen3() {
        Store.Info info = store.diff(previous, current);
        assertThat(info.added, is(3));
    }

    @Test
    public void whenDeleteddOneThen3() {
        Store.Info info = store.diff(previous, current);
        assertThat(info.deleted, is(2));
    }

    @Test
    public void whenChangedOneThen1() {
        Store.Info info = store.diff(previous, current);
        assertThat(info.changed, is(1));
    }
}
