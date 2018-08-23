package ru.job4j.wordindex;

import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class WordIndexTest {
    WordIndex index = new WordIndex();
    String file = "Aaaa bbbb cccc bbbb cccc";

    @Before
    public void setUp() {
        index.setFile(file);
    }

    @Test
    public void whenFindBBBBThen5() {
        index.setIndexes();
        Iterator iterator = index.getIndexes4Word("bbbb").iterator();
        assertThat(iterator.next(), is(file.indexOf("bbbb")));
    }

    @Test
    public void whenFindCCCCThen20() {
        index.setIndexes();
        Iterator iterator = index.getIndexes4Word("cccc").iterator();
        iterator.next();
        assertThat(iterator.next(), is(file.lastIndexOf("cccc")));
    }
}
