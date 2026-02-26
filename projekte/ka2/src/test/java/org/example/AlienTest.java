package org.example;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AlienTest {

    @Test
    void collidesWithRocket_trueWhenSamePosition() {
        var alien = new Alien(new V2(1, 2));
        var rocket = new Rocket(new V2(1, 2));
        assertTrue(alien.collidesWith(rocket));
    }

    @Test
    void collidesWithRocket_falseWhenDifferentPosition() {
        var alien = new Alien(new V2(1, 2));
        var rocket = new Rocket(new V2(0, 0));
        assertFalse(alien.collidesWith(rocket));
    }

    @Test
    void collidesWithList_detectsAnyCollision() {
        var alien = new Alien(new V2(5, 5));
        var rockets = List.of(new Rocket(new V2(0, 0)), new Rocket(new V2(5, 5)));
        assertTrue(alien.collidesWith(rockets));
    }

    @Test
    void collidesWithList_noCollision() {
        var alien = new Alien(new V2(5, 5));
        var rockets = List.of(new Rocket(new V2(0, 0)), new Rocket(new V2(1, 1)));
        assertFalse(alien.collidesWith(rockets));
    }
}