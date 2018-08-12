package ru.job4j.cyclicity;

import org.junit.Test;
import org.junit.Before;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class CyclicityTest {
    private Cyclicity cyclicity;
    private Node<Integer> first;
    private Node<Integer> two;
    private Node<Integer> third;
    private Node<Integer> four;

    @Before
    public void beforeTest() {
        first = new Node<>(1);
        two = new Node<>(1);
        third = new Node<>(1);
        four = new Node<>(1);

        first.next = two;
        two.next = third;
        third.next = four;
        four.next = first;

        cyclicity = new Cyclicity();

    }

    @Test
    public void whenHasCiclicityThenTrue() {
        assertThat(cyclicity.hasCycle(first), is(true));
    }
}
