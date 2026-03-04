package org.example;

import java.util.EmptyStackException;
import java.util.NoSuchElementException;

public class Queue<T> {
    MutableNode<T> first;
    MutableNode<T> last;


    public Queue() {
        first = null;
        last = null;
    }
//    private Queue(MutableNode<T>  first) {
//         this.first = first;
//         this.last = first;
//    }

  //  public static <T> Queue<T> of() {
//        return new Queue<>();
//    }

//    public static <T> Queue<T> of(T e1) {
//
//        var q = new Queue<T>();
//        var n = new MutableNode<>(e1);
//        q.first = n;
//        q.last = n;
//        return q;
//    }
//
//    public static <T> Queue<T> of(T e1, T e2) {
//        var last = new MutableNode<T>(e2);
//        var first = new MutableNode<T>(e1, last);
//        var q =  new Queue<T>();
//        q.last = last;
//        q.first = first;
//        return q;
//    }
//
//    public static <T> Queue<T> of(T e1, T e2, T e3) {
//        var last = new MutableNode<T>(e3);
//        var first = new MutableNode<T>(e1, new MutableNode<>(e2, last));
//        var q =  new Queue<T>();
//        q.last = last;
//        q.first = first;
//        return q;
//    }

    public boolean isEmpty() {
        return first == null;
    }


    public void enqueue(T x) {
        var newNode = new MutableNode<T>(x);
        if (isEmpty()) {
            first = newNode;
            last = newNode;
            return;
        }
        last.setNextNode(newNode);
        last = newNode;
    }

    public T dequeue() {
        if (isEmpty()) throw new NoSuchElementException();
        var result = first.content();
        first = first.nextNode();
        if (first == null) {
            last = null;
        }
        return result;
    }
}
