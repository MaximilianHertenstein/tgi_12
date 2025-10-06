package org.example;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class ModelTest {

    @Test
    void testFullConstructor() {
        Model m1 = new Model(8, 9, new Snake(new V2(6, 5), List.of(), true), new V2(1, 0), new V2(3, 2));
        assertEquals(8, m1.cols);
        assertEquals(9, m1.rows);
        assertEquals(new Snake(new V2(6, 5), List.of(), true), m1.snake);
        assertEquals(new V2(1, 0), m1.direction);
        assertEquals(new V2(3, 2), m1.applePosition);
    }

    @Test
    void testInitConstructor() {
        Model m1 = new Model(10, 5);
        assertEquals(10, m1.cols);
        assertEquals(5, m1.rows);
        assertEquals(new Snake(new V2(5, 2)), m1.snake);
        assertEquals(new V2(1, 0), m1.direction);
        assertEquals(new V2(9, 4), m1.applePosition);

        Model m2 = new Model(30, 20);
        assertEquals(30, m2.cols);
        assertEquals(20, m2.rows);
        assertEquals(new Snake(new V2(15, 10)), m2.snake);
        assertEquals(new V2(1, 0), m2.direction);
        assertEquals(new V2(29, 19), m2.applePosition);
    }

    @Test
    void testSnakeIsAlive() {
        assertTrue(new Model(8, 9, new Snake(new V2(6, 5), List.of(), true), new V2(1, 0), new V2(3, 2)).snakeIsAlive());
        assertFalse(new Model(8, 9, new Snake(new V2(-1, 5), List.of(), true), new V2(1, 0), new V2(3, 2)).snakeIsAlive());
        assertFalse(new Model(5, 5, new Snake(new V2(2, 3),
                List.of(new V2(3, 3), new V2(4, 3), new V2(4,2), new V2(4, 1),
                        new V2(3, 1), new V2(2, 1), new V2(2, 2), new V2(2, 3)),
                false), new V2(1, 0), new V2(0, 1)).snakeIsAlive());
    }

    @Test
    void testBoardIsFull() {
        assertTrue(new Model(2, 2, new Snake(new V2(0, 0), List.of(new V2(0, 1), new V2(1, 1), new V2(1, 0)), true), new V2(1, 0), new V2(0, 0)).boardIsFull());
        assertFalse(new Model(2, 2, new Snake(new V2(0, 0), List.of(new V2(0, 1), new V2(1, 1)), true), new V2(1, 0), new V2(0, 0)).boardIsFull());
    }

    @Test
    void testGameOngoing() {
        assertFalse(new Model(2, 2, new Snake(new V2(0, 0), List.of(new V2(0, 1), new V2(1, 1), new V2(1, 0)), true), new V2(1, 0), new V2(0, 0)).gameOngoing());
        assertTrue(new Model(2, 2, new Snake(new V2(0, 0), List.of(new V2(0, 1), new V2(1, 1)), true), new V2(1, 0), new V2(0, 0)).gameOngoing());
        assertFalse(new Model(2, 2, new Snake(new V2(-1, 0), List.of(new V2(0, 0)), true), new V2(-1, 0), new V2(0, 0)).gameOngoing());
    }

    @Test
    void testGetEndMessage() {
        assertEquals("won", new Model(2, 2, new Snake(new V2(0, 0), List.of(new V2(0, 1), new V2(1, 1), new V2(1, 0)), true), new V2(1, 0), new V2(0, 0)).getEndMessage());
        assertEquals("lost", new Model(2, 2, new Snake(new V2(-1, 0), List.of(new V2(0, 0)), true), new V2(-1, 0), new V2(0, 0)).getEndMessage());
    }

    @Test
    void testGetUIState() {
        UIState ui1 = new Model(8, 9, new Snake(new V2(6, 5), List.of(), true), new V2(1, 0), new V2(3, 2)).getUIState();
        assertEquals(new V2(6, 5), ui1.snakeHead());
        assertEquals(List.of(), ui1.snakeTail());
        assertEquals(new V2(3, 2), ui1.applePosition());

        UIState ui2 = new Model(8, 9, new Snake(new V2(6, 5), List.of(new V2(5, 5), new V2(5, 4)), false), new V2(1, 0), new V2(0, 1)).getUIState();
        assertEquals(new V2(6, 5), ui2.snakeHead());
        assertEquals(List.of(new V2(5, 5), new V2(5, 4)), ui2.snakeTail());
        assertEquals(new V2(0, 1), ui2.applePosition());
    }

    @Test
    void testMoveSnake() {
        var m1 = new Model(8, 9, new Snake(new V2(6, 5), List.of(new V2(5, 5)), false), new V2(1, 0), new V2(3, 2));
        m1.moveSnake();
        assertEquals(new Snake(new V2(7, 5), List.of(new V2(6, 5)), false), m1.snake);

        var m2 = new Model(8, 9, new Snake(new V2(4, 5), List.of(new V2(5, 5)), true), new V2(-1, 0), new V2(3, 5));
        m2.moveSnake();
        assertEquals(new Snake(new V2(3, 5), List.of(new V2(4, 5), new V2(5, 5)), true), m2.snake);

        var m3 = new Model(2, 2, new Snake(new V2(0, 0), List.of(new V2(0, 1), new V2(1, 1)), true), new V2(1, 0), new V2(1, 1));
        m3.moveSnake();
        assertEquals(new V2(1, 1), m3.applePosition); // Apfel bleibt gleich, da das Board voll ist
    }

    @Test
    void testSetDirection() {
        var m = new Model(8, 9, new Snake(new V2(6, 5), List.of(new V2(5, 5)), false), new V2(1, 0), new V2(3, 2));
        m.setDirection('w');
        assertEquals(new V2(0, -1), m.direction);

        m.setDirection('a');
        assertEquals(new V2(-1, 0), m.direction);
    }
}
