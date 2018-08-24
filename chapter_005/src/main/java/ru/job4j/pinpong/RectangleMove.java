package ru.job4j.pinpong;

import javafx.scene.shape.Rectangle;

import java.util.Random;

public class RectangleMove implements Runnable {
    private final Rectangle rect;
    int dx = 4;
    int dy = 0;
    static Random random = new Random();

    public RectangleMove(Rectangle rect) {
        this.rect = rect;
    }

    @Override
    public void run() {
        while (true) {
            if (rect.getX() < 0 || rect.getX() > 290) {
                dx = -dx;
                dy = -1 + random.nextInt(3);
            }
            if (rect.getY() < 0 || rect.getY() > 290) {
                dy = -dy;
            }
            this.rect.setX(this.rect.getX() + dx);
            this.rect.setY(this.rect.getY() + dy);
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

