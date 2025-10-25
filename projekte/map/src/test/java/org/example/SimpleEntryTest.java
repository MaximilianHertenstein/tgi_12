// src/test/java/org/example/SimpleEntryTest.java
package org.example;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class SimpleEntryTest {

    @Test
    void constructorAndGetters() {
        SimpleEntry entry = new SimpleEntry("FCH", 3);
        assertEquals("FCH", entry.key());
        assertEquals(3, entry.value());
    }

    @Test
    void tesConstructorAndGetters() {
        SimpleEntry entry = new SimpleEntry("FCH");
        assertEquals("FCH", entry.key());
        assertEquals(0, entry.value());
    }



}
