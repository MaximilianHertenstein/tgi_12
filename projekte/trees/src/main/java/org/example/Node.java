package org.example;


public record Node<T>(T content, Node<T> left, Node<T> right) {

    boolean isLeaf(){
        return  left == null && right == null;
    }

    Node(T content){
        this(content, null, null);
    }

}
