package ru.job4j.loop;

import java.util.function.BiPredicate;

/**
 * @author Gorunov Alexandr(mailto:gorunov.sasha85@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class Paint {
    /**
     * Рисует на консоли пирамиду из символов.
     * @param height - высота пирамиды.
     * @return рисуеи пирамиду.
     */
    public String pyramid(int height) {
        return this.loopBy(
                height,
                2 * height - 1,
                (row, column) -> row >= height - column - 1 && row + height - 1 >= column
        );
    }

    /**
     * Вспомогательный метод для отрисовки пирамиды.
     * @param height - высота.
     * @param weight - ширина.
     * @param predict - условие.
     * @return рисует пирамиду.
     */
    private String loopBy(int height, int weight, BiPredicate<Integer, Integer> predict) {
        StringBuilder screen = new StringBuilder();
        for (int row = 0; row != height; row++) {
            for (int column = 0; column != weight; column++) {
                if (predict.test(row, column)) {
                    screen.append("^");
                } else {
                    screen.append(" ");
                }
            }
            screen.append(System.lineSeparator());
        }
        return screen.toString();
    }


}
