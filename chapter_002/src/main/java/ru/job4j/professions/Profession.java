package ru.job4j.professions;

/**
 * Базовый класс профессий.
 */
public abstract class Profession {
    private String name;
    private String profession;

    /**
     *
     * @param name - имя.
     * @param profession - профессия.
     */
    public Profession(String name, String profession) {
        this.name = name;
        this.profession = profession;
    }

    public String getName() {
        return name;
    }

    public String getProfession() {
        return profession;
    }
}
