package org.example;// UtilsTest.java
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class UtilsTest {
@Test
void dedupWithIntegers_examples() {
    assertEquals(List.of(1, 2, 3, 4), Utils.dedup(List.of(1, 2, 2, 3, 1, 4)));
    assertEquals(List.of(5, 6), Utils.dedup(List.of(5, 5, 6, 6, 5)));
}

@Test
void dedupWithStrings_examples() {
    assertEquals(List.of("A", "B", "C"), Utils.dedup(List.of("A", "B", "A", "C", "B")));
    assertEquals(List.of("X", "Y"), Utils.dedup(List.of("X", "Y", "X", "Y", "X")));
}

@Test
void dedupWithEmptyList_examples() {
    assertEquals(List.of(), Utils.dedup(List.of()));
    assertEquals(List.of(), Utils.dedup(List.of()));
}

@Test
void keySet_examples() {
    assertEquals(List.of("FCH", "BMG", "M05"), Utils.keySet(List.of(
            new SimpleEntry("FCH", 3),
            new SimpleEntry("BMG", 3),
            new SimpleEntry("M05", 4)
    )));
    assertEquals(List.of("A", "B"), Utils.keySet(List.of(
            new SimpleEntry("A", 1),
            new SimpleEntry("B", 2),
            new SimpleEntry("A", 3)
    )));
}

@Test
void values_examples() {
    assertEquals(List.of(3, 3, 4), Utils.values(List.of(
            new SimpleEntry("FCH", 3),
            new SimpleEntry("BMG", 3),
            new SimpleEntry("M05", 4)
    )));
    assertEquals(List.of(1, 2, 1), Utils.values(List.of(
            new SimpleEntry("A", 1),
            new SimpleEntry("B", 2),
            new SimpleEntry("A", 1)
    )));
}

@Test
void remove_examples() {
    assertEquals(List.of(new SimpleEntry("FCH", 3), new SimpleEntry("M05", 4)),
            Utils.remove(List.of(
                    new SimpleEntry("FCH", 3),
                    new SimpleEntry("BMG", 3),
                    new SimpleEntry("M05", 4)
            ), "BMG"));
    assertEquals(List.of(new SimpleEntry("B", 2)),
            Utils.remove(List.of(
                    new SimpleEntry("A", 1),
                    new SimpleEntry("B", 2)
            ), "A"));
}

@Test
void putNew_examples() {
    assertEquals(List.of(
            new SimpleEntry("FCH", 3),
            new SimpleEntry("BMG", 3),
            new SimpleEntry("M05", 4),
            new SimpleEntry("WOB", 5)
    ), Utils.put(List.of(
            new SimpleEntry("FCH", 3),
            new SimpleEntry("BMG", 3),
            new SimpleEntry("M05", 4)
    ), "WOB", 5));
    assertEquals(List.of(
            new SimpleEntry("A", 1),
            new SimpleEntry("B", 2),
            new SimpleEntry("C", 3)
    ), Utils.put(List.of(
            new SimpleEntry("A", 1),
            new SimpleEntry("B", 2)
    ), "C", 3));
}

@Test
void putUpdate_examples() {
    assertEquals(List.of(
            new SimpleEntry("FCH", 3),
            new SimpleEntry("BMG", 5),
            new SimpleEntry("M05", 4)
    ), Utils.put(List.of(
            new SimpleEntry("FCH", 3),
            new SimpleEntry("BMG", 3),
            new SimpleEntry("M05", 4)
    ), "BMG", 5));
    assertEquals(List.of(
            new SimpleEntry("A", 99),
            new SimpleEntry("B", 2)
    ), Utils.put(List.of(
            new SimpleEntry("A", 1),
            new SimpleEntry("B", 2)
    ), "A", 99));
}

@Test
void invertedEntries_examples() {
    assertEquals(List.of(
            new InvertedEntry(3, List.of("FCH", "BMG")),
            new InvertedEntry(4, List.of("M05"))
    ), Utils.invertedEntries(List.of(
            new SimpleEntry("FCH", 3),
            new SimpleEntry("BMG", 3),
            new SimpleEntry("M05", 4)
    )));
    assertEquals(List.of(
            new InvertedEntry(1, List.of("A", "C")),
            new InvertedEntry(2, List.of("B"))
    ), Utils.invertedEntries(List.of(
            new SimpleEntry("A", 1),
            new SimpleEntry("B", 2),
            new SimpleEntry("C", 1)
    )));
}
}