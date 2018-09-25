package ru.job4j.pool;

import ru.job4j.concurency.SimpleBlockingQueue;

import java.util.LinkedList;
import java.util.List;

/**
 * Класс реализует простой Пул потоков.
 */
public class ThreadPool {
    private final List<Thread> threads = new LinkedList<>();
    private final SimpleBlockingQueue<Runnable> tasks = new SimpleBlockingQueue<>();

    /**
     * Конструктор добавляет потоки в Пул.
     */
    public ThreadPool() {
        int size = Runtime.getRuntime().availableProcessors();
        for (int index = 0; index < size; index++) {
            Thread thread = new Thread(
                () -> {
                    while (!Thread.currentThread().isInterrupted()) {
                        tasks.poll().run();
                    }
            });
            threads.add(thread);
            thread.start();
        }
    }

    /**
     * Метод добавляет задачи в очередь.
     * @param job
     */
    public void work(Runnable job) {
            tasks.offer(job);
    }

    /**
     * Метод останавливает пул потоков.
     */
    public void shutdown() {
        for (Thread thread : threads) {
            thread.interrupt();
        }
    }
}

