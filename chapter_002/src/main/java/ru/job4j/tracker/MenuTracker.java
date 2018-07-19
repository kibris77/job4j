package ru.job4j.tracker;

public class MenuTracker {
    private Input input;
    private Tracker tracker;
    private UserAction[] actions = new UserAction[7];

    public MenuTracker(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    public void fillActions() {
        actions[0] = new AddItem();
    }

    public void show() {
        for (int index = 0; index < actions.length; index++) {
            if (actions[index] != null) {
                System.out.println(actions[index].info());
            }
        }
    }

    public void select(int key) {
        if (key < 6) {
            this.actions[key].execute(this.input, this.tracker);
        }
    }

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
}
