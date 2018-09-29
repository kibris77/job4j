package ru.job4j.bomberman;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Класс реализует игровое поле и его мезанику.
 */
public class Board {
    private final ReentrantLock[][] board;
    private final List<Unit> units = new ArrayList<>();
    private final Bomberman bomberman;
    private final int size;
    private final int difficult;
    private Random random = new Random();

    public Board(int size, int difficult) {
        this.size = size;
        this.difficult = difficult;
        this.board = new ReentrantLock[size][size];
        this.bomberman = new Bomberman(new Cell(size / 2, size / 2), this, 'B');
    }

    public ReentrantLock[][] getBoard() {
        return board;
    }

    /**
     * Метод перемещает объекты по полю.
     * @param source - с какой клетки преместить.
     * @param dest - в какую клетку преместиь.
     * @return - boolean.
     */
    public boolean move(Cell source, Cell dest) {
        boolean result = false;
        try {
            if (board[dest.getY()][dest.getX()].tryLock(500, TimeUnit.MILLISECONDS)) {
                //System.out.println("Заблокировано " + dest.getX() + " " + dest.getY());
                board[source.getY()][source.getX()].unlock();
                //System.out.println("Разблокировано " + source.getX() + " " + source.getY());
                result = true;
            }
        } catch (InterruptedException e) {

        }
        return result;
    }

    public int getSize() {
        return size;
    }

    public List<Unit> getUnits() {
        return units;
    }

    /**
     * Метод инициализирует и добавляет элементы на игровое поле.
     */
    private void init() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                board[i][j] = new ReentrantLock();
            }
        }
        units.add(bomberman);
        for (int i = 0; i < difficult; i++) {
            Cell position = new Cell(random.nextInt(size), random.nextInt(size));
            units.add(new Monster(position, this, 'M'));
        }
        for (int i = 0; i < difficult * 3; i++) {
            Cell position = new Cell(random.nextInt(size), random.nextInt(size));
            units.add(new Wall(position, this, 'X'));
        }
    }

    /**
     * Метод запускает игру.
     */
    private void start() {
        init();
        for (int i = 0; i < units.size(); i++) {
            if (units.get(i) instanceof ActiveUnit) {
                new Thread((ActiveUnit) (units.get(i))).start();
            } else {
                units.get(i).moveUnit();
            }
        }
    }

    /**
     * Метод остановливает игру.
     */
    public void stop() {
        for (Unit unit : units) {
            unit.stop();
        }
    }

    public static void main(String[] args) {
        Board board = new Board(16, 4);
        board.start();
        while (true) {
            System.out.println("--------------------------------------");
            for (int i = 0; i < board.getSize(); i++) {
                for (int j = 0; j < board.getSize(); j++) {
                    boolean isUnit = false;
                    for (Unit unit : board.getUnits()) {
                        if (unit.getX() == i && unit.getY() == j) {
                            System.out.print(unit.getImg());
                            isUnit = true;
                            break;
                        }
                    }
                    if (!isUnit) {
                        System.out.print("-");
                    }
                }
                System.out.println();
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {

            }
        }

    }
}
