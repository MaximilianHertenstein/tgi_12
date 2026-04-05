package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class MutableList<T>   {
    private MutableNode<T>  first;

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


    public MutableNode<T> getNode(int index) {
        if (index < 0) {
            throw new IndexOutOfBoundsException();
        }
        if (first == null ) {
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

    public void addAll(MutableList<T> other) {
        if (first == null) {
            first = other.first;
        }
        else {
            var last = getLastNode();
            last.setNextNode(other.first);
        }
    }


    public T removeFirst(){
        if (first == null) {
            throw new NoSuchElementException();
        }
        var result = first.content();
        first = first.nextNode();
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



    public  MutableList<T> copy() {
        if (first != null) {
            return new MutableList<T>(first.copyAll());
        }
        return new MutableList<T>();


    }



}
