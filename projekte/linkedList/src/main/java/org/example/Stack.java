package org.example;

import java.util.EmptyStackException;

public class Stack<T> {
    private Node<T> first;

    public Stack(Node<T> first) {
        this.first = first;
    }

    public Stack() {
        this.first = null;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public T getFirst() {
        if (first == null) {
            throw new EmptyStackException();
        }
        return first.content();
    }

    public void push(T x) {
        first = new Node<T>(x, first);
    }

    public T pop() {
        if (isEmpty()) throw new EmptyStackException();
        var result = first.content();
        first = first.nextNode();
        return result;
    }
}
