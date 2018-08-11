package ru.job4j.list;

import org.junit.Test;
import org.junit.Before;

import java.util.Iterator;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.theInstance;
import static org.junit.Assert.assertThat;

public class DynamicLinkedListTest {
    private DynamicLinkedList<Integer> list;

    @Before
    public void beforeTest() {
        list = new DynamicLinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);

    }

    @Test
    public void whenAddThreeElementsThenUseGetOneResultTwo() {
        assertThat(list.get(1), is(2));
        assertThat(list.get(2), is(3));
    }

    @Test
    public void whenUseIterator3ThenUseGetResult3() {
        Iterator<Integer> it = list.iterator();
        it.next();
        assertThat(it.next(), is(2));
        assertThat(it.hasNext(), is(true));
    }

    @Test
    public void whenUseIterator11ThenHasNextFalse() {
        Iterator<Integer> it = list.iterator();
        it.next();
        it.next();
        it.next();
        assertThat(it.hasNext(), is(false));
    }

}
