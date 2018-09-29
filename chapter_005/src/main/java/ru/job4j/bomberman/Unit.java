package ru.job4j.bomberman;

import java.util.Random;

/**
 * Абстрактный класс описываеб элемент на поле.
 */
public abstract class Unit {
    Cell position;
    final Board board;
    final char img;
    int dx = 0;
    int dy = 0;
    private Random random = new Random();
    boolean isRunning = true;


    public Unit(Cell position, Board board, char img) {
        this.position = position;
        this.board = board;
        this.img = img;
    }

    public int getX() {
        return position.getX();
    }
    public int getY() {
        return position.getY();
    }

    public Board getBoard() {
        return board;
    }

    public char getImg() {
        return img;
    }

    /**
     * Аьсьрактый метод премещает элемент по полю.
     */
    abstract void moveUnit();

    /**
     * Метод задает случайное набправление для перемещиния.
     */
    void randomDirection() {
        dx = -1 + random.nextInt(3);
        dy = -1 + random.nextInt(3);
        if ((position.getX() + dx) > board.getSize() - 1 || (position.getX() + dx) < 1) {
            dx = -dx;
        }
        if ((position.getY() + dy) > board.getSize() - 1 || (position.getY() + dy) < 1) {
            dy = -dy;
        }
    }

    /**
     * Метод останавливает элемент.
     */
    public void stop() {
        isRunning = false;
    }
}
