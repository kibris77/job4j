package ru.job4j.map;

import org.junit.Test;
import org.junit.Before;

import java.util.Iterator;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class SimpleMapTest {
    SimpleMap<Integer, String> map;

    @Before
    public void beforeTest() {
        map = new SimpleMap<>();
        for (int i = 0; i < 20; i++) {
            map.insert(i, new Integer(i).toString());
        }
    }

    @Test
    public void whenGetFourElementReturnFour() {
        assertThat(map.get(4), is("4"));
    }

    @Test
    public void whenDeleteFourElementThenSize19() {
        map.delete(4);
        assertThat(map.getSize(), is(19));
    }

    @Test
    public void whenIteratorNextTwiceThen1() {
        Iterator<SimpleMap<Integer, String>.Entry<Integer, String>> it = map.iterator();
        assertThat(it.hasNext(), is(true));
        it.next();
        assertThat(it.next().getValue(), is("1"));
    }

}
