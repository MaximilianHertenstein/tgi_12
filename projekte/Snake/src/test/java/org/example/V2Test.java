package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class V2Test {



    @Test
    void testConstructor() {
        V2 v1 = new V2(3, 2);
        assertEquals(3, v1.x());
        assertEquals(2, v1.y());

        V2 v2 = new V2(1, -5);
        assertEquals(1, v2.x());
        assertEquals(-5, v2.y());
    }

    @Test
    void testPlus() {
        V2 result1 = new V2(3, 2).plus(new V2(1, -5));
        assertEquals(new V2(4, -3), result1);

        V2 result2 = new V2(3, 4).plus(new V2(-4, -5));
        assertEquals(new V2(-1, -1), result2);
    }

    @Test
    void testTimes() {
        int times1 = new V2(1, 2).times(new V2(3, 4));
        assertEquals(11, times1);

        int times2 = new V2(3, 2).times(new V2(1, -5));
        assertEquals(-7, times2);
    }
}