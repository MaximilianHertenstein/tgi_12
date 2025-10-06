package org.example;

import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

class SnakeTest {

    @Test
    void testConstructorAndGetCoordinates() {
        Snake s1 = new Snake(new V2(6, 5), List.of(new V2(5, 5), new V2(4, 5), new V2(4, 6)), true);
        assertEquals(List.of(new V2(6, 5), new V2(5, 5), new V2(4, 5), new V2(4, 6)), s1.getCoordinates());

        Snake s2 = new Snake(new V2(1, 0), new ArrayList<>(), false);
        assertEquals(List.of(new V2(1, 0)), s2.getCoordinates());
    }

    @Test
    void testTailBitten() {
        Snake s1 = new Snake(new V2(6, 5), List.of(new V2(5, 5), new V2(4, 5), new V2(4, 6)), true);
        assertFalse(s1.tailBitten());

        Snake s2 = new Snake(new V2(2, 3),
                List.of(new V2(3, 3), new V2(4, 3), new V2(4,2), new V2(4, 1),
                        new V2(3, 1), new V2(2, 1), new V2(2, 2), new V2(2, 3)),
                false);
        assertTrue(s2.tailBitten());
    }

    @Test
    void testMoveDigestingTrue() {
        Snake s = new Snake(new V2(6, 5), List.of(new V2(5, 5), new V2(4, 5)), true);
        Snake moved = s.move(new V2(1, 0), new V2(7, 9));
        assertEquals(List.of(new V2(7, 5), new V2(6, 5), new V2(5, 5), new V2(4, 5)), moved.getCoordinates());
        assertFalse(moved.tailBitten());
        assertFalse(moved.digesting()); // Apfel nicht getroffen

        Snake s2 = new Snake(new V2(4, 5), List.of(new V2(4, 4)), false);
        Snake moved2 = s2.move(new V2(0, 1), new V2(4, 6));
        assertEquals(List.of(new V2(4, 6), new V2(4, 5)), moved2.getCoordinates());
        assertTrue(moved2.digesting()); // Apfel getroffen
    }

    @Test
    void testMoveDigestingFalse() {
        Snake s = new Snake(new V2(6, 5), List.of(new V2(5, 5), new V2(4, 5)), false);
        Snake moved = s.move(new V2(1, 0), new V2(7, 9));
        assertEquals(List.of(new V2(7, 5), new V2(6, 5), new V2(5, 5)), moved.getCoordinates());
        assertFalse(moved.digesting());

        Snake s2 = new Snake(new V2(4, 5), List.of(new V2(4, 4)), true);
        Snake moved2 = s2.move(new V2(0, 1), new V2(4, 6));
        assertEquals(List.of(new V2(4, 6), new V2(4, 5), new V2(4, 4)), moved2.getCoordinates());
        assertTrue(moved2.digesting());
    }

    @Test
    void testSecondConstructor() {
        Snake s = new Snake(new V2(3, 2));
        assertEquals(List.of(new V2(3, 2)), s.getCoordinates());
        assertFalse(s.tailBitten());
        assertFalse(s.digesting());
    }
}
