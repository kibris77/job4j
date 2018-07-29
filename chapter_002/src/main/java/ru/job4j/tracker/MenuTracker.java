package ru.job4j.tracker;

import java.util.ArrayList;
import java.util.List;

/**
 * Главный класс.
 */
public class MenuTracker {
    private Input input;
    private Tracker tracker;
    private List<UserAction> actions = new ArrayList<>();

    public MenuTracker(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    /**
     * Регистрация событий пунктов меню.
     */
    public void fillActions(StartUI startUI) {
        actions.add(new AddItem(0, "Add new Item"));
        actions.add(new ShowAllItems(1, "Show all items"));
        actions.add(new EditItem(2, "Edit item"));
        actions.add(new DeleteItem(3, "Delete item"));
        actions.add(new FindItemId(4, "Find item by Id"));
        actions.add(new FindItemName(5, "Find items by name"));
        actions.add(new ExitProgram(6, "Exit Program", startUI));
    }

    /**
     * Возвращает номера пунктов меню.
     * @return массив номеров.
     */
    public int[] getMenuNumbers() {
        int[] menuNumers = new int[actions.size()];
        for (int index = 0; index < menuNumers.length; index++) {
            menuNumers[index] = actions.get(index).key();
        }
        return menuNumers;
    }

    /**
     * Показать информацию о событии.
     */
    public void show() {
        for (UserAction action : actions) {
            System.out.println(action.info());
        }
    }

    /**
     * Выполнлить действие по индексу пункта меню.
     * @param key - индекс пунта меню.
     */
    public void select(int key) {
        this.actions.get(key).execute(this.input, this.tracker);
    }

    /**
     * Класс для добавлению заметки.
     */
    private class AddItem extends BaseAction {
        public AddItem(int key, String name) {
            super(key, name);
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            System.out.println("------------ Добавление новой заявки --------------");
            String name = input.ask("Введите имя заявки :");
            String desc = input.ask("Введите описание заявки :");
            Item item = new Item(name, desc, System.currentTimeMillis());
            tracker.addItem(item);
            System.out.println("------------ Новая заявка с getId : " + item.getId() + "-----------");
        }
    }

    /**
     * Класс для редактирования заметки.
     */
    private static class EditItem extends BaseAction {
        public EditItem(int key, String name) {
            super(key, name);
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            System.out.println("------------ Редактирование заявки --------------");
            String id = input.ask("Введите номер заявки :");
            String name = input.ask("Введите имя заявки :");
            String desc = input.ask("Введите описание заявки :");
            Item item = new Item(name, desc, System.currentTimeMillis());
            if (tracker.replace(id, item)) {
                System.out.println("-------- Отредактирована заявка с getId : " + id + "-----------");
            } else {
                System.out.println("------------ Ззаявка с getId : " + id + " не найдена-----------");
            }
        }
    }

    /**
     * Класс для удаления заметки.
     */
    private class DeleteItem extends BaseAction {
        public DeleteItem(int key, String name) {
            super(key, name);
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            System.out.println("------------ Удаление заявки --------------");
            String id = input.ask("Введите номер заявки :");
            if (tracker.delete(id)) {
                System.out.println("------------ Заявка с getId : " + id + " удалена-----------");
            } else {
                System.out.println("------------ Ззаявка с getId : " + id + " не найдена-----------");
            }
        }
    }

    /**
     * Класс для поиска заметки по ID.
     */
    private class FindItemId extends BaseAction {
        public FindItemId(int key, String name) {
            super(key, name);
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            System.out.println("---------- Поиск заявки по ID ----------");
            String id = input.ask("Введите номер заявки :");
            Item item = tracker.findById(id);
            if (item != null) {
                System.out.println(item);
            } else {
                System.out.println("------------ Ззаявка с getId : " + id + " не найдена-----------");
            }
        }
    }

    /**
     * Класс для поиска заметки по имени.
     */
    private class FindItemName extends BaseAction {
        public FindItemName(int key, String name) {
            super(key, name);
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            System.out.println("---------- Поиск заявки по имени ----------");
            String name = input.ask("Введите имя заявки :");
            List<Item> items = tracker.findByName(name);
            if (items.size() > 0) {
                for (Item item : items) {
                    System.out.println(item);
                }
            } else {
                System.out.println("------------ Ззаявка с getName : " + name + " не найдена-----------");
            }
        }
    }

    private class ExitProgram extends BaseAction {
        private StartUI startUI;
        public ExitProgram(int key, String name, StartUI startUI) {
            super(key, name);
            this.startUI = startUI;
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            startUI.stop();
        }
    }
}
