package ru.job4j.bomberman;

/**
 * Класс реализует бомбермена.
 */
public class Bomberman extends ActiveUnit {
    public Bomberman(Cell position, Board board, char img) {
        super(position, board, img);
    }

    @Override
    public void run() {
        super.run();
    }

    /**
     * Метол перемещает бомбермена по полю.
     * @param dx - направление х.
     * @param dy - направление y.
     */
    public void moveBomberman(int dx, int dy) {
        board.getBoard()[position.getY()][position.getX()].lock();
        while (!Thread.currentThread().isInterrupted() && isRunning) {
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
