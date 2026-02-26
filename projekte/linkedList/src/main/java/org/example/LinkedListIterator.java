package org.example;

import java.util.Iterator;

class LinkedListIterator<T> implements Iterator<T> {
    Node<T> current;

    public LinkedListIterator(Node<T> current) {
        this.current = current;
    }

    @Override
    public boolean hasNext() {
        return current != null;
    }

    @Override
    public T next() {
        var result = current.content();
        current = current.nextNode();
        return result;
    }
}
