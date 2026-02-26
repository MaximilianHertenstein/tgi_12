package org.example;

import org.junit.jupiter.api.Test;

import java.util.List;

import static java.lang.IO.println;
import static org.junit.jupiter.api.Assertions.*;


class LinkedListTest {
    ImmutableLinkedList<Integer> xs = new ImmutableLinkedList(new Node(1, new Node(2, new Node(3, new Node(1,null)))));
    ImmutableLinkedList<Integer> empty = new ImmutableLinkedList(null);
    @org.testng.annotations.Test
    void size() {

        assertEquals(4, xs.size());


        assertEquals(0, empty.size());
    }

    @Test
    void isEmpty() {
        assertFalse(xs.isEmpty());
        assertTrue(empty.isEmpty());
    }

    @Test
    void contains() {
        assertTrue(xs.contains(1));
        assertTrue(xs.contains(2));
        assertTrue(xs.contains(3));
        assertFalse(xs.contains(4));
    }

    @Test
    void get() {
        assertEquals(1, xs.get(0));
        assertEquals(2, xs.get(1));
        assertEquals(3, xs.get(2));
    }

    @Test
    void indexOf() {
        assertEquals(-1, empty.indexOf(1));
        assertEquals(-1, xs.indexOf(4));
        assertEquals(0, xs.indexOf(1));
        assertEquals(1, xs.indexOf(2));
        assertEquals(2, xs.indexOf(3));
    }

    @Test
    void lastIndexOf() {
        assertEquals(-1, empty.lastIndexOf(1));
        assertEquals(-1, xs.lastIndexOf(4));
        assertEquals(3, xs.lastIndexOf(1));
        assertEquals(1, xs.lastIndexOf(2));
        assertEquals(2, xs.lastIndexOf(3));
    }

    @Test
    void iterator() {
        var emptyIterator = empty.iterator();
        assertFalse(emptyIterator.hasNext());
        var xsIterator = xs.iterator();
        assertTrue(xsIterator.hasNext());
        var x1 = xsIterator.next();
        assertEquals(1, x1);
        assertTrue(xsIterator.hasNext());
        var x2 = xsIterator.next();
        assertEquals(2, x2);
        assertTrue(xsIterator.hasNext());
        var x3 = xsIterator.next();
        assertEquals(3, x3);
        assertTrue(xsIterator.hasNext());
        var x4 = xsIterator.next();
        assertEquals(1, x4);
        assertFalse(xsIterator.hasNext());
    }

    @Test
    void subList() {
        assertEquals(List.of(2,3 ), xs.subList(1, 2));
        assertEquals(List.of(1, 2, 3, 1 ), xs.subList(0, 3));
        assertEquals(List.of(3, 1), xs.subList(2, 3));
    }


    @Test
    void reversed() {
        assertEquals(ImmutableLinkedList.of(5,6,7), ImmutableLinkedList.of(7,6,5).reversed());
        assertEquals(ImmutableLinkedList.of(), ImmutableLinkedList.of().reversed());
    }

    @Test
    void reversed2() {
        assertEquals(ImmutableLinkedList.fromList(List.of(3,7,9,10,11,20,2)), ImmutableLinkedList.fromList(List.of(2,20,11,10,9,7,3)).reversed());
    }


    @Test
    void add() {
        assertEquals(ImmutableLinkedList.fromList(List.of(1,2,3,1,4,5)), xs.plus(ImmutableLinkedList.fromList(List.of(4,5))));
        assertEquals(ImmutableLinkedList.fromList(List.of(5,6,7,8,9,10)), (ImmutableLinkedList.of(5, 6, 7)).plus(ImmutableLinkedList.of(8,9,10)));

    }



//    @Test
//    void testLoop() {
//        var acc = 0;
//        for (var x : xs) {
//            acc+=x;
//        }
//        assertEquals(7,acc);
//    }



    @Test
    void listIterator() {
        var xs = ImmutableLinkedList.fromList(List.of(1,2,3));
        for (var x: xs) {
            println(x);
        }
    }

    @Test
    void toArray() {
    }



    @Test
    void testListIterator() {
    }



    @Test
    void testToArray() {
    }

    @Test
    void first() {
    }
}