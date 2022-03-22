package model;

import java.util.ArrayList;
import java.util.List;

public class Tree<T, U> {
    private Node<T, U> root;

    public Tree(T rootValue) {
        root = new Node<T, U>();
        root.setValue(rootValue);
        root.setChildren(new ArrayList<Node<T, U>>());
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
                curr.addChild(node);
            }
            curr = child;
        }
        Node<T, U> leaf = new Node<T, U>(branches.get(n - 1), curr, label);
        curr.addChild(leaf);
    }

    public U find(ArrayList<T> branches) {
        Node<T, U> curr = root;
        for (T branch : branches) {
            curr = curr.findChild(branch);
            if (curr == null) {
                return null;
            }
        }
        return curr.label;
    }

    public static class Node<T, U> {
        private T value;
        private Node<T, U> parent;
        private List<Node<T, U>> children;
        private U label;

        public Node() {}

        public Node(T value, Node<T, U> parent, List<Node<T, U>> children) {
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
                if (children.get(i).getValue() == value) {
                    return children.get(i);
                }
            }
            return null;
        }

        public void addChild(Node<T, U> child) {
            child.setParent(this);;
            if (this.children == null) {
                this.children = new ArrayList<>();
            }
            this.children.add(child);
        }

        public List<Node<T, U>> getChildren() {
            return children;
        }

        public void setChildren(List<Node<T, U>> children) {
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
}
