package ru.job4j.tracker;

/**
 * Это поле содержит последовательность ответов пользователя.
 * Например. Если пользователь
 * хочет выбрать добавление новой заявки ему нужно ввести:
 * 0 - выбор пункта меня "добавить новую заявку".
 * name - имя заявки
 * desc - описание заявки
 * 6 - выйти из трекера.
 */
public class StubInput implements Input {
    private final String[] value;
    private int position;

    public StubInput(final String[] value) {
        this.value = value;
    }

    @Override
    public String ask(String question) {
        return value[position++];
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
