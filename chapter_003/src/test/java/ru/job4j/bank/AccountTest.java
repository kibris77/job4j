package ru.job4j.bank;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class AccountTest {
    @Test
    public void whenAdd10Then15() {
        Account account = new Account(5, "1234");
        account.addMoney(10);
        assertThat(account.getValue(), is(15.0));
    }

    @Test
    public void whenSub5Then10() {
        Account account = new Account(15, "1234");
        account.subMoney(5);
        assertThat(account.getValue(), is(10.0));
    }
}
