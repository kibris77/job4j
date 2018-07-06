package ru.job4j.max;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author Gorunov Alexandr (mailto:gorunov.sasha85@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class MaxTest {

    @Test
    public void whenFirstLessSecond() {
        Max maxim = new Max();
        int result = maxim.max(1, 2);
        assertThat(result, is(2));
    }

    @Test
    public void whenFirstMoreSecond() {
        Max maxim = new Max();
        int result = maxim.max(2, 1);
        assertThat(result, is(2));
    }

    @Test
    public void whenFirstEqualsSecond() {
        Max maxim = new Max();
        int result = maxim.max(2, 2);
        assertThat(result, is(2));
    }

    @Test
    public void whenFirstMoreSecondAndThird() {
        Max maxim = new Max();
        int result = maxim.max(3, 2, 1);
        assertThat(result, is(3));
    }

    @Test
    public void whenThirdMoreFirstAndSecond() {
        Max maxim = new Max();
        int result = maxim.max(3, 2, 4);
        assertThat(result, is(4));
    }
}
