package org.example;

import java.util.*;

public record ImmutableLinkedList<T>(Node<T> first) implements List<T> {


    @Override
    public boolean isEmpty() {
        return first == null;
    }



    @Override
    public int size() {
        var current = first;
        int size = 0;
        while (current != null) {
            current = current.nextNode();
            size++;

        }
        return size;
    }



    @Override
    public boolean contains(Object o) {
        var current = first;
        while (current != null) {
            if (current.content().equals(o)) {
                return true;
            }
            current = current.nextNode();
        }
        return false;
    }


    @Override
    public String toString(){
        var current = first;
        var res = current.content().toString();
        while (current.nextNode() != null) {
            current = current.nextNode();
            res = res + ", " + current.content();
        }
        return "[" +res + "]";
    }


    @Override
    public T get(int index) {
        var current = first;
        while (current.nextNode() != null && index > 0) {
            index--;
            current = current.nextNode();
        }
        if(index == 0) {
            return current.content();
        }
        throw new IndexOutOfBoundsException();
    }

    @Override
    public int indexOf(Object o) {
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

    @Override
    public int lastIndexOf(Object o) {
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



    @Override
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

    @Override
    public Object[] toArray() {
        var result = new Object[size()];
        var index = 0;
        var current = first;
        while (current != null) {
            result[index] = current.content();
            current = current.nextNode();
            index++;
        }
        return result;
    }

    @Override
    public Iterator<T> iterator() {
        return new LinkedListIterator<T>(this);
    }


    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object o : c) {
            if (!contains(o)) {
                return false;
            }
        }
        return true;
    }



    @Override
    public ListIterator<T> listIterator(int index) {
        return null;
    }




    @Override
    public ListIterator<T> listIterator() {
        throw new UnsupportedOperationException();
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        throw new UnsupportedOperationException();
    }











    @Override
    public boolean add(T t) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean remove(Object o) {
        throw new UnsupportedOperationException();
    }




    @Override
    public boolean addAll(Collection<? extends T> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException();

    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException();

    }


    @Override
    public T set(int index, T element) {
        throw new UnsupportedOperationException();

    }

    @Override
    public void add(int index, T element) {
        throw new UnsupportedOperationException();

    }

    @Override
    public T remove(int index) {
        throw new UnsupportedOperationException();

    }

}
