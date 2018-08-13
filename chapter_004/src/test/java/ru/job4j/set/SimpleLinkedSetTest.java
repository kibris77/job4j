package ru.job4j.set;

import org.junit.Test;
import org.junit.Before;

import java.util.Iterator;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class SimpleLinkedSetTest {
    private SimpleLinkedSet<Integer> set;

    @Before
    public void beforeTest() {
        set = new SimpleLinkedSet<>();
        set.add(1);
        set.add(2);
        set.add(2);
        set.add(3);
    }

    @Test
    public void whenAddFourElementsThenUseIterator() {
        Iterator<Integer> it = set.iterator();
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(1));
        assertThat(it.next(), is(2));
        assertThat(it.next(), is(3));
        assertThat(it.hasNext(), is(false));
    }

}
