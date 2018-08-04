package ru.job4j.departments;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


public class SortDepartmentsTest {
    @Test
    public void whenSorAbsendingThen() {
        String[] depts = new String[] {"K1\\SK1", "K1\\SK2", "K1\\SK1\\SSK1",
                                        "K1\\SK1\\SSK2", "K2", "K2\\SK1\\SSK1",
                                        "K2\\SK1\\SSK2"};
        SortDepartments sort = new SortDepartments();
        String[] result = sort.sortAbsending(depts);
        String[] expected = new String[] {"K1", "K1\\SK1", "K1\\SK1\\SSK1",
                                            "K1\\SK1\\SSK2", "K1\\SK2", "K2", "K2\\SK1", "K2\\SK1\\SSK1",
                                            "K2\\SK1\\SSK2"};
        assertThat(result, is(expected));
    }

    @Test
    public void whenSorDescendingThen() {
        String[] depts = new String[] {"K1\\SK1", "K1\\SK2", "K1\\SK1\\SSK1",
                "K1\\SK1\\SSK2", "K2", "K2\\SK1\\SSK1",
                "K2\\SK1\\SSK2"};
        SortDepartments sort = new SortDepartments();
        String[] result = sort.sortDescending(depts);
        String[] expected = new String[] {"K2", "K2\\SK1", "K2\\SK1\\SSK2",
                            "K2\\SK1\\SSK1", "K1", "K1\\SK2", "K1\\SK1", "K1\\SK1\\SSK2",
                            "K1\\SK1\\SSK1"};
        assertThat(result, is(expected));
    }
}
