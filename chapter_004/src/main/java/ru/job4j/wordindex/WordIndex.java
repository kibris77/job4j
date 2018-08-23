package ru.job4j.wordindex;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Класс составляет таблицу индексов слов находящихся в файле.
 */
public class WordIndex {
    private Map<String, Set<Integer>> wordIndex = new TreeMap<>();
    private String file;

    void setFile(String file) {
        this.file = file;
    }

    /**
     * Мтеод сохраняет загруженный файл в виде строки.
     * @param fileName - имя файла.
     */
    public void loadFile(String fileName) {
        String s;
        StringBuilder result = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            while ((s = reader.readLine()) != null) {
                result.append(s);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        this.file = result.toString();
    }

    /**
     * Метод возвращает набор индексов слова в файле.
     * @param searchWord - искомое слово.
     * @return - Set.
     */
    public Set<Integer> getIndexes4Word(String searchWord) {
        return wordIndex.get(searchWord);
    }

    /**
     * Метод формирует тблицу индексов слов.
     */
    public void setIndexes() {
        if (this.file == null) {
            throw new NoSuchElementException();
        }
        Pattern pattern = Pattern.compile("[A-Za-zА-Яа-я]+");
        Matcher matcher = pattern.matcher(file);
        while (matcher.find()) {
            String word = matcher.group();
            int index = matcher.start();
            if (wordIndex.get(word) == null) {
                Set<Integer> set = new TreeSet<>();
                set.add(index);
                wordIndex.put(word, set);
            } else {
                wordIndex.get(word).add(index);
            }
        }
    }
}
