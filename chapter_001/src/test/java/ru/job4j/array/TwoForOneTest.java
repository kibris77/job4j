package ru.job4j.array;

import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class TwoForOneTest {
    @Test
    public void whenTwoSortedArrThenTurnedOneArray() {
        TwoForOne twoForOne = new TwoForOne();
        int[] first = new int[] {1, 3, 5, 7, 9};
        int[] second = new int[] {2, 4, 6};
        int[] result = twoForOne.merge(first, second);
        int[] expect = new int[] {1, 2, 3, 4, 5, 6, 7, 9};
        assertThat(result, is(expect));
    }
}
