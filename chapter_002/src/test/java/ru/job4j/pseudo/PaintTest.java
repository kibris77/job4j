package ru.job4j.pseudo;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


public class PaintTest {
    PrintStream stdout = System.out;
    ByteArrayOutputStream outStream = new ByteArrayOutputStream();

    @Before
    public void loadOutput() {
        System.out.println("execute before method");
        System.setOut(new PrintStream(this.outStream));
    }

    @After
    public void backOutput() {
        System.setOut(this.stdout);
        System.out.println("execute after method");
    }


    @Test
    public void whenDrawTriangle() {
        new Paint().draw(new Triangle());
        assertThat(new String(outStream.toByteArray()), is(new StringBuilder()
                .append("  *  ").append(System.lineSeparator())
                .append(" *** ").append(System.lineSeparator())
                .append("*****").append(System.lineSeparator())
                .append(System.lineSeparator()).toString()));
    }

    @Test
    public void whenDrawSquare() {
        new Paint().draw(new Square());
        assertThat(new String(outStream.toByteArray()), is(new StringBuilder()
                .append("*****").append(System.lineSeparator())
                .append("*   *").append(System.lineSeparator())
                .append("*   *").append(System.lineSeparator())
                .append("*   *").append(System.lineSeparator())
                .append("*****").append(System.lineSeparator())
                .append(System.lineSeparator()).toString()));
    }
}
