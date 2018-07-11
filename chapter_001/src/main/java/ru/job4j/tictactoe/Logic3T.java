package ru.job4j.tictactoe;

public class Logic3T {
    private final Figure3T[][] table;

    public Logic3T(Figure3T[][] table) {
        this.table = table;
    }

    public boolean isWinnerX() {
        boolean result = false;
        if (table[0][0].hasMarkX()) {
            if (table[1][1].hasMarkX() && table[2][2].hasMarkX()) {
                result = true;
            }
            if (table[1][0].hasMarkX() && table[2][0].hasMarkX()) {
                result = true;
            }
        } else if (table[0][1].hasMarkX()) {
            if (table[1][1].hasMarkX() && table[2][1].hasMarkX()) {
                result = true;
            }
        } else if (table[0][2].hasMarkX()) {
            if (table[1][1].hasMarkX() && table[2][0].hasMarkX()) {
                result = true;
            }
            if (table[1][2].hasMarkX() && table[2][2].hasMarkX()) {
                result = true;
            }
        } else if (table[1][0].hasMarkX()) {
            if (table[1][1].hasMarkX() && table[1][2].hasMarkX()) {
                result = true;
            }
        } else if (table[2][0].hasMarkX()) {
            if (table[2][1].hasMarkX() && table[2][2].hasMarkX()) {
                result = true;
            }
        }

        return result;
    }

    public boolean isWinnerO() {
        boolean result = false;
        if (table[0][0].hasMarkO()) {
            if (table[1][1].hasMarkO() && table[2][2].hasMarkO()) {
                result = true;
            }
            if (table[1][0].hasMarkO() && table[2][0].hasMarkO()) {
                result = true;
            }
        } else if (table[0][1].hasMarkO()) {
            if (table[1][1].hasMarkO() && table[2][1].hasMarkO()) {
                result = true;
            }
        } else if (table[0][2].hasMarkO()) {
            if (table[1][1].hasMarkO() && table[2][0].hasMarkO()) {
                result = true;
            }
            if (table[1][2].hasMarkO() && table[2][2].hasMarkO()) {
                result = true;
            }
        } else if (table[1][0].hasMarkO()) {
            if (table[1][1].hasMarkO() && table[1][2].hasMarkO()) {
                result = true;
            }
        } else if (table[2][0].hasMarkO()) {
            if (table[2][1].hasMarkO() && table[2][2].hasMarkO()) {
                result = true;
            }
        }
        return result;
    }

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
}

