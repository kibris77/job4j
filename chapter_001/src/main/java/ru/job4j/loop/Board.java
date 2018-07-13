package ru.job4j.loop;

/**
 * @author Gorunov Alexandr(mailto:gorunov.sasha85@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class Board {
    /**
     * Метод рисует шахматную доску в псевдографике
     * @param width - ширина доски
     * @param height - высота доски
     * @return Строка с доской
     */
    public String paint(int width, int height) {
        StringBuilder screen = new StringBuilder();
        String ln = System.lineSeparator();
        for (int out = 0; out < height; out++) {
            for (int in = 0; in < width; in++) {
                // условие проверки, что писать пробел или X
                // Выше в задании мы определили закономерность, когда нужно проставлять X
                if ((in + out) % 2 == 0) {
                    screen.append("X");
                } else {
                    screen.append(" ");
                }
            }
            // добавляем перевод на новую строку.
            screen.append(ln);
        }
        return screen.toString();

    }
}
