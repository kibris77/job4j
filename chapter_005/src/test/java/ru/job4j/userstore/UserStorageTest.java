package ru.job4j.userstore;

import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class UserStorageTest {
    private class ThreadCount extends Thread {
        private final UserStorage userStorage;

        private ThreadCount(final UserStorage userStorage) {
            this.userStorage = userStorage;
        }

        @Override
        public void run() {
            userStorage.transfer(1, 2, 100);
            userStorage.transfer(2, 1, 300);
        }
    }

    @Test
    public void whenTransferAmountThreadThen1400and600() throws InterruptedException {
        User user1 = new User(1, 1000);
        User user2 = new User(2, 1000);
        UserStorage userStorage = new UserStorage();
        userStorage.add(user1);
        userStorage.add(user2);
        ThreadCount threadA = new ThreadCount(userStorage);
        ThreadCount threadB = new ThreadCount(userStorage);
        threadA.start();
        threadB.start();
        threadA.join();
        threadB.join();
        assertThat(user1.getAmount(), is(1400));
        assertThat(user2.getAmount(), is(600));
    }

}
