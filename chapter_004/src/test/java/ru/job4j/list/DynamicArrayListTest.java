package ru.job4j.list;

import org.junit.Test;
import org.junit.Before;

import java.util.Iterator;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.theInstance;
import static org.junit.Assert.assertThat;

public class DynamicArrayListTest {
    private DynamicArrayList<Integer> list;

    @Before
    public void beforeTest() {
        list = new DynamicArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);
        list.add(7);
        list.add(8);
        list.add(9);
        list.add(10);
        list.add(11);
    }

    @Test
    public void whenAddThreeElementsThenUseGetOneResultTwo() {
        assertThat(list.get(1), is(2));
    }

    @Test
    public void whenUseIterator3ThenUseGetResult3() {
        Iterator<Integer> it = list.iterator();
        it.next();
        it.next();
        assertThat(it.next(), is(3));
        assertThat(it.hasNext(), is(true));
    }

    @Test
    public void whenUseIterator11ThenHasNextFalse() {
        Iterator<Integer> it = list.iterator();
        for (int i = 0; i < 11; i++) {
            it.next();
        }
        assertThat(it.hasNext(), is(false));
    }
}
