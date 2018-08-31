package ru.job4j.concurency;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Класс реализует блокирующуся очередь.
 * @param <T>
 */
@ThreadSafe
public class SimpleBlockingQueue<T> {
    @GuardedBy("this")
    private Queue<T> queue = new LinkedList<>();
    private boolean full = false;
    private boolean epmty = true;
    private final int capacity = 10;

    /**
     * Метод помещает объект в очередь.
     * @param value - значение.
     */
    public synchronized void offer(T value) {
        while (full) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        queue.offer(value);
        System.out.println("Добавлено " + value);
        if (queue.size() > capacity) {
            full = true;
        }
        epmty = false;
        notify();
    }

    /**
     * Метод извлекает объект из очереди.
     * @return значение.
     */
    public synchronized T poll() {
        while (epmty) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        T result = queue.poll();
        System.out.println("Получено " + result);
        if (queue.isEmpty()) {
            epmty = true;
        }
        full = false;
        notify();
        return result;
    }
}
