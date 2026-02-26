// src/test/java/org/example/HistogramTest.java
package org.example;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class HistogramTest {

    @Test
    void constructorInitializesEmptyMap() {
        Histogram histogram = new Histogram();
        assertTrue(histogram.getEntries().isEmpty());
    }

    @Test
    void countAddsAndIncrementsEntries() {
        Histogram histogram = new Histogram();
        histogram.count("x");
        histogram.count("y");
        histogram.count("x");
        assertEquals(2, histogram.map.get("x"));
        assertEquals(1, histogram.map.get("y"));
    }

//    @Test
//    void fromStringListCreatesCorrectHistogram() {
//        List<String> list = List.of("a", "b", "a", "c", "b", "a");
//        Histogram histogram = Histogram.fromStringList(list);
//        assertEquals(3, histogram.map.get("a"));
//        assertEquals(2, histogram.map.get("b"));
//        assertEquals(1, histogram.map.get("c"));
//    }


}
