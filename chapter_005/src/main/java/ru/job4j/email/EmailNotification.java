package ru.job4j.email;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Класс реализует рассылку Email.
 */
public class EmailNotification {
    private final ExecutorService pool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    /**
     * Метод для добавления сообщений в пул для отправки.
     * @param user - пользователь.
     */
    public void emailTo(User user) {
        String subject = String.format("Notification {%s} to email {%s}.", user.getUsername(), user.getEmail());
        String body = String.format("Add a new event to {username}");
        pool.submit(() -> send(subject, body, user.getEmail()));
    }

    /**
     * Метод отправляет сообщение на Email.
     * @param suject - тема.
     * @param body - тело письма.
     * @param email - адрес.
     */
    private void send(String suject, String body, String email) {
        System.out.println("Sending Email to " + email);
    }

    /**
     * Метод останавливает отправку сообщений.
     */
    public void close() {
        pool.shutdown();
        while (!pool.isTerminated()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
