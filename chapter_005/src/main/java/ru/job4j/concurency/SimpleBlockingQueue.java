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
    private final int capacity = 10;

    /**
     * Метод помещает объект в очередь.
     * @param value - значение.
     */
    public synchronized void offer(T value) {
        while (queue.size() > capacity) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        queue.offer(value);
        System.out.println("Добавлено " + value);
        notify();
    }

    /**
     * Метод извлекает объект из очереди.
     * @return значение.
     */
    public synchronized T poll() {
        while (queue.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        T result = queue.poll();
        System.out.println("Получено " + result);
        notify();
        return result;
    }

    /**
     * Метод возвращает размер очереди.
     * @return кол-ыо элементов.
     */
    public synchronized int size() {
        return queue.size();
    }

    public synchronized boolean isEmpty() {
        return queue.size() == 0;
    }
}
