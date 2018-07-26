package ru.job4j.coffe;

import java.util.Arrays;

/**
 * КЛасс реалищзует функционал коффемашины.
 */
public class CoffeMachine {
    private int[] coins = new int[] {10, 5, 2, 1};

    /**
     * Метод возвращает массив из монет от 10 до 1, которые отдает автомат ввиде сдачи.
     * @param value - количество вносимых денег.
     * @param price - стоимость кофе.
     * @return - массив монет здачи.
     * @throws NotEnoughMoneyExeption - возникает если недостаточно денег внесли в автомат.
     */
    public int[] change(int value, int price) throws NotEnoughMoneyExeption {
        if (value < price) {
            throw new NotEnoughMoneyExeption("Недосточно денег");
        }
        if (value <= 0 || price <= 0) {
            throw new IllegalArgumentException();
        }
        int[] moneyBack = new int[100];
        int[] result = null;
        int indexCoin = 0;
        int indexRes = 0;
        int change = value - price;
        while (change != 0) {
            if (change >= coins[indexCoin]) {
                moneyBack[indexRes++] = coins[indexCoin];
                change -= coins[indexCoin];
            } else {
                indexCoin++;
            }
        }
        if (indexCoin == 0) {
            result = Arrays.copyOf(moneyBack, 1);
        } else {
            result = Arrays.copyOf(moneyBack, indexRes);
        }
        return result;
    }
}
