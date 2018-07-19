package ru.job4j.tracker;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Date;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


public class StartUITest {
    private final PrintStream stdout = System.out;
    private final ByteArrayOutputStream outStream = new ByteArrayOutputStream();
    private final String separator = System.lineSeparator();
    private final String menu = new StringBuilder()
            .append("MENU").append(separator)
            .append("0. Add new Item").append(separator)
            .append("1. Show all items").append(separator)
            .append("2. Edit item").append(separator)
            .append("3. Delete item").append(separator)
            .append("4. Find item by Id").append(separator)
            .append("5. Find items by name").append(separator)
            .append("6. Exit Program").append(separator).toString();


    @Before
    public void loadOutput() {
        System.out.println("execute before method");
        System.setOut(new PrintStream(this.outStream));
    }

    @After
    public void backOutput() {
        System.setOut(this.stdout);
        System.out.println("execute after method");
    }


    @Test
    public void whenAddNewItemThenTrackerHasSameItem() {
        StubInput input = new StubInput(new String[]{"0", "Ne Rabotaet", "Vse slomalos", "6"});
        Tracker tracker = new Tracker();
        StartUI startUI = new StartUI(tracker, input);
        startUI.init();
        assertThat(tracker.findAll()[0].getName(), is("Ne Rabotaet"));
    }

    @Test
    public void whenUpdateThenTrackerHasUpdatedValue() {
        // создаём Tracker
        Tracker tracker = new Tracker();
        //Напрямую добавляем заявку
        Item item = tracker.addItem(new Item("test name", "desc", System.currentTimeMillis()));
        //создаём StubInput с последовательностью действий(производим замену заявки)
        Input input = new StubInput(new String[]{"2", item.getId(), "test replace", "заменили заявку", "6"});
        // создаём StartUI и вызываем метод init()
        new StartUI(tracker, input).init();
        // проверяем, что нулевой элемент массива в трекере содержит имя, введённое при эмуляции.
        assertThat(tracker.findById(item.getId()).getName(), is("test replace"));
    }

    @Test
    public void whenDeleteThenTrackerValue() {
        // создаём Tracker
        Tracker tracker = new Tracker();
        Item item = tracker.addItem(new Item("test name", "desc", System.currentTimeMillis()));
        Item item1 = tracker.addItem(new Item("test name1", "desc1", System.currentTimeMillis()));
        Item[] items = new Item[]{item};
        Input input = new StubInput(new String[]{"3", item1.getId(), "6"});
        new StartUI(tracker, input).init();
        assertThat(tracker.findAll(), is(items));
    }

    @Test
    public void whenFindItemId() {
        Tracker tracker = new Tracker();
        Item item = tracker.addItem(new Item("test name", "desc", System.currentTimeMillis()));
        Input input = new StubInput(new String[]{"4", item.getId(), "6"});
        new StartUI(tracker, input).init();
        assertThat(new String(outStream.toByteArray()), is(new StringBuilder()
                .append(menu).append("---------- Поиск заявки по ID ----------").append(separator)
                .append("------------ Заявка номер-").append(item.getId()).append(" --------------").append(separator)
                .append("Имя: ").append(item.getName()).append(separator)
                .append("Описание: ").append(item.getDescription()).append(separator)
                .append("Дата: ").append(new Date(item.getCreate())).append(separator)
                .append("-------------------------------------------------------").append(separator).append(separator)
                .append(menu)
                .toString()));
    }

    @Test
    public void whenFindItemName() {
        Tracker tracker = new Tracker();
        Item item = tracker.addItem(new Item("test name", "desc", System.currentTimeMillis()));
        Input input = new StubInput(new String[]{"5", item.getName(), "6"});
        new StartUI(tracker, input).init();
        assertThat(new String(outStream.toByteArray()), is(new StringBuilder()
                .append(menu).append("---------- Поиск заявки по имени ----------").append(separator)
                .append("------------ Заявка номер-").append(item.getId()).append(" --------------").append(separator)
                .append("Имя: ").append(item.getName()).append(separator)
                .append("Описание: ").append(item.getDescription()).append(separator)
                .append("Дата: ").append(new Date(item.getCreate())).append(separator)
                .append("-------------------------------------------------------").append(separator).append(separator)
                .append(menu)
                .toString()));
    }


}
