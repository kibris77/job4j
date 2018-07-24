package ru.job4j.chess.figures;

import ru.job4j.chess.exeptions.ImposibleMoveException;

/**
 * Абстрактный базовый класс фигуры.
 */
public abstract class Figure {
    private final Cell position;

    public Figure(Cell position) {
        this.position = position;
    }

    /**
     * Метод возвращает массив клеток на пути фигуры.
     * @param source - исходная клетка.
     * @param dest - клетка назначения.
     * @return массив клеток.
     * @throws ImposibleMoveException
     */
    public abstract Cell[] way(Cell source, Cell dest) throws ImposibleMoveException;

    /**
     * Метод меняет координаты ячейки.
     * @param dest - клетка назначения.
     * @return - фигуру.
     */
    public abstract Figure copy(Cell dest);

    /**
     * Метод возращает координаты клетки.
     * @return координаты клетки.
     */
    public Cell position() {
        return position;
    }
}
