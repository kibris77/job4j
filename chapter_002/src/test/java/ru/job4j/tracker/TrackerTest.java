package ru.job4j.tracker;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


public class TrackerTest {
    @Test
    public void whenAddNewItemThenTrackerHasSameItem() {
        Tracker tracker = new Tracker(new Config());
        Item item = new Item("test1", "testDescription", 123L);
        Item item1 = new Item("test1", "test1desc]", 131L);
        tracker.addItem(item);
        tracker.addItem(item1);
        assertThat(tracker.findAll().get(1), is(item1));
    }

    @Test
    public void whenReplaceNameThenReturnNewName() {
        Tracker tracker = new Tracker(new Config());
        Item previous = new Item("test1", "testDescription", 123L);
        // Добавляем заявку в трекер. Теперь в объект проинициализирован id.
        tracker.addItem(previous);
        // Создаем новую заявку.
        Item next = new Item("test2", "testDescription2", 1234L);
        // Проставляем старый id из previous, который был сгенерирован выше.
        next.setId(previous.getId());
        // Обновляем заявку в трекере.
        tracker.replace(previous.getId(), next);
        // Проверяем, что заявка с таким id имеет новые имя test2.
        assertThat(tracker.findById(previous.getId()).getName(), is("test2"));
    }

    @Test
    public void whenFindByIdThenReturnName() {
        Tracker tracker = new Tracker(new Config());
        Item item = new Item("test1", "testDescription", 123L);
        tracker.addItem(item);
        Item item1 = new Item("test2", "testDescription2", 1234L);
        tracker.addItem(item1);
        assertThat(tracker.findById(item1.getId()).getName(), is("test2"));
    }

    @Test
    public void whenFindByNameThenReturnId() {
        Tracker tracker = new Tracker(new Config());
        Item item = new Item("test1", "testDescription", 123L);
        tracker.addItem(item);
        Item item1 = new Item("test2", "testDescription2", 1234L);
        tracker.addItem(item1);
        assertThat(tracker.findByName(item.getName()).get(0).getId(), is(item.getId()));
    }
    @Test
    public void whenFindAllThenReturnArray() {
        Tracker tracker = new Tracker(new Config());
        Item item = new Item("test1", "testDescription", 123L);
        tracker.addItem(item);
        Item item1 = new Item("test2", "testDescription2", 1234L);
        tracker.addItem(item1);
        Item[] expected = new Item[] {item, item1};
        Item[] result = tracker.findAll().toArray(new Item[0]);
        assertThat(result, is(expected));
    }

    @Test
    public void whenDeleteByIdThenReturnArray() {
        Tracker tracker = new Tracker(new Config());
        Item item = new Item("test1", "testDescription", 123L);
        tracker.addItem(item);
        Item item1 = new Item("test2", "testDescription2", 1234L);
        tracker.addItem(item1);
        Item item2 = new Item("test3", "testdesc3", 142L);
        tracker.addItem(item2);
        Item item3 = new Item("test4", "testdesc4", 1422L);
        tracker.addItem(item3);
        Item[] expected = new Item[] {item1, item2, item3};
        tracker.delete(item.getId());
        Item[] result = tracker.findAll().toArray(new Item[0]);
        assertThat(result, is(expected));
        tracker.delete(item2.getId());
        result = tracker.findAll().toArray(new Item[0]);
        expected = new Item[]{item1, item3};
        assertThat(result, is(expected));
    }
}
