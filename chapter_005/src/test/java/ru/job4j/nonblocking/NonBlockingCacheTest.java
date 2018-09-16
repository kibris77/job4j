package ru.job4j.nonblocking;

import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.atomic.AtomicReference;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class NonBlockingCacheTest {
    NonBlockingCache cache;
    Base base1;
    Base base2;

    @Before
    public void beforeTest() {
        cache = new NonBlockingCache();
        base1 = new Base(1, 1);
        base2 = new Base(2, 1);
        cache.add(base1);
        cache.add(base2);
    }

    @Test
    public void whenUpdateBase() throws InterruptedException {
        AtomicReference<Exception> ex = new AtomicReference<>();
        new Thread(() -> {
            try {
                cache.update(base1);
            } catch (Exception e) {
                ex.set(e);
            }
            }
        ).start();
        new Thread(() -> {
            try {
                cache.update(base1);
            } catch (Exception e) {
                ex.set(e);
            }
        }
        ).start();
    }
}
