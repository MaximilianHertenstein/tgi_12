// java
package org.example;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

class MutableListTest {

    @Test
    void clear_emptiesList() {
        var list = MutableList.of(1, 2, 3);
        list.clear();
        assertTrue(list.isEmpty());
        // clearing an already empty list stays empty
        list.clear();
        assertTrue(list.isEmpty());
    }

    @Test
    void replace_updatesAndReturnsOldValue() {
        var list = MutableList.of(1, 2, 3);
        Integer old = list.replace(1, 99);
        assertEquals(2, old);
        assertEquals(List.of(1, 99, 3), list.toArrayList());
    }

    @Test
    void replace_outOfBounds_throws() {
        var list = MutableList.of(1, 2);
        assertThrows(IndexOutOfBoundsException.class, () -> list.replace(5, 10));
        assertThrows(IndexOutOfBoundsException.class, () -> list.replace(-1, 10));
    }

    @Test
    void addFirst_and_add_appendCorrectly() {
        var list = MutableList.of();
        assertTrue(list.isEmpty());

        list.addFirst(5);
        assertFalse(list.isEmpty());
        assertEquals(List.of(5), list.toArrayList());

        list.addFirst(4);
        assertEquals(List.of(4, 5), list.toArrayList());

        list.add(6);
        assertEquals(List.of(4, 5, 6), list.toArrayList());
    }

    @Test
    void add_atIndex_insertsAtPosition() {
        var list = MutableList.of(1, 3);
        list.add(1, 2); // insert 2 at index 1
        assertEquals(List.of(1, 2, 3), list.toArrayList());

        // insert at index 0 should behave like addFirst
        list.add(0, 0);
        assertEquals(List.of(0, 1, 2, 3), list.toArrayList());
    }

    @Test
    void add_atIndex_equalSize_appends() {
        var list = MutableList.of(1, 2);
        int size = list.size();
        list.add(size, 3); // should append
        assertEquals(List.of(1, 2, 3), list.toArrayList());

        // also works when list was empty: add(0, x)
        var empty = MutableList.of();
        empty.add(empty.size(), 7);
        assertEquals(List.of(7), empty.toArrayList());
    }

    @Test
    void add_atIndex_tooLarge_or_negative_throws() {
        var list = MutableList.of(1, 2);
        // index > size
        assertThrows(IndexOutOfBoundsException.class, () -> list.add(list.size() + 1, 9));
        // negative index
        assertThrows(IndexOutOfBoundsException.class, () -> list.add(-1, 9));
        // empty list: add(1, x) invalid
        var empty = MutableList.of();
        assertThrows(IndexOutOfBoundsException.class, () -> empty.add(1, 5));
    }

    @Test
    void removeFirst_throwsOnEmpty_and_returnsFirst() {
        var empty = MutableList.of();
        assertTrue(empty.isEmpty());
        assertThrows(NoSuchElementException.class, empty::removeFirst);

        var list = MutableList.of(10, 20);
        assertFalse(list.isEmpty());
        Integer removed = list.removeFirst();
        assertEquals(10, removed);
        assertEquals(List.of(20), list.toArrayList());
        assertFalse(list.isEmpty());
    }

    @Test
    void removeLast_singleAndMultipleElements() {
        var single = MutableList.of(7);
        assertFalse(single.isEmpty());
        Integer r1 = single.removeLast();
        assertEquals(7, r1);
        assertTrue(single.isEmpty());

        var list = MutableList.of(1, 2, 3);
        Integer r2 = list.removeLast();
        assertEquals(3, r2);
        assertEquals(List.of(1, 2), list.toArrayList());
        assertFalse(list.isEmpty());
    }

    @Test
    void remove_atIndex_returnsAndRemoves_orThrowsOnInvalidIndex() {
        var list = MutableList.of(1, 2, 3);
        Integer removed = list.remove(1);
        assertEquals(2, removed);
        assertEquals(List.of(1, 3), list.toArrayList());

        // removing out of bounds should throw
        assertThrows(IndexOutOfBoundsException.class, () -> list.remove(5));
    }

