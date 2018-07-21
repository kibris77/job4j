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
        boolean contains = false;
        if (!answer.matches("[0-9]+")) {
            throw new WrongInputExeption("Неправильный ввод. Вы ввели не число.");
        }
        int key = Integer.valueOf(answer);
        for (int element : range) {
            if (key == element) {
                contains = true;
                break;
            }
        }
        if (!contains) {
            throw new MenuOutExeption("Вы ввели несуществующий номер пункта меню.");
        }
        return key;
    }
}
