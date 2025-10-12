package org.example;

import java.util.Iterator;

public class LinkedListIterator<T> implements Iterator<T> {
    Node<T> current;

    public LinkedListIterator(ImmutableLinkedList<T> linkedList) {
        this.current = linkedList.first();
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
