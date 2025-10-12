package org.example;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SimpleMapTest {


    @Test
    void entryList() {
        List<SimpleEntry> footBallList = List.of(new SimpleEntry("FCB", 18), new SimpleEntry("BVB", 14), new SimpleEntry("RBL", 13));
        SimpleMap footBallMap = new  SimpleMap(footBallList);
        assertEquals(footBallList, footBallMap.entryList());
    }

    @Test
    void clear() {
        List<SimpleEntry> footBallList = List.of(new SimpleEntry("FCB", 18), new SimpleEntry("BVB", 14), new SimpleEntry("RBL", 13));
        SimpleMap footBallMap = new  SimpleMap(footBallList);
        footBallMap.clear();
        assertEquals(List.of(), footBallMap.entryList());
    }



    @Test
    void size() {
        List<SimpleEntry> footBallList = List.of(new SimpleEntry("FCB", 18), new SimpleEntry("BVB", 14), new SimpleEntry("RBL", 13));
        SimpleMap footBallMap = new  SimpleMap(footBallList);
        assertEquals(3, footBallMap.size());
    }

    @Test
    void isEmpty() {
        List<SimpleEntry> footBallList = List.of(new SimpleEntry("FCB", 18), new SimpleEntry("BVB", 14), new SimpleEntry("RBL", 13));
        SimpleMap footBallMap = new  SimpleMap(footBallList);
        assertFalse(footBallMap.isEmpty());
    }

    @Test
    void keySet() {
        List<SimpleEntry> footBallList = List.of(new SimpleEntry("FCB", 18), new SimpleEntry("BVB", 14), new SimpleEntry("RBL", 13));
        SimpleMap footBallMap = new  SimpleMap(footBallList);
        assertEquals(List.of("FCB", "BVB", "RBL"), footBallMap.keySet());
    }

    @Test
    void values() {
        List<SimpleEntry> footBallList = List.of(new SimpleEntry("FCB", 18), new SimpleEntry("BVB", 14), new SimpleEntry("RBL", 13));
        SimpleMap footBallMap = new  SimpleMap(footBallList);
        assertEquals(List.of(18, 14, 13), footBallMap.values());
    }

    @Test
    void get() {
        List<SimpleEntry> footBallList = List.of(new SimpleEntry("FCB", 18), new SimpleEntry("BVB", 14), new SimpleEntry("RBL", 13));
        SimpleMap footBallMap = new  SimpleMap(footBallList);
        assertEquals(18, footBallMap.get("FCB"));
        assertEquals(14, footBallMap.get("BVB"));
        assertEquals(13, footBallMap.get("RBL"));
    }

    @Test
    void containsKey() {
        List<SimpleEntry> footBallList = List.of(new SimpleEntry("FCB", 18), new SimpleEntry("BVB", 14), new SimpleEntry("RBL", 13));
        SimpleMap footBallMap = new  SimpleMap(footBallList);
        assertTrue(footBallMap.containsKey("FCB"));
        assertTrue(footBallMap.containsKey("BVB"));
        assertTrue(footBallMap.containsKey("RBL"));
        assertFalse(footBallMap.containsKey("KSC"));
    }

    @Test
    void containsValue() {
        List<SimpleEntry> footBallList = List.of(new SimpleEntry("FCB", 18), new SimpleEntry("BVB", 14), new SimpleEntry("RBL", 13));
        SimpleMap footBallMap = new  SimpleMap(footBallList);
        assertTrue(footBallMap.containsValue(18));
        assertTrue(footBallMap.containsValue(14));
        assertTrue(footBallMap.containsValue(13));
        assertFalse(footBallMap.containsValue(2));
    }

    @Test
    void put() {
        List<SimpleEntry> footBallList = List.of(new SimpleEntry("FCB", 18), new SimpleEntry("BVB", 14), new SimpleEntry("RBL", 13));
        SimpleMap footBallMap = new  SimpleMap(footBallList);
        footBallMap.put("FCB", 20);
        assertEquals(List.of(new SimpleEntry("FCB", 20), new SimpleEntry("BVB", 14), new SimpleEntry("RBL", 13)),footBallMap.entryList());
    }



    @Test
    void remove() {
        List<SimpleEntry> footBallList = List.of(new SimpleEntry("FCB", 18), new SimpleEntry("BVB", 14), new SimpleEntry("RBL", 13));
        SimpleMap footBallMap = new  SimpleMap(footBallList);
        footBallMap.remove("FCB");
        assertEquals(List.of(new SimpleEntry("BVB", 14), new SimpleEntry("RBL", 13)),footBallMap.entryList());
    }

    @Test
    void putAll() {
        List<SimpleEntry> footBallList1 = List.of(new SimpleEntry("FCB", 18));
        SimpleMap footBallMap1 = new  SimpleMap(footBallList1);
        List<SimpleEntry> footBallList2 = List.of(new SimpleEntry("BVB", 14), new SimpleEntry("RBL", 13));
        SimpleMap footBallMap2 = new  SimpleMap(footBallList2);
        footBallMap1.putAll(footBallMap2);
        assertEquals(List.of(new SimpleEntry("FCB", 18), new SimpleEntry("BVB", 14), new SimpleEntry("RBL", 13)), footBallMap1.entryList());
    }

    @Test
    void invertedEntries() {
        List<SimpleEntry> footBallList = List.of(new SimpleEntry("Freiburg", 8), new SimpleEntry("HSV", 8), new SimpleEntry("St. Pauli", 7));
        SimpleMap footBallMap = new  SimpleMap(footBallList);
        assertEquals(List.of(new InvertedEntry(8, List.of("Freiburg", "HSV")), new InvertedEntry(7, List.of("St. Pauli"))), footBallMap.invertedEntries());
    }
}