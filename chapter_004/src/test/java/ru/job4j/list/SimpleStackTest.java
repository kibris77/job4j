package ru.job4j.list;

import org.junit.Test;
import org.junit.Before;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class SimpleStackTest {
    private SimpleStack<Integer> stack;

    @Before
    public void beforeTest() {
        stack = new SimpleStack<>();
        stack.add(1);
        stack.add(2);
        stack.add(3);
        stack.push(4);
        stack.push(5);
    }

    @Test
    public void whenAddFiveElementsThenUseGetOneResultFour() {
        assertThat(stack.get(0), is(5));
        assertThat(stack.get(1), is(4));
    }

    @Test
    public void whenPollFiveElementsThenUsePollResultFive() {
        assertThat(stack.poll(), is(5));
        assertThat(stack.poll(), is(4));
        assertThat(stack.poll(), is(1));
    }

}
