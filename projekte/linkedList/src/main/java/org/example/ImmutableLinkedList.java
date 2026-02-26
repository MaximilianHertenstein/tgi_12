package org.example;


import java.util.*;

import static java.lang.IO.println;

public record ImmutableLinkedList<T>(Node<T> first) implements Iterable<T> {


    public static <T> ImmutableLinkedList<T> of() {
        return new ImmutableLinkedList<>(null);
    }

    public static <T> ImmutableLinkedList<T> of(T e1) {
        return new ImmutableLinkedList<>(new Node<>(e1));
    }

    public static <T> ImmutableLinkedList<T> of(T e1, T e2) {
        return new ImmutableLinkedList<>(new Node<>(e1, new Node<>(e2)));
    }

    public static <T> ImmutableLinkedList<T> of(T e1, T e2, T e3) {
        return new ImmutableLinkedList<>(new Node<>(e1, new Node<>(e2, new Node<>(e3))));
    }

    public boolean isEmpty() {
        return first == null;
    }


    public T getFirst() {
        if (first == null) {
            throw new NoSuchElementException();
        }


        return first().content();
    }


    public void printContents() {
        var current = first;
        while (current != null) {
            println(current.content());
            current = current.nextNode();
        }

    }


    public Node<T> getLastNode() {
        if (first == null) {
            throw new NoSuchElementException();
        }
        var current = first;
        while (current.nextNode() != null) {
            current = current.nextNode();
        }
        return current;
    }

    public T getLast() {
        return getLastNode().content();
    }


    private Node<T> getSecondLastNode() {
        return getNode(size() - 2);
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


    public boolean contains(T o) {
        var current = first;
        while (current != null) {
            if (current.content().equals(o)) {
                return true;
            }
            current = current.nextNode();
        }
        return false;
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


    public Node<T> getNode(int index) {
        if (index < 0) {
            throw new IndexOutOfBoundsException();
        }
        var current = first;
        while (index > 0  && current.nextNode() != null) {
            index--;
            current = current.nextNode();
        }
        if (index == 0 && current != null) {
            return current;
        }
        throw new IndexOutOfBoundsException();
    }

    public T get(int index) {
        return getNode(index).content();
    }


    public int indexOf(T o) {
        var index = 0;
        var current = first;
        while (current != null) {
            if (current.content().equals(o)) {
                return index;
            }
            current = current.nextNode();
            index++;
        }
        return -1;
    }


    public int lastIndexOf(T o) {
        var index = 0;
        var result = -1;
        var current = first;
        while (current != null) {
            if (current.content().equals(o)) {
                result = index;
            }
            current = current.nextNode();
            index++;
        }
        return result;

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


    public List<T> subList(int fromIndex, int toIndex) {
        var result = new ArrayList<T>();
        var index = 0;
        var current = first;
        while (current != null && index <= toIndex) {
            if (index >= fromIndex) {
                result.add(current.content());
            }
            current = current.nextNode();
            index++;
        }
        return result;
    }


    public static <T> ImmutableLinkedList<T> fromList(List<T> xs) {
        Node<T> res = null;
        for (int i = xs.size() - 1; i >= 0; i--) {
            res = new Node<>(xs.get(i), res);
        }
        return new ImmutableLinkedList<>(res);
    }

    public  ImmutableLinkedList<T> reversed() {
        Node<T> res = null;
        Node<T> curr = first;
        while (curr != null) {
            res = new Node<>(curr.content(), res);
            curr = curr.nextNode();

        }
        return new ImmutableLinkedList<>(res);
    }


    public  ImmutableLinkedList<T> copy() {
        return fromList(toArrayList());
    }

    public ImmutableLinkedList<T>  plus(T other) {
        var res = new Node<>(other);
        for (var elem: reversed().toArrayList()) {
            res = new Node<>(elem, res);
        }
        return new ImmutableLinkedList<>(res);
    }


    public ImmutableLinkedList <T> plus(ImmutableLinkedList <T> other) {
        var res = other.first;
        for (var elem: reversed().toArrayList()) {
            res = new Node<>(elem, res);
        }
        return new ImmutableLinkedList<>(res);
    }



//
//    public T[] toArray() {
//        var result = new T[size()];
//        var index = 0;
//        var current = first;
//        while (current != null) {
//            result[index] = current.content();
//            current = current.nextNode();
//            index++;
//        }
//        return result;
//    }


    public Iterator<T> iterator() {
        return new LinkedListIterator<>(first);
    }


    public boolean containsAll(Collection<T> c) {
        for (T o : c) {
            if (!contains(o)) {
                return false;
            }
        }
        return true;
    }


    public ListIterator<T> listIterator(int index) {
        return null;
    }


    public ListIterator<T> listIterator() {
        throw new UnsupportedOperationException();
    }


    public <T1> T1[] toArray(T1[] a) {
        throw new UnsupportedOperationException();
    }


//    public boolean add(T t) {
//        throw new UnsupportedOperationException();
//    }


    public boolean remove(T o) {
        throw new UnsupportedOperationException();
    }


    public boolean addAll(Collection<? extends T> c) {
        throw new UnsupportedOperationException();
    }


    public boolean addAll(int index, Collection<? extends T> c) {
        throw new UnsupportedOperationException();
    }


    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }


    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException();

    }


    public void clear() {
        throw new UnsupportedOperationException();

    }


    public T set(int index, T element) {
        throw new UnsupportedOperationException();

    }


    public void add(int index, T element) {
        throw new UnsupportedOperationException();

    }


    public T remove(int index) {
        throw new UnsupportedOperationException();

    }

}
