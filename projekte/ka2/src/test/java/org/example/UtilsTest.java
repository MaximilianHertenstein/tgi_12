package org.example;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UtilsTest {
    @Test
    void onlyLivingAliens_filtersCollidedAliens() {
        var a1 = new Alien(new V2(0, 0));
        var a2 = new Alien(new V2(1, 1));
        var a3 = new Alien(new V2(2, 2));

        var aliens = new ArrayList<Alien>();
        aliens.add(a1);
        aliens.add(a2);
        aliens.add(a3);

        var rockets = List.of(new Rocket(new V2(1, 1)));

        var result = Utils.onlyLivingAliens(aliens, rockets);

        assertEquals(2, result.size());
        assertTrue(result.contains(a1));
        assertTrue(result.contains(a3));
        assertFalse(result.contains(a2));
    }
}