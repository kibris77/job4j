package ru.job4j.pseudo;

import org.junit.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class PaintTest {
    @Test
    public void whenDrawTriangle() {
        PrintStream stdout = System.out;
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outStream));
        new Paint().draw(new Triangle());
        assertThat(new String(outStream.toByteArray()), is(new StringBuilder()
                .append("  *  \n")
                .append(" *** \n")
                .append("*****\n").toString()));
    }

    @Test
    public void whenDrawSquare() {
        PrintStream stdout = System.out;
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outStream));
        new Paint().draw(new Square());
        assertThat(new String(outStream.toByteArray()), is(new StringBuilder()
                .append("*****\n")
                .append("*   *\n")
                .append("*   *\n")
                .append("*   *\n")
                .append("*****\n").toString()));
    }
}
