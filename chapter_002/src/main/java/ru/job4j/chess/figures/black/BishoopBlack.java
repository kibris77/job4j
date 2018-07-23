package ru.job4j.chess.figures.black;

import ru.job4j.chess.exeptions.ImposibleMoveException;
import ru.job4j.chess.figures.Cell;
import ru.job4j.chess.figures.Figure;

public class BishoopBlack extends Figure {
    public BishoopBlack(Cell position) {
        super(position);
    }

    @Override
    public Cell[] way(Cell source, Cell dest) throws ImposibleMoveException {


        return new Cell[0];
    }

    @Override
    public Figure copy(Cell dest) {
        return new BishoopBlack(dest);
    }

    public double isDiagonal(Cell source, Cell dest) {
        //boolean result = false;
        double angle = Math.atan((dest.y - source.y)/(dest.x - source.x)) * 180 / Math.PI;
        if ((angle > 44 && angle < 46)) {

        }
        return angle;
    }
}
