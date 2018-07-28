package ru.job4j.userconvert;

import com.sun.javafx.collections.UnmodifiableListSet;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class UserConvertTest {
    @Test
    public void whenAddListThenHasMap() {
        UserConvert convert = new UserConvert();
        List<User> list = new ArrayList<>();
        list.add(new User(1, "Ivan", "Moscow"));
        list.add(new User(2, "Petr", "Tver"));
        list.add(new User(3, "Masha", "London"));
        HashMap<Integer, User> map = convert.process(list);
        String name = map.get(2).getName();
        assertThat(name, is("Petr"));
    }
}
