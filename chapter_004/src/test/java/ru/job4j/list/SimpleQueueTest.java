package ru.job4j.list;

import org.junit.Test;
import org.junit.Before;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class SimpleQueueTest {
    private SimpleQueue<Integer> queue;

    @Before
    public void beforeTest() {
        queue = new SimpleQueue<>();
        queue.push(1);
        queue.push(2);
        queue.push(3);
    }

    @Test
    public void whenPushThreeElementsThenUseGetOneResultOne() {
        assertThat(queue.get(0), is(1));
        assertThat(queue.get(1), is(2));
    }

    @Test
    public void whenPushThreeElementsThenUsePollTwoResultOne() {
        assertThat(queue.poll(), is(1));
        assertThat(queue.poll(), is(2));
    }

}
