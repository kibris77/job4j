package ru.job4j.chess;

import org.junit.Test;
import ru.job4j.chess.exeptions.ImposibleMoveException;
import ru.job4j.chess.figures.Cell;
import ru.job4j.chess.figures.black.BishopBlack;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

public class BishopTest {
    @Test
    public void whenIsDiagonaThenTrue() {
        Cell pos = new Cell(3, 3);
        BishopBlack black = new BishopBlack(pos);
        assertThat(black.isDiagonal(pos, new Cell(1, 1)), is(true));
    }

    @Test
    public  void whenwWayThenArraySteps() throws ImposibleMoveException {
        Cell pos = new Cell(3, 3);
        BishopBlack black = new BishopBlack(pos);
        Cell[] result = black.way(pos, new Cell(5, 5));
        Cell[] expected = new Cell[]{new Cell(4, 4), new Cell(5, 5)};
        assertThat(result, is(expected));
    }

}
