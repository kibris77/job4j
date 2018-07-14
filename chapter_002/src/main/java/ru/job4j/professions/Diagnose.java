package ru.job4j.professions;

/**
 * Класс диагноз.
 */
public class Diagnose {
    private String diagnose;

    /**
     *
     * @param diagnose - диагноз.
     */
    public Diagnose(String diagnose) {
        this.diagnose = diagnose;
    }

    public String getDiagnose() {
        return diagnose;
    }
}
