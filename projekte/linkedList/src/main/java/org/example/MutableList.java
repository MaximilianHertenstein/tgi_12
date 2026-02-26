package org.example;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;

public class MutableList<T>   {
    MutableNode<T>  first;

    public MutableList(MutableNode<T>  first) {
        this.first = first;
    }

    public MutableList() {
this.first  = null; }


    public static <T> MutableList<T> of() {
        return new MutableList<>(null);
    }

    public static <T> MutableList<T> of(T e1) {
        return new MutableList<>(new MutableNode<>(e1));
    }

    public static <T> MutableList<T> of(T e1, T e2) {
        return new MutableList<>(new MutableNode<>(e1, new MutableNode<>(e2)));
    }

    public static <T> MutableList<T> of(T e1, T e2, T e3) {
        return new MutableList<>(new MutableNode<>(e1, new MutableNode<>(e2, new MutableNode<>(e3))));
    }

    public boolean isEmpty() {
        return first == null;
    }



    public int size() {
        var current = first;
        int size = 0;
        while (current != null) {
            current = current.nextNode();
            size++;

        }
        return size;
    }

    public String show() {
        if (first == null) {
            return "[]";
        }
        var current = first;
        var res = current.content().toString();
        while (current.nextNode() != null) {
            current = current.nextNode();
            res = res + ", " + current.content();
        }
        return "[" + res + "]";
    }

    public MutableNode<T> getLastNode() {
        if (first == null) {
            throw new NoSuchElementException();
        }
        var current = first;
        while (current.nextNode() != null) {
            current = current.nextNode();
        }
        return current;
    }

    private MutableNode<T> getSecondLastNode() {
        return getNode(size() - 2);
    }

    public MutableNode<T> getNode(int index) {
        if (index < 0) {
            throw new IndexOutOfBoundsException();
        }
        var current = first;
        while (index > 0 && current.nextNode() != null  ) {
            index--;
            current = current.nextNode();
        }
        if (index == 0 && current != null) {
            return current;
        }
        throw new IndexOutOfBoundsException();
    }

    public ArrayList<T> toArrayList() {
        var result = new ArrayList<T>();
        var current = first;
        while (current != null) {
            result.add(current.content());
            current = current.nextNode();
        }
        return result;
    }

    public static <T> MutableList<T> fromList(List<T> xs) {
        MutableNode<T> res = null;
        for (int i = xs.size() - 1; i >= 0; i--) {
            res = new MutableNode<>(xs.get(i), res);
        }
        return new MutableList<>(res);
    }


    public void clear(){
        first = null;
    }

    public T replace(int index, T newElem){
        var node = getNode(index);
        var result = node.content();
        node.setContent(newElem);
        return result;
    }


    public void addFirst(T newElem){
        first = new MutableNode<>(newElem,first);
    }


    public void add(T newElem){
        if (first == null) {
            first = new MutableNode<>(newElem);
        }
        else {
            var last = getLastNode();
            last.setNextNode(new MutableNode<>(newElem));
        }
    }

    public void add(int index, T newElem){
        if (index == 0) {
            addFirst(newElem);
            return;
        }
        var nodeBefore = getNode(index-1);
        var nodeAfter = nodeBefore.nextNode();
        nodeBefore.setNextNode(new MutableNode<>(newElem,nodeAfter));
    }

    public T removeFirst(){
        if (first == null) {
            throw new NoSuchElementException();
        }
        var result = first.content();
        first = first.nextNode();
        return result;
    }

    public T removeLast(){
        if (first == null) {
            throw new NoSuchElementException();
        }
        if (first.nextNode() == null) {
            return removeFirst();
        }
        var nodeBefore = getSecondLastNode();
        var result = nodeBefore.nextNode().content();
        nodeBefore.setNextNode(null);
        return result;
    }



    public T remove(int index){
        if (index == 0) {
            return removeFirst();
        }
        var nodeBefore = getNode(index-1);
        var nodeToDelete = nodeBefore.nextNode();
        if (nodeToDelete == null) {
            throw new IndexOutOfBoundsException();
        }
        nodeBefore.setNextNode(nodeToDelete.nextNode());
        return nodeToDelete.content();
    }










}
