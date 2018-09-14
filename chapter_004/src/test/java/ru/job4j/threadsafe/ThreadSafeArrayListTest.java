package ru.job4j.threadsafe;

import org.junit.Before;

import org.junit.Test;


import java.util.Iterator;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;


public class ThreadSafeArrayListTest {
    private ThreadSafeArrayList<Integer> list;

    @Before
    public void beforeTest() {
        list = new ThreadSafeArrayList<>();
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
        assertThat(list.get(10), is(11));
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
