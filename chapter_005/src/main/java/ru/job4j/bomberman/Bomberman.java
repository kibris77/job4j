package ru.job4j.bomberman;

import java.util.Random;

/**
 * Класс реализует бомбермена.
 */
public class Bomberman extends Thread {
    private Cell position;
    private final Board board;
    private int dx = 0;
    private int dy = 0;
    boolean isRunning = true;
    private Random random = new Random();


    public Bomberman(Cell position, Board board) {
        this.position = position;
        this.board = board;
    }

    @Override
    public void run() {
        moveBomberman();
    }

    /**
     * Метод перемещает бомбермена по полю.
     */
    private void moveBomberman() {
        while (!Thread.currentThread().isInterrupted() && isRunning) {
            randomDirection();
            Cell newPosition = new Cell(position.getX() + dx, position.getY() + dy);
            if (board.move(position, newPosition)) {
                position = newPosition;
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {

                }
            }
        }
    }

    /**
     * Метод задает случайное направление для перемешения бомбермена.
     */
    private void randomDirection() {
        dx = -1 + random.nextInt(3);
        dy = -1 + random.nextInt(3);
        if ((position.getX() + dx) > board.getSize() || (position.getX() + dx) < 0) {
            dx = -dx;
        }
        if ((position.getY() + dy) > board.getSize() || (position.getY() + dy) < 0) {
            dy = -dy;
        }
    }

    public int getX() {
        return position.getX();
    }

    public int getY() {
        return position.getY();
    }
}
