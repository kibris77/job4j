package ru.job4j.concurency;

import org.junit.Test;

public class SimpleBlockingQueueTest {
    /**
     * Класс Producer.
     */
    private class Producer implements Runnable {
        SimpleBlockingQueue<Integer> queue;

        public Producer(SimpleBlockingQueue queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            int i = 0;
            while (i < 20) {
               try {
                    Thread.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                queue.offer(i++);
            }
        }
    }

    /**
     * Класс Consumer.
     */
    private class Consumer implements Runnable {
        SimpleBlockingQueue<Integer> queue;

        public Consumer(SimpleBlockingQueue queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            while (true) {
                queue.poll();
            }
        }
    }

    @Test
    public void whenExecute2ThreadThen() throws InterruptedException {
        SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>();
        Thread producer = new Thread(new Producer(queue));
        Thread consumer = new Thread(new Consumer(queue));
        producer.start();
        consumer.start();
        producer.join();
        consumer.join();
    }
}
