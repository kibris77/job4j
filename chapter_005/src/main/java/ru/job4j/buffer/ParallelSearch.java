package ru.job4j.buffer;

import ru.job4j.concurency.SimpleBlockingQueue;

public class ParallelSearch {

    public static void main(String[] args) throws InterruptedException {
        SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<Integer>();
        final Thread consumer = new Thread(
                () -> {
                    while (!Thread.currentThread().isInterrupted() || !queue.isEmpty()) {
                        if (!queue.isEmpty()) {
                            System.out.println(queue.poll());
                        }
                    }
                }
        );
        consumer.start();
        final Thread producer = new Thread(
                () -> {
                    for (int index = 0; index != 3; index++) {
                        queue.offer(index);
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {

                        }
                    }
                }

        );
        producer.start();
        producer.join();
        consumer.interrupt();
        consumer.join();
    }
}

