package ru.job4j.departments;

import java.util.*;

/**
 * Класс сортирует каталог департаментов по возрастанию и убыванию.
 */
public class SortDepartments {
    /**
     * Метод сортирует каталог отделов по возрастанию.
     * @param depts - массив отделов.
     * @return - отсортированныцй массив.
     */
    public String[] sortAbsending(String[] depts) {
        String[] fullDepts = completeDeptList(depts);
        TreeSet<String> setDepts = new TreeSet<String>(Arrays.asList(fullDepts));
        return setDepts.toArray(new String[0]);
    }

    /**
     * Метод сортирует каталог отделов по возрастанию.
     * @param depts - массив отделов.
     * @return - отсортированный масив.
     */
    public String[] sortDescending(String[] depts) {
        String[] fullDepts = completeDeptList(depts);
        TreeSet<String> setDepts = new TreeSet<String>(new DesendingComparator());
        setDepts.addAll(Arrays.asList(fullDepts));
        return setDepts.toArray(new String[0]);
    }

    /**
     * Метод дополняет массив отделов недотающими.
     * @param depts - массив отделов.
     * @return - полный массив.
     */
    public String[] completeDeptList(String[] depts) {
        Set<String> fullSet = new HashSet<>();
        for (String subDirectory : depts) {
            StringBuilder deptStr = new StringBuilder();
            for (String part : subDirectory.split("\\\\")) {
                deptStr.append(part);
                fullSet.add(deptStr.toString());
                deptStr.append("\\");
            }
        }
        return fullSet.toArray(new String[0]);
    }

    /**
     * Компаратор для сравнения отделов по убыванию.
     */
    public class DesendingComparator implements Comparator<String> {
        @Override
        public int compare(String o1, String o2) {
            int result = 0;
            for (int i = 0; i < o1.length() && i < o2.length(); i++) {
                result = Character.compare(o2.charAt(i), o1.charAt(i));
                if (result != 0) {
                    break;
                }
            }
            if (result == 0) {
                result = Integer.compare(o1.length(), o2.length());
            }

            return result;
        }
    }
}
