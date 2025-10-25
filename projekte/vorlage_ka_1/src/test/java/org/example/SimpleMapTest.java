package org.example;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class SimpleMapTest {
//    @Test
//    void emptyConstructorCreatesEmptyMap() {
//        SimpleMap map = new SimpleMap();
//        assertTrue(map.isEmpty());
//        assertEquals(0, map.size());
//        assertEquals(List.of(), map.entryList());
//
//        SimpleMap anotherMap = new SimpleMap();
//        assertTrue(anotherMap.isEmpty());
//        assertEquals(0, anotherMap.size());
//        assertEquals(List.of(), anotherMap.entryList());
//    }
//
//    @Test
//    void listConstructorInitializesEntries() {
//        List<SimpleEntry> entries = List.of(
//                new SimpleEntry("FCH", 3),
//                new SimpleEntry("BMG", 3),
//                new SimpleEntry("M05", 4)
//        );
//        SimpleMap map = new SimpleMap(entries);
//        assertEquals(entries, map.entryList());
//        assertEquals(3, map.size());
//        assertFalse(map.isEmpty());
//
//        List<SimpleEntry> moreEntries = List.of(
//                new SimpleEntry("A", 1),
//                new SimpleEntry("B", 2)
//        );
//        SimpleMap map2 = new SimpleMap(moreEntries);
//        assertEquals(moreEntries, map2.entryList());
//        assertEquals(2, map2.size());
//        assertFalse(map2.isEmpty());
//    }
//
//    @Test
//    void entryList() {
//        List<SimpleEntry> footBallList = List.of(new SimpleEntry("FCH", 3), new SimpleEntry("BMG", 3), new SimpleEntry("M05", 4));
//        SimpleMap footBallMap = new SimpleMap(footBallList);
//        assertEquals(footBallList, footBallMap.entryList());
//
//        List<SimpleEntry> list2 = List.of(new SimpleEntry("X", 10));
//        SimpleMap map2 = new SimpleMap(list2);
//        assertEquals(list2, map2.entryList());
//    }
//
//    @Test
//    void size() {
//        List<SimpleEntry> footBallList = List.of(new SimpleEntry("FCH", 3), new SimpleEntry("BMG", 3), new SimpleEntry("M05", 4));
//        SimpleMap footBallMap = new SimpleMap(footBallList);
//        assertEquals(3, footBallMap.size());
//
//        SimpleMap emptyMap = new SimpleMap();
//        assertEquals(0, emptyMap.size());
//    }
//
//    @Test
//    void isEmpty() {
//        List<SimpleEntry> footBallList = List.of(new SimpleEntry("FCH", 3), new SimpleEntry("BMG", 3), new SimpleEntry("M05", 4));
//        SimpleMap footBallMap = new SimpleMap(footBallList);
//        assertFalse(footBallMap.isEmpty());
//
//        SimpleMap emptyFootBallMap = new SimpleMap();
//        assertTrue(emptyFootBallMap.isEmpty());
//
//        SimpleMap map2 = new SimpleMap(List.of(new SimpleEntry("A", 1)));
//        assertFalse(map2.isEmpty());
//    }
//
//    @Test
//    void clear() {
//        List<SimpleEntry> footBallList = List.of(new SimpleEntry("FCH", 3), new SimpleEntry("BMG", 3), new SimpleEntry("M05", 4));
//        SimpleMap footBallMap = new SimpleMap(footBallList);
//        footBallMap.clear();
//        assertEquals(List.of(), footBallMap.entryList());
//
//        SimpleMap map2 = new SimpleMap(List.of(new SimpleEntry("X", 10)));
//        map2.clear();
//        assertEquals(List.of(), map2.entryList());
//    }
//
//    @Test
//    void keySet() {
//        List<SimpleEntry> footBallList = List.of(new SimpleEntry("FCH", 3), new SimpleEntry("BMG", 3), new SimpleEntry("M05", 4));
//        SimpleMap footBallMap = new SimpleMap(footBallList);
//        assertEquals(List.of("FCH", "BMG", "M05"), footBallMap.keySet());
//
//        SimpleMap map2 = new SimpleMap(List.of(new SimpleEntry("X", 10), new SimpleEntry("Y", 20)));
//        assertEquals(List.of("X", "Y"), map2.keySet());
//    }
//
//    @Test
//    void values() {
//        List<SimpleEntry> footBallList = List.of(new SimpleEntry("FCH", 3), new SimpleEntry("BMG", 3), new SimpleEntry("M05", 4));
//        SimpleMap footBallMap = new SimpleMap(footBallList);
//        assertEquals(List.of(3, 3, 4), footBallMap.values());
//
//        SimpleMap map2 = new SimpleMap(List.of(new SimpleEntry("X", 10), new SimpleEntry("Y", 20)));
//        assertEquals(List.of(10, 20), map2.values());
//    }
//
//    @Test
//    void containsKey() {
//        List<SimpleEntry> footBallList = List.of(new SimpleEntry("FCH", 3), new SimpleEntry("BMG", 3), new SimpleEntry("M05", 4));
//        SimpleMap footBallMap = new SimpleMap(footBallList);
//        assertTrue(footBallMap.containsKey("M05"));
//        assertTrue(footBallMap.containsKey("BMG"));
//        assertTrue(footBallMap.containsKey("FCH"));
//        assertFalse(footBallMap.containsKey("WOB"));
//
//        SimpleMap map2 = new SimpleMap(List.of(new SimpleEntry("A", 1), new SimpleEntry("B", 2)));
//        assertTrue(map2.containsKey("A"));
//        assertFalse(map2.containsKey("C"));
//    }
//
//    @Test
//    void containsValue() {
//        List<SimpleEntry> footBallList = List.of(new SimpleEntry("FCH", 3), new SimpleEntry("BMG", 3), new SimpleEntry("M05", 4));
//        SimpleMap footBallMap = new SimpleMap(footBallList);
//        assertTrue(footBallMap.containsValue(4));
//        assertTrue(footBallMap.containsValue(3));
//        assertFalse(footBallMap.containsValue(5));
//
//        SimpleMap map2 = new SimpleMap(List.of(new SimpleEntry("X", 10)));
//        assertTrue(map2.containsValue(10));
//        assertFalse(map2.containsValue(20));
//    }
//
//    @Test
//    void keyToIndex() {
//        List<SimpleEntry> footBallList = List.of(new SimpleEntry("FCH", 3), new SimpleEntry("BMG", 3), new SimpleEntry("M05", 4));
//        SimpleMap footBallMap = new SimpleMap(footBallList);
//        assertEquals(0, footBallMap.keyToIndex("FCH"));
//        assertEquals(1, footBallMap.keyToIndex("BMG"));
//        assertEquals(2, footBallMap.keyToIndex("M05"));
//        assertEquals(-1, footBallMap.keyToIndex("WOB"));
//
//        SimpleMap map2 = new SimpleMap(List.of(new SimpleEntry("X", 10)));
//        assertEquals(0, map2.keyToIndex("X"));
//        assertEquals(-1, map2.keyToIndex("Y"));
//    }
//
//    @Test
//    void get() {
//        List<SimpleEntry> footBallList = List.of(new SimpleEntry("FCH", 3), new SimpleEntry("BMG", 3), new SimpleEntry("M05", 4));
//        SimpleMap footBallMap = new SimpleMap(footBallList);
//        assertEquals(4, footBallMap.get("M05"));
//        assertEquals(3, footBallMap.get("BMG"));
//        assertEquals(3, footBallMap.get("FCH"));
//        assertEquals(-1, footBallMap.get("WOB"));
//
//        SimpleMap map2 = new SimpleMap(List.of(new SimpleEntry("X", 10)));
//        assertEquals(10, map2.get("X"));
//        assertEquals(-1, map2.get("Y"));
//    }
//
//    @Test
//    void invertedEntries() {
//        List<SimpleEntry> footBallList = List.of(new SimpleEntry("FCH", 3), new SimpleEntry("BMG", 3), new SimpleEntry("M05", 4));
//        SimpleMap footBallMap = new SimpleMap(footBallList);
//        assertEquals(List.of(new InvertedEntry(3, List.of("FCH", "BMG")), new InvertedEntry(4, List.of("M05"))), footBallMap.invertedEntries());
//
//        SimpleMap map2 = new SimpleMap(List.of(new SimpleEntry("X", 10), new SimpleEntry("Y", 10), new SimpleEntry("Z", 20)));
//        assertEquals(List.of(new InvertedEntry(10, List.of("X", "Y")), new InvertedEntry(20, List.of("Z"))), map2.invertedEntries());
//    }
}