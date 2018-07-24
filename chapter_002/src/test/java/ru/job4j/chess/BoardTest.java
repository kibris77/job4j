package ru.job4j.chess;

import org.junit.Test;
import ru.job4j.chess.exeptions.FigureNotFoundException;
import ru.job4j.chess.exeptions.ImposibleMoveException;
import ru.job4j.chess.exeptions.OccupiedWayException;
import ru.job4j.chess.figures.Cell;
import ru.job4j.chess.figures.black.BishopBlack;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

public class BoardTest {
    @Test
    public void whenMoveFigureThenTrue() throws OccupiedWayException, FigureNotFoundException {
        Board board = new Board();
        board.add(new BishopBlack(new Cell(3, 3)));
        boolean result = false;
        try {
            result = board.move(new Cell(3, 3), new Cell(6, 6));
        } catch (ImposibleMoveException e) {
            e.getMessage();
        }
        assertThat(result, is(true));
    }

    @Test
    public void whenMoveFigureThenFalse() throws OccupiedWayException, FigureNotFoundException {
        Board board = new Board();
        board.add(new BishopBlack(new Cell(3, 3)));
        boolean result = false;
        try {
            result = board.move(new Cell(3, 3), new Cell(5, 6));
        } catch (ImposibleMoveException e) {
            e.getMessage();
        }
        assertThat(result, is(false));
    }

    @Test
    public void whenFigureNotFoundThenFalse() throws ImposibleMoveException, OccupiedWayException {
        Board board = new Board();
        board.add(new BishopBlack(new Cell(4, 4)));
        boolean result = false;
        try {
            result = board.move(new Cell(3, 3), new Cell(6, 6));
        } catch (FigureNotFoundException e) {
            e.getMessage();
        }
        assertThat(result, is(false));
    }

    @Test
    public void whenOcupiedFigureThenFalse() throws ImposibleMoveException, FigureNotFoundException {
        Board board = new Board();
        board.add(new BishopBlack(new Cell(3, 3)));
        board.add(new BishopBlack(new Cell(4, 4)));
        boolean result = false;
        try {
            result = board.move(new Cell(3, 3), new Cell(6, 6));
        } catch (OccupiedWayException e) {
            e.getMessage();
        }
        assertThat(result, is(false));
    }
}
