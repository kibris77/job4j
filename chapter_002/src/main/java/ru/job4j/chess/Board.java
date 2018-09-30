package ru.job4j.chess;

import ru.job4j.chess.exeptions.FigureNotFoundException;
import ru.job4j.chess.exeptions.ImposibleMoveException;
import ru.job4j.chess.exeptions.OccupiedWayException;
import ru.job4j.chess.figures.Cell;
import ru.job4j.chess.figures.Figure;

import java.util.function.BiFunction;

/**
 * Класс шахматной доски.
 */
public class Board {
    private final Figure[] figures = new Figure[32];
    private int index = 0;

    /**
     * Метод добавляет фигуру на доску.
     * @param figure - фигура.
     */
    public void add(Figure figure) {
        this.figures[index++] = figure;
    }

    /**
     * Метод перемещает фигуру в указанную точку на доске.
     * @param source - исходная точка.
     * @param dest - точка назначения.
     * @return boolean.
     * @throws ImposibleMoveException
     * @throws OccupiedWayException
     * @throws FigureNotFoundException
     */
    public boolean move(Cell source, Cell dest) throws ImposibleMoveException, OccupiedWayException, FigureNotFoundException {
        boolean rst = false;
        int sourceFigure = this.findBy(source, (figure1, cell2) -> figure1 != null && figure1.position().equals(cell2));
        if (sourceFigure == -1) {
            throw new FigureNotFoundException();
        }
        Cell[] steps = this.figures[sourceFigure].way(source, dest);
        for (int index = 0; index < steps.length; index++) {
             int figureWay = this.findBy(steps[index], (figure1, cell2) -> figure1 != null && figure1.position().equals(cell2));
             if (figureWay != -1) {
                 throw new OccupiedWayException();
             }
        }
        if (steps.length > 0 && steps[steps.length - 1].equals(dest)) {
                rst = true;
                this.figures[sourceFigure] = this.figures[sourceFigure].copy(dest);
        }
        return rst;
    }

    /**
     * Метод удалет все фигуры с доски.
     */
    public void clean() {
        for (int position = 0; position != this.figures.length; position++) {
            this.figures[position] = null;
        }
        this.index = 0;
    }

    /**
     * Метод находит индекс фигуры в массиве по координатам.
     * @param cell - координаты клетки.
     * @return индекс.
     */
    private int findBy(Cell cell, BiFunction<Figure, Cell, Boolean> search) {
        int rst = -1;
        for (int index = 0; index != this.figures.length; index++) {
            if (search.apply(this.figures[index], cell)) {
                rst = index;
                break;
            }
        }
        return rst;
    }
}
