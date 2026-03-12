package org.example;

public class MutableNode<T> {
    private T content;
    private MutableNode<T> nextNode;


    public MutableNode(T content, MutableNode<T> next) {
        this.content = content;
        this.nextNode = next;
    }

    public MutableNode(T content) {
        this.content = content;
        this.nextNode = null;
    }

    public T content() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }

    public MutableNode<T> nextNode() {
        return nextNode;
    }

    public void setNextNode(MutableNode<T> nextNode) {
        this.nextNode = nextNode;
    }

    public MutableNode<T> copyAll() {
        MutableNode<T> newFirst = new MutableNode<>(content);
        var newCurrent = newFirst;
        var current = nextNode();
        while (current != null) {
            MutableNode<T> newNode = new MutableNode<>(current.content());
            newCurrent.setNextNode(newNode);
            newCurrent = newNode;
            current = current.nextNode();
        }
        return  newFirst;
    }

}
