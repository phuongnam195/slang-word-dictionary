package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

public class Tree<T, U> implements Serializable {
    private Node<T, U> root;

    public Tree(T rootValue) {
        root = new Node<T, U>();
        root.setValue(rootValue);
        root.setChildren(new ArrayList<Node<T, U>>());
    }

    public Node<T, U> getRoot() {
        return root;
    }

    public void addLeaf(ArrayList<T> branches, U label) {
        Node<T, U> curr = root;
        int n = branches.size();
        for (int i = 0; i < n - 1; i++) {
            T branch = branches.get(i);
            Node<T, U> child = curr.findChild(branch);
            if (child == null) {
                Node<T, U> node = new Node<>();
                node.setValue(branch);
                child = curr.addChild(node);
            }
            curr = child;
        }
        Node<T, U> leaf = new Node<T, U>(branches.get(n - 1), curr, label);
        curr.addChild(leaf);
    }

    public boolean updateLeaf(ArrayList<T> branches, U newLabel) {
        Node<T, U> curr = root;
        for (T branch : branches) {
            curr = curr.findChild(branch);
            if (curr == null) {
                return false;
            }
        }
        curr.setLabel(newLabel);
        return true;
    }

    public boolean removeLeaf(ArrayList<T> branches) {
        Node<T, U> curr = root;
        for (T branch : branches) {
            curr = curr.findChild(branch);
            if (curr == null) {
                return false;
            }
        }
        if (curr.getLabel() == null) {
            return false;
        }
        curr.setLabel(null);
        return true;
    }

    public U getRandom() {
        Random rng = new Random();
        Node<T, U> curr = root;
        while (true) {
            ArrayList<Node<T, U>> children = curr.getChildren();
            int index = rng.nextInt(children.size());
            Node<T, U> child = children.get(index);
            if (child.getLabel() != null) {
                if (child.getChildren().isEmpty()) {
                    return child.getLabel();
                } else {
                    boolean continueFind = rng.nextBoolean();
                    if (continueFind) {
                        curr = child;
                    } else {
                        return child.getLabel();
                    }
                }

            } else {
                curr = child;
            }
        }
    }

    public U findLeaf(ArrayList<T> branches) {
        Node<T, U> curr = root;
        for (T branch : branches) {
            curr = curr.findChild(branch);
            if (curr == null) {
                return null;
            }
        }
        return curr.getLabel();
    }

    public ArrayList<U> filter(ArrayList<T> branches) {
        Node<T, U> curr = root;
        for (T branch : branches) {
            curr = curr.findChild(branch);
            if (curr == null) {
                return new ArrayList<>();
            }
        }
        ArrayList<U> result = new ArrayList<>();
        Stack<Node<T, U>> stack = new Stack<>();
        stack.addAll(curr.getChildren());
        while (!stack.empty()) {
            Node<T, U> p = stack.pop();
            if (p.getLabel() != null) {
                result.add(p.getLabel());
            }
            if (p.getChildren() != null) {
                stack.addAll(p.getChildren());
            }
        }
        return result;
    }
}
