package org.example;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AlienManagerTest {


        @Test
        void getLowestAliens_returnsAliensWithMinimumY() {
            var a1 = new Alien(new V2(0, 1));
            var a2 = new Alien(new V2(1, 1));
            var a3 = new Alien(new V2(2, 3));
            var a4 = new Alien(new V2(3, 7));

            var manager = new AlienManager(List.of(a1, a2, a3, a4));

            var lowest = manager.getLowestAliens();

            assertEquals(2, lowest.size());
            assertTrue(lowest.contains(a1));
            assertTrue(lowest.contains(a2));
        }

        @Test
        void removeDeadAliens_removesCollidedOnes() {
            var a1 = new Alien(new V2(0, 0));
            var a2 = new Alien(new V2(1, 1));

            var manager = new AlienManager(new java.util.ArrayList<>(List.of(a1, a2)));

            var rockets = List.of(new Rocket(new V2(1, 1)));

            manager.removeDeadAliens(rockets);

            var remaining = manager.getAliens();
            assertEquals(1, remaining.size());
            assertTrue(remaining.contains(a1));
            assertFalse(remaining.contains(a2));
        }

    @Test
    void addAlien_addsAlienToList() {
        var manager = new AlienManager();
        var a = new Alien(new V2(2, 2));
        manager.addAlien(a);
        assertEquals(1, manager.getAliens().size());
        assertTrue(manager.getAliens().contains(a));
    }
}