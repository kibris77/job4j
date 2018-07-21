package ru.job4j.tracker;

import java.util.Scanner;

public class ConsoleInput implements Input {
    private Scanner scanner = new Scanner(System.in);

    @Override
    public String ask(String question) {
        System.out.println(question);
        return scanner.nextLine();
    }

    @Override
    public int ask(String question, int[] range) {
        String answer = ask(question);
        if (answer.matches("[0-9]+")) {
            int key = Integer.valueOf(answer);
            boolean contains = false;
            for (int element : range) {
                if (key == element) {
                    contains = true;
                    break;
                }
            }
            if (contains) {
                return key;
            } else {
                throw new MenuOutExeption("Вы ввели несуществующий номер пункта меню.");
            }
        } else {
            throw new WrongInputExeption("Неправильный ввод. Вы ввели не число.");
        }
    }
}
