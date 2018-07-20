package ru.job4j.tracker;

/**
 * Главный класс.
 */
public class MenuTracker {
    private Input input;
    private Tracker tracker;
    private UserAction[] actions = new UserAction[7];

    public MenuTracker(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    /**
     * Регистрация событий пунктов меню.
     */
    public void fillActions() {
        actions[0] = new AddItem();
        actions[1] = new ShowAllItems();
        actions[2] = new EditItem();
        actions[3] = new DeleteItem();
        actions[4] = new FindItemId();
        actions[5] = new FindItemName();
        actions[6] = new ExitProgram();
    }

    /**
     * Показать информацию о событии.
     */
    public void show() {
        for (int index = 0; index < actions.length; index++) {
            if (actions[index] != null) {
                System.out.println(actions[index].info());
            }
        }
    }

    /**
     * Выполнлить действие по индексу пункта меню.
     * @param key - индекс пунта меню.
     */
    public void select(int key) {
        if (key < 6) {
            this.actions[key].execute(this.input, this.tracker);
        }
    }

    /**
     * Класс для добавлению заметки.
     */
    private class AddItem implements UserAction {
        @Override
        public int key() {
            return 0;
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

        @Override
        public String info() {
            return String.format("%s. %s", this.key(), "Add new Item");
        }
    }

    /**
     * Класс для редактирования заметки.
     */
    private static class EditItem implements UserAction {
        @Override
        public int key() {
            return 2;
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

        @Override
        public String info() {
            return String.format("%s. %s", this.key(), "Edit item");
        }
    }

    /**
     * Класс для удаления заметки.
     */
    private class DeleteItem implements UserAction {
        @Override
        public int key() {
            return 3;
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

        @Override
        public String info() {
            return String.format("%s. %s", this.key(), "Delete item");
        }
    }

    /**
     * Класс для поиска заметки по ID.
     */
    private class FindItemId implements UserAction {
        @Override
        public int key() {
            return 4;
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

        @Override
        public String info() {
            return String.format("%s. %s", this.key(), "Find item by Id");
        }
    }

    /**
     * Класс для поиска заметки по имени.
     */
    private class FindItemName implements UserAction {
        @Override
        public int key() {
            return 5;
        }
        @Override
        public void execute(Input input, Tracker tracker) {
            System.out.println("---------- Поиск заявки по имени ----------");
            String name = input.ask("Введите имя заявки :");
            Item[] items = tracker.findByName(name);
            if (items.length > 0) {
                for (Item item : items) {
                    System.out.println(item);
                }
            } else {
                System.out.println("------------ Ззаявка с getName : " + name + " не найдена-----------");
            }
        }

        @Override
        public String info() {
            return String.format("%s. %s", this.key(), "Find items by name");
        }
    }

    private class ExitProgram implements UserAction {
        @Override
        public int key() {
            return 6;
        }
        @Override
        public void execute(Input input, Tracker tracker) {
        }

        @Override
        public String info() {
            return String.format("%s. %s", this.key(), "Exit Program");
        }
    }

}
