package org.example;

record   Node<T> (T content, Node<T> nextNode) {
    public Node(T content) {
        this(content, null);
    }
}
