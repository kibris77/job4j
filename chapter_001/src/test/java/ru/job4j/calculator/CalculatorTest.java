package ru.job4j.calculator;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class CalculatorTest {

    @Test
    public void whenAddOnePlusOneThenTwo() {
        Calculator cacl = new Calculator();
        cacl.add(1D, 1D);
        double result = cacl.getResult();
        double expected = 2D;
        assertThat(result, is(expected));
    }

    @Test
    public void whenTwoMinusOneThenOne() {
        Calculator cacl = new Calculator();
        cacl.sub(2D, 1D);
        double result = cacl.getResult();
        double expected = 1D;
        assertThat(result, is(expected));
    }

    @Test
    public void whenTwoMultiplyThreeThenSix() {
        Calculator cacl = new Calculator();
        cacl.mul(2D, 3D);
        double result = cacl.getResult();
        double expected = 6D;
        assertThat(result, is(expected));
    }

    @Test
    public void whenTenDivideTwoThenFive() {
        Calculator cacl = new Calculator();
        cacl.div(10D, 2D);
        double result = cacl.getResult();
        double expected = 5D;
        assertThat(result, is(expected));
    }
}
