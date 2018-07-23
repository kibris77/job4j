package ru.job4j.chess;

import org.junit.Test;
import ru.job4j.chess.figures.Cell;
import ru.job4j.chess.figures.black.BishoopBlack;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

public class BishoopTest {
    @Test
    public  void whenIsDiagonaThenTrue() {
        Cell pos = Cell.A3;
        BishoopBlack black = new BishoopBlack(pos);
        assertThat(black.isDiagonal(pos, Cell.C1), is(true));
    }
}
