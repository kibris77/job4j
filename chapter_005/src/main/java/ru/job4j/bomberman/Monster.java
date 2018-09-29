package ru.job4j.bomberman;

/**
 * Класс описывает монстров которые перемещается по полю и нападют на бомбермена.
 */
public class Monster extends ActiveUnit {
    public Monster(Cell position, Board board, char img) {
        super(position, board, img);
    }

    @Override
    public void run() {
        super.run();
    }
}
