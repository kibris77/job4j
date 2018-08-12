package ru.job4j.cyclicity;

public class Cyclicity {
    /**
     * Метод проверяет зацикленность спсисков.
     * @param first - первый элемент списка.
     * @return - boolean
     */
    boolean hasCycle(Node first) {
        boolean result = false;
        Node one = first;
        Node two = first;
        while (one != null && two != null) {
            one = one.next;
            two = two.next.next;
            if (one.equals(two)) {
                result = true;
                break;
            }
        }
        return result;
    }
}
