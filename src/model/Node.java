package model;

import java.io.Serializable;
import java.util.ArrayList;

public class Node<T, U> implements Serializable {
    private T value;
    private Node<T, U> parent;
    private ArrayList<Node<T, U>> children = new ArrayList<>();
    private U label;

    public Node() {
    }

    public Node(T value, Node<T, U> parent, ArrayList<Node<T, U>> children) {
        this.setValue(value);
        this.setParent(parent);
        this.setChildren(children);
    }

    public Node(T value, Node<T, U> parent, U label) {
        this.setValue(value);
        this.setParent(parent);
        this.setLabel(label);
    }

    public Node<T, U> findChild(T value) {
        for (int i = 0; i < children.size(); i++) {
            if (children.get(i).getValue().equals(value)) {
                return children.get(i);
            }
        }
        return null;
    }

    public Node<T, U> addChild(Node<T, U> child) {
        child.setParent(this);
        this.children.add(child);
        return child;
    }

    public ArrayList<Node<T, U>> getChildren() {
        return children;
    }

    public void setChildren(ArrayList<Node<T, U>> children) {
        this.children = children;
    }

    public Node<T, U> getParent() {
        return parent;
    }

    public void setParent(Node<T, U> parent) {
        this.parent = parent;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public U getLabel() {
        return label;
    }

    public void setLabel(U label) {
        this.label = label;
    }
}
