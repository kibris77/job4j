package ru.job4j.map;

import org.junit.Test;
import org.junit.Before;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class UserTest {
    private Map<User, Object> map;

    @Before
    public void beforeTest() {
        map = new HashMap<>();
        User user1 = new User("Alex", 0, new GregorianCalendar(1985,
                Calendar.SEPTEMBER, 7));
        User user2 = new User("Alex", 0, new GregorianCalendar(1985,
                Calendar.SEPTEMBER, 7));

        Object object = new Object();
        map.put(user1, object);
        map.put(user2, object);
    }

    @Test
    public void whenAddFourElementsThenUseIterator() {
        assertThat(map.size(), is(2));
    }

}
