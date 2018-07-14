package ru.job4j.professions;

/**
 * Класс доктор.
 */
public class Doctor extends Profession {
    public Doctor(String name, String profession) {
        super(name, profession);
    }

    /**
     * Метод лечит пациента.
     * @param pacient - пациент.
     * @return диагноз.
     */
    public Diagnose heal(Pacient pacient) {
        return new Diagnose("грипп");
    }
}
