package ru.job4j.chess.figures.black;

import ru.job4j.chess.exeptions.ImposibleMoveException;
import ru.job4j.chess.figures.Cell;
import ru.job4j.chess.figures.Figure;

/**
 * Класс фигуры черный слон.
 */
public class BishopBlack extends Figure {
    public BishopBlack(Cell position) {
        super(position);
    }

    /**
     * Возвращает массив ячеек от искходной точки до точки назначения.
     * @param source - исходная точка.
     * @param dest - точка назначения.
     * @return - массив ячеек.
     * @throws ImposibleMoveException
     */
    @Override
    public Cell[] way(Cell source, Cell dest) throws ImposibleMoveException {
        if (!isDiagonal(source, dest)) {
            throw new ImposibleMoveException();
        }
        Cell[] result = new Cell[Math.abs(dest.x - source.x)];
        int deltaX = dest.x > source.x ? 1 : -1;
        int deltaY = dest.y > source.y ? 1 : -1;
        for (int index = 0; index < result.length; index++) {
            result[index] = new Cell(source.x + (index + 1) * deltaX, source.y + (index + 1) * deltaY);
        }
        return result;
    }

    @Override
    public Figure copy(Cell dest) {
        return new BishopBlack(dest);
    }

    /**
     * Прверяет находится ли на диагонлаи точка куда перменстить фигуру.
     * @param source - исходная точка.
     * @param dest - точка назнаяения.
     * @return - boolean.
     */
    public boolean isDiagonal(Cell source, Cell dest) {
        boolean result = false;
        if (Math.abs(dest.x - source.x) == Math.abs(dest.y - source.y)  && source.x != dest.x && source.y != dest.y) {
            result = true;
        }
        return result;
    }
}
