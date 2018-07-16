package ru.job4j.tracker;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


public class StartUITest {
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

}
