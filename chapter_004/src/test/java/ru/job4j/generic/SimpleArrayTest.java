package ru.job4j.generic;

import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class SimpleArrayTest {
    private SimpleArray<Integer> arr;
    private Iterator<Integer> it;

    @Before
    public void setUp() {
        arr = new SimpleArray<>();
        arr.add(23);
        arr.add(34);
        arr.add(12);
        arr.add(76);
        it = arr.iterator();
    }

    @Test
    public void get2ShouldReturn12() {
        assertThat(arr.get(2), is(12));
    }

    @Test
    public void set2ShouldReturn24() {
        arr.set(2, 24);
        assertThat(arr.get(2), is(24));
    }

    @Test
    public void delete2ShouldReturn76() {
        arr.delete(2);
        assertThat(arr.get(2), is(76));
    }

    @Test
    public void iteratorThenReturn() {
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(23));
        assertThat(it.next(), is(34));
        assertThat(it.next(), is(12));
        assertThat(it.next(), is(76));
        assertThat(it.hasNext(), is(false));
    }

}
