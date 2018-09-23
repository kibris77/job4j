package ru.job4j.diapason;

import org.junit.Test;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class CalculateTest {
    Calculate calculate;

    @Test
    public void whenLinearThen() {
        calculate = new Calculate();
        List<Double> result = calculate.diapason(1, 3, (num) -> num);
        assertThat(result.toArray(), is(new double[]{1, 2, 3}));
    }

    @Test
    public void whenQuadraticThen() {
        calculate = new Calculate();
        List<Double> result = calculate.diapason(1, 3, (num) -> num * num);
        assertThat(result.toArray(), is(new double[]{1, 4, 9}));
    }

    @Test
    public void whenLogarithmThen() {
        calculate = new Calculate();
        List<Double> result = calculate.diapason(1, 3, Math::exp);
        assertThat(result.toArray(), is(new double[]{2.718281828459045, 7.38905609893065, 20.085536923187668}));
    }

}
