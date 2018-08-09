package ru.job4j.iterators;

import org.junit.Before;
import org.junit.Test;
import ru.job4j.generic.*;

import java.util.NoSuchElementException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class StoreTest {
    Store<User> user;
    Store<Role> role;

    @Before
    public void setUp() {
        user = new UserStore();
        user.add(new User("1"));
        user.add(new User("2"));
        user.add(new User("3"));

        role = new RoleStore();
        role.add(new Role("1"));
        role.add(new Role("2"));
        role.add(new Role("3"));
    }

    @Test
    public void whenReplase2Then4() {
        user.replace("2", new User("4"));
        role.replace("2", new Role("4"));
        assertThat(user.findById("4").getId(), is("4"));
        assertThat(role.findById("4").getId(), is("4"));
    }

    @Test
    public void whenDelete2ThenTrueAfterFalse() {
        assertThat(user.delete("2"), is(true));
        assertThat(role.delete("2"), is(true));
        assertThat(user.delete("2"), is(false));
        assertThat(role.delete("2"), is(false));
    }

    @Test(expected = NoSuchElementException.class)
    public void whenFi2Then4() {
        assertThat(user.findById("2").getId(), is("2"));
        assertThat(role.findById("2").getId(), is("2"));
        assertThat(user.findById("4").getId(), is("4"));
        assertThat(role.findById("4").getId(), is("4"));
    }
}
