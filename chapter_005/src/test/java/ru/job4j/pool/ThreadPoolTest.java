package ru.job4j.pool;

import org.junit.Before;
import org.junit.Test;

public class ThreadPoolTest {
    ThreadPool pool;

    @Before
    public void beforeTest() {
        pool = new ThreadPool();
    }

    @Test
    public void whenUpdateBase() {
        for (int i = 0; i < 100; i++) {
            pool.work(() -> {
                System.out.println(Thread.currentThread().getName() + " Working");
            });
        }
        pool.shutdown();
    }
}
