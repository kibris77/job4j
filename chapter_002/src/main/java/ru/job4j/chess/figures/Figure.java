package ru.job4j.chess.figures;

import ru.job4j.chess.exeptions.ImposibleMoveException;

public abstract class Figure {
    private final Cell position;

    public Figure(Cell position) {
        this.position = position;
    }

    public abstract Cell[] way(Cell source, Cell dest) throws ImposibleMoveException;

    public abstract Figure copy(Cell dest);

    public Cell position() {
        return position;
    }
}
