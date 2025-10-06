package org.example;

import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

class UtilsTest {

    @Test
    void testKeyToV2() {
        assertEquals(new V2(0, -1), Utils.keyToV2('w'));
        assertEquals(new V2(-1, 0), Utils.keyToV2('a'));
        assertEquals(new V2(0, 1), Utils.keyToV2('s'));
        assertEquals(new V2(1, 0), Utils.keyToV2('d'));
        assertEquals(new V2(0, 0), Utils.keyToV2('i'));
    }

    @Test
    void testComputeNewDirection() {
        assertEquals(new V2(0, -1), Utils.computeNewDirection(new V2(1, 0), 'w'));
        assertEquals(new V2(0, 1), Utils.computeNewDirection(new V2(0, 1), 's'));
        assertEquals(new V2(0, 1), Utils.computeNewDirection(new V2(0, 1), 't')); // keine Änderung
    }

    @Test
    void testDropLast() {
        assertEquals(List.of('i'), Utils.dropLast(List.of('i', 'a')));
        assertEquals(List.of(), Utils.dropLast(new ArrayList<>()));
        assertEquals(List.of(new V2(5, 5), new V2(4, 5)), Utils.dropLast(List.of(new V2(5, 5), new V2(4, 5), new V2(4, 6))));
    }

    @Test
    void testIsOnBoard() {
        assertTrue(Utils.isOnBoard(new V2(3, 4), 4, 5));
        assertFalse(Utils.isOnBoard(new V2(6, 5), 6, 7));
        assertFalse(Utils.isOnBoard(new V2(6, 5), 7, 5));
        assertTrue(Utils.isOnBoard(new V2(3, 2), 7, 5));
        assertFalse(Utils.isOnBoard(new V2(3, -2), 7, 5));
    }


}
