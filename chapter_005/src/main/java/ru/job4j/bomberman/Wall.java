package ru.job4j.bomberman;

public class Wall extends Unit {
    public Wall(Cell position, Board board, char img) {
        super(position, board, img);
    }

    @Override
    public void moveUnit() {
        board.getBoard()[position.getY()][position.getX()].lock();
    }
}
