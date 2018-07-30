package ru.job4j.sort;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
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

    @Test
    public void whenSortUserThenNameLength() {
        SortUser sort = new SortUser();
        List<User> result = sort.sortNameLength(Arrays.asList(new User(23, "Ivanovich"), new User(45, "Igor"),
                new User(20, "Masha")));
        assertThat(result.get(0).getName(), is("Igor"));
    }

    @Test
    public void whenSortUserThenFirstNameAge() {
        SortUser sort = new SortUser();
        List<User> result = sort.sortByAllFields(Arrays.asList(new User(23, "Ivan"), new User(45, "Igor"),
                new User(20, "Ivan"), new User(33, "Igor")));
        assertThat(result.get(0).getName(), is("Igor"));
        assertThat(result.get(0).getAge(), is(33));
        assertThat(result.get(3).getName(), is("Ivan"));
        assertThat(result.get(3).getAge(), is(23));
    }

}
