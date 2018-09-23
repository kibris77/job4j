package ru.job4j.bomberman;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Класс реализует игровое поле и его мезанику.
 */
public class Board {
    private final ReentrantLock[][] board;
    private final Bomberman bomberman;
    private final int size;

    public Board(int size) {
        this.size = size;
        this.board = new ReentrantLock[size][size];
        this.bomberman = new Bomberman(new Cell(size / 2, size / 2), this);
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                board[i][j] = new ReentrantLock();
            }
        }
        bomberman.start();
    }

    /**
     * Метод перемещает объекты по полю.
     * @param source - с какой клетки преместить.
     * @param dest - в какую клетку преместиь.
     * @return - boolean.
     */
    public boolean move(Cell source, Cell dest) {
        boolean result = false;
        try {
            if (board[dest.getY()][dest.getX()].tryLock(500, TimeUnit.MILLISECONDS)) {
                System.out.println("Заблокировано " + dest.getX() + " " + dest.getY());
                if (board[source.getY()][source.getX()].isLocked()) {
                    board[source.getY()][source.getX()].unlock();
                    System.out.println("Разблокировано " + source.getX() + " " + source.getY());
                }
                result = true;
            }
        } catch (InterruptedException e) {

        }
        return result;
    }

    public int getSize() {
        return size;
    }

    public void stop() {
        bomberman.isRunning = false;
    }

    public static void main(String[] args) {
        Board board = new Board(16);
       /* while (true) {
           System.out.println("--------------------------------------");
            for (int i = 0; i < board.getSize(); i++) {
                for (int j = 0; j < board.getSize(); j++) {
                    if (i == board.bomberman.getY() && j == board.bomberman.getX()) {
                        System.out.print("X");
                    } else {
                        System.out.print("o");
                    }
                }
                System.out.println();
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {

            }
        } */

    }
}
