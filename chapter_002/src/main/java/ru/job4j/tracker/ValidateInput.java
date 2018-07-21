package ru.job4j.tracker;

/**
 * Класс проверяет данные которые ввел пользователь.
 */
public class ValidateInput extends ConsoleInput {
    /**
     * Метод просит ввестипользователя данные.
     * @param question - текст запроса.
     * @param range - массив возможных значений ответа.
     * @return ключ пунта меню.
     */
    @Override
    public int ask(String question, int[] range) {
        boolean invalid = true;
        int key = -1;
        do {
            try {
                key = super.ask(question, range);
                invalid = false;
            } catch (WrongInputExeption ew) {
                System.out.println(ew.getMessage());
            } catch (MenuOutExeption em) {
                System.out.println(em.getMessage());
            }
        } while (invalid);
        return key;
    }
}
