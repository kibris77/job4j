package ru.job4j.bank;

import org.junit.Test;
import ru.job4j.bank.exeptions.NoAccountExeption;
import ru.job4j.bank.exeptions.NotEnougthMoneyExeption;
import ru.job4j.bank.exeptions.UserExistExeption;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


public class BankTest {
    @Test
    public void whenAddUser() throws UserExistExeption {
        Bank bank = new Bank();
        User user = new User("Ivan", "1234");
        bank.addUser(user);
        assertThat(bank.getMap().containsKey(user), is(true));
    }

    @Test
    public void whenDeleteUser() throws UserExistExeption {
        Bank bank = new Bank();
        User user = new User("Ivan", "1234");
        bank.addUser(user);
        assertThat(bank.getMap().containsKey(user), is(true));
        bank.deleteUser(user);
        assertThat(bank.getMap().containsKey(user), is(false));
    }

    @Test
    public void whenAddAccount() throws UserExistExeption {
        Bank bank = new Bank();
        User user = new User("Ivan", "1234");
        bank.addUser(user);
        bank.addAccountToUser("1234", new Account(0, "1111"));
        assertThat(bank.getMap().get(user).get(0).getRequisites(), is("1111"));
    }

    @Test
    public void whenGetAccounts() throws UserExistExeption, NoAccountExeption {
        Bank bank = new Bank();
        bank.addUser(new User("Ivan", "1234"));
        bank.addAccountToUser("1234", new Account(0, "1111"));
        bank.addAccountToUser("1234", new Account(10, "1112"));
        assertThat(bank.getUserAccounts("1234").size(), is(2));
        assertThat(bank.getUserAccounts("1234").get(1).getRequisites(), is("1112"));
        assertThat(bank.getUserAccounts("1234").get(1).getValue(), is(10.0));
    }

    @Test
    public void whenGetCurrentAccounts() throws UserExistExeption, NoAccountExeption {
        Bank bank = new Bank();
        bank.addUser(new User("Ivan", "1234"));
        bank.addAccountToUser("1234", new Account(0, "1111"));
        bank.addAccountToUser("1234", new Account(10, "1112"));
        assertThat(bank.getCurrentAccount("1234", "1111").getValue(), is(0.0));
        assertThat(bank.getCurrentAccount("1234", "1112").getValue(), is(10.0));
    }

    @Test
    public void whenDeleteAccount() throws UserExistExeption, NoAccountExeption {
        Bank bank = new Bank();
        bank.addUser(new User("Ivan", "1234"));
        bank.addAccountToUser("1234", new Account(0, "1111"));
        bank.addAccountToUser("1234", new Account(10, "1112"));
        assertThat(bank.getUserAccounts("1234").size(), is(2));
        bank.deleteAccountFromUser("1234", bank.getCurrentAccount("1234", "1111"));
        assertThat(bank.getUserAccounts("1234").size(), is(1));
    }

    @Test
    public void whenTransfer5to0Then5to5() throws UserExistExeption, NoAccountExeption, NotEnougthMoneyExeption {
        Bank bank = new Bank();
        bank.addUser(new User("Ivan", "1234"));
        bank.addUser(new User("Petr", "1235"));
        bank.addAccountToUser("1234", new Account(10, "1111"));
        bank.addAccountToUser("1235", new Account(0, "1112"));
        boolean result = bank.transferMoney("1234", "1111", "1235", "1112", 5.0);
        assertThat(bank.getUserAccounts("1234").get(0).getValue(), is(5.0));
        assertThat(bank.getUserAccounts("1235").get(0).getValue(), is(5.0));
        assertThat(result, is(true));
    }

}
