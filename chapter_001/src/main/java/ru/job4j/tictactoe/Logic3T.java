package ru.job4j.tictactoe;

/**
 * Реализует логику определения победителя.
 */
public class Logic3T {
    private final Figure3T[][] table;

    public Logic3T(Figure3T[][] table) {
        this.table = table;
    }

    /**
     * Проверяет выграл ли игрок ставящий крестики.
     * @return boolean
     */
    public boolean isWinnerX() {
        boolean winner = false;
        for (int i = 0; i < table.length; i++) {
            if (checkXLine(i, 0, 0, 1)) {
                winner = true;
                break;
            } else if (checkXLine(0, i, 1, 0)) {
                winner = true;
                break;
            }
        }
        if (!winner) {
            winner = (checkXLine(0, 0, 1, 1)) || checkXLine(2, 0, -1, 1);
        }
        return winner;
    }

    /**
     * Проверяет выиграл ли игрок ставящий нолики.
     * @return boolean
     */
    public boolean isWinnerO() {
        boolean winner = false;
        for (int i = 0; i < table.length; i++) {
            if (checkOLine(i, 0, 0, 1)) {
                winner = true;
                break;
            } else if (checkOLine(0, i, 1, 0)) {
                winner = true;
                break;
            }
        }
        if (!winner) {
            winner = (checkOLine(0, 0, 1, 1)) || checkOLine(2, 0, -1, 1);
        }
        return winner;
    }

    /**
     * Проверяет ести ли пустая ячейка на поле.
     * @return boolean
     */
    public boolean hasGap() {
        boolean gap = false;
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table[i].length; j++) {
                if (!table[i][j].hasMarkO() && !table[i][j].hasMarkX()) {
                    gap = true;
                    break;
                }
            }
        }
        return gap;
    }

    /**
     * Проверяет заполнение линии крестиками.
     * @param startX - начальная точка Х.
     * @param startY - начальная точка Y.
     * @param dX - направление приращения координат по X.
     * @param dY - направление приращения координат по Y.
     * @return boolean
     */
    private boolean checkXLine(int startX, int startY, int dX, int dY) {
        boolean result = true;
        for (int i = 0; i < table.length; i++) {
            if (!table[startX + dX * i][startY + dY * i].hasMarkX()) {
                result = false;
                break;
            }
        }
        return result;
    }

    /**
     * Проверяет заполнение линии ноликами.
     * @param startX - начальная точка Х.
     * @param startY - начальная точка Y.
     * @param dX - направление приращения координат по X.
     * @param dY - направление приращения координат по Y.
     * @return boolean
     */    private boolean checkOLine(int startX, int startY, int dX, int dY) {
        boolean result = true;
        for (int i = 0; i < table.length; i++) {
            if (!table[startX + dX * i][startY + dY * i].hasMarkO()) {
                result = false;
                break;
            }
        }
        return result;
    }
}