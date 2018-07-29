package ru.job4j.sort;

import org.junit.Test;

import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SortUserTest {
    @Test
    public void whenAddlistThenSortedSet() {
        SortUser sort = new SortUser();
        Set<User> result = sort.sort(Arrays.asList(new User(23, "Ivan"), new User(45, "Igor"),
                new User(20, "Masha")));
        assertThat(((TreeSet<User>) result).first().getName(), is("Masha"));
    }
}
