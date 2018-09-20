package ru.job4j.email;

import org.junit.Test;


public class EmailNotificationTest {
    @Test
    public void whenSendEmail() {
        EmailNotification emailNotification = new EmailNotification();
        emailNotification.emailTo(new User("Vasya", "vas@mail.ru"));
        emailNotification.emailTo(new User("Pet", "pet@mail.ru"));
        emailNotification.emailTo(new User("Alex", "al@mail.ru"));
        emailNotification.close();
    }
}
