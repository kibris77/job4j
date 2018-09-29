package ru.job4j.bomberman;

/**
 * Класс опичывает активный эдемент на поле который постоянно премещается.
 */
public class ActiveUnit extends Unit implements Runnable {
    public ActiveUnit(Cell position, Board board, char img) {
        super(position, board, img);
    }

    @Override
    public void run() {
        moveUnit();
    }

    @Override
    public void moveUnit() {
        board.getBoard()[position.getY()][position.getX()].lock();
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
}
