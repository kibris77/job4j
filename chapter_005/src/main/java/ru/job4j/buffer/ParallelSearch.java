package ru.job4j.buffer;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;
import ru.job4j.concurency.SimpleBlockingQueue;

import java.util.LinkedList;
import java.util.Queue;

public class ParallelSearch {
    private static volatile boolean isRunning = true;

    public static void main(String[] args) throws InterruptedException {
        SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<Integer>();
        final Thread consumer = new Thread(
                () -> {
                    while (isRunning) {
                        if (!queue.isEmpty()) {
                            System.out.println(queue.poll());
                        }
                    }
                }
        );
        consumer.start();
        new Thread(
                () -> {
                    for (int index = 0; index != 3; index++) {
                        queue.offer(index);
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {

                        }
                    }
                    isRunning = false;
                }

        ).start();
    }
}

