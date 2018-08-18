package ru.job4j.tree;

import java.util.*;

/**
 * Класс реализующий дерево.
 * @param <E>
 */
public class Tree<E extends Comparable<E>> implements SimpleTree<E> {
    private Node<E> root;

    public Tree(E rootValue) {
        this.root = new Node<>(rootValue);
    }

    /**
     * Метод добвавляет дочерний узел к родительскому узлу.
     * @param parent parent.
     * @param child child.
     * @return boolean.
     */
    @Override
    public boolean add(E parent, E child) {
        boolean result = true;
        Node<E> nodeToAdd = findBy(parent).get();
        for (Node<E> leaf : nodeToAdd.leaves()) {
            if (leaf.eqValue(child)) {
                result = false;
                break;
            }
        }
        if (result) {
            nodeToAdd.add(new Node<>(child));
        }
        return result;
    }

    /**
     * Метод находит узел в дереве по значению.
     * @param value - значение.
     * @return - узел.
     */
    @Override
    public Optional<Node<E>> findBy(E value) {
        Optional<Node<E>> rsl = Optional.empty();
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (el.eqValue(value)) {
                rsl = Optional.of(el);
                break;
            }
            for (Node<E> child : el.leaves()) {
                data.offer(child);
            }
        }
        return rsl;

    }

    /**
     * Возвращает список всех эллекментов дерева.
     * @param node - элемент дерева.
     * @return - спискок жлементов дерева.
     */
    private List<Node<E>> addAllChildreValue(Node<E> node) {
        List<Node<E>> childrenList = new ArrayList<>();
        childrenList.add(node);
        for (Node<E> childNode : node.leaves()) {
            if (childNode.leaves().size() > 0) {
                addAllChildreValue(childNode);
            }
            childrenList.add(childNode);
        }
        return childrenList;
    }

    private void checkUniqueNode() {

    }

    /**
     * Метод возвращает итератор.
     * @return - итератор.
     */
    @Override
    public Iterator<Node<E>> iterator() {
        return addAllChildreValue(root).iterator();
    }
}