    @Test
    void remove_negativeIndex_throws() {
        var list = MutableList.of(1, 2);
        assertThrows(IndexOutOfBoundsException.class, () -> list.remove(-1));
        var empty = MutableList.of();
        assertThrows(IndexOutOfBoundsException.class, () -> empty.remove(-1));
    }
}


class MutableListComprehensiveTest {

    @Test
    void simple_operations() {
        var xs = MutableList.of();
        assertTrue(xs.isEmpty());
        assertEquals(0, xs.size());

        xs.addFirst(1);
        assertFalse(xs.isEmpty());
        assertEquals(1, xs.size());
        assertEquals(Arrays.asList(1), xs.toArrayList());

        xs.add(2);
        xs.add(3);
        assertEquals(Arrays.asList(1, 2, 3), xs.toArrayList());
    }

    @Test
    void add_at_boundaries_index_zero_and_size() {
        var list = MutableList.of();
        list.add(0, 10); // add at 0 on empty
        assertEquals(Arrays.asList(10), list.toArrayList());

        list.add(list.size(), 20); // append by index == size
        assertEquals(Arrays.asList(10, 20), list.toArrayList());

        list.add(1, 15); // insert middle
        assertEquals(Arrays.asList(10, 15, 20), list.toArrayList());
    }

    @Test
    void add_invalid_indexes_throw() {
        var list = MutableList.of(1, 2, 3);
        assertThrows(IndexOutOfBoundsException.class, () -> list.add(-1, 5));
        assertThrows(IndexOutOfBoundsException.class, () -> list.add(list.size() + 1, 5));
        var empty = MutableList.of();
        assertThrows(IndexOutOfBoundsException.class, () -> empty.add(1, 5));
    }

    @Test
    void remove_first_last_and_by_index_with_exceptions() {
        var list = MutableList.of(1, 2, 3);
        assertEquals(1, list.removeFirst());
        assertEquals(Arrays.asList(2, 3), list.toArrayList());

        assertEquals(3, list.removeLast());
        assertEquals(Arrays.asList(2), list.toArrayList());

        // remove last remaining by index 0
        assertEquals(2, list.remove(0));
        assertTrue(list.isEmpty());

        // on empty - removes throw
        assertThrows(NoSuchElementException.class, list::removeFirst);
        assertThrows(NoSuchElementException.class, list::removeLast);

        var xs = MutableList.of(5, 6);
        assertThrows(IndexOutOfBoundsException.class, () -> xs.remove(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> xs.remove(xs.size())); // out of bounds
    }

    @Test
    void replace_valid_and_invalid_indexes() {
        var list = MutableList.of(5, 6, 7);
        Integer old = list.replace(1, 60);
        assertEquals(6, old);
        assertEquals(Arrays.asList(5, 60, 7), list.toArrayList());

        Integer oldFirst = list.replace(0, 50);
        assertEquals(5, oldFirst);
        assertEquals(Arrays.asList(50, 60, 7), list.toArrayList());

        assertThrows(IndexOutOfBoundsException.class, () -> list.replace(-1, 0));
        assertThrows(IndexOutOfBoundsException.class, () -> list.replace(list.size(), 0));
    }

    @Test
    void null_handling_for_add_and_replace() {
        MutableList<Integer> list = MutableList.<Integer>of((Integer) null);
        assertEquals(1, list.size());
        assertNull(list.toArrayList().get(0));

        Integer old = list.replace(0, 99);
        assertNull(old);
        assertEquals(Arrays.asList(99), list.toArrayList());

        // adding null to non-empty
        list.add(null);
        assertEquals(2, list.size());
        assertNull(list.toArrayList().get(1));
    }



    @Test
    void getNode_bounds_and_clear_reuse() {
        var list = MutableList.of(9, 8, 7);
        var n0 = list.getNode(0);
        assertEquals(9, n0.content());
        var n2 = list.getNode(2);
        assertEquals(7, n2.content());

        assertThrows(IndexOutOfBoundsException.class, () -> list.getNode(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> list.getNode(list.size()));

        // clear and reuse
        list.clear();
        assertTrue(list.isEmpty());
        list.add(42);
        assertEquals(Arrays.asList(42), list.toArrayList());
    }
}