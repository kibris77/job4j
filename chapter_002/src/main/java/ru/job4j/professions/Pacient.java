package ru.job4j.professions;

/**
 * Класс пациент.
 */
public class Pacient {
    private String name;

    /**
     *
     * @param name - имя пациента.
     */
    public Pacient(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
