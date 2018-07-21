package ru.job4j.tracker;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class ValidateInputTest {
    private final ByteArrayOutputStream mem = new ByteArrayOutputStream();
    private final PrintStream out = System.out;

    @Before
    public void loadMem() {
        System.setOut(new PrintStream(this.mem));
    }

    @After
    public void loadSys() {
        System.setOut(this.out);
    }

    @Test
    public void whenInvalidInput() {
        ValidateInput input = new ValidateInput(
                new StubInput(new String[] {"Invalid", "1"})
        );
        input.ask("Enter", new int[] {0, 1});
        assertThat(
                this.mem.toString(),
                is(new StringBuilder()
                              .append("Неправильный ввод. Вы ввели не число.")
                              .append(System.lineSeparator()).toString()
                )
        );
    }

    @Test
    public void whenNoMenuNumberInput() {
        ValidateInput input = new ValidateInput(
                new StubInput(new String[] {"21", "1"})
        );
        input.ask("Enter", new int[] {0, 1});
        assertThat(
                this.mem.toString(),
                is(new StringBuilder()
                        .append("Вы ввели несуществующий номер пункта меню.")
                        .append(System.lineSeparator()).toString()
                )
        );
    }
}

