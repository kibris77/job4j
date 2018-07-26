package ru.job4j.coffe;

import org.junit.Test;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;


public class CoffeMachineTest {
    @Test
    public void when50and35Then10and5() throws NotEnoughMoneyExeption {
        CoffeMachine coffeMachine = new CoffeMachine();
        int[] result = coffeMachine.change(50, 35);
        assertThat(result, is(new int[]{10, 5}));
    }

    @Test
    public void when50and50Then0() throws NotEnoughMoneyExeption {
        CoffeMachine coffeMachine = new CoffeMachine();
        int[] result = coffeMachine.change(50, 50);
        assertThat(result, is(new int[]{0}));
    }
}
