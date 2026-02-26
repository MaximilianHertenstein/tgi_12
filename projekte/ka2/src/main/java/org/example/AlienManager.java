package org.example;

import java.util.ArrayList;
import java.util.List;

public class AlienManager {

    private List<Alien> aliens;

    public AlienManager(List<Alien> aliens) {
        this.aliens = aliens;
    }

    public AlienManager() {
        this.aliens = new ArrayList<>();
    }

    public List<Alien>  getAliens() {
        return aliens;
    }

    public void addAlien(Alien alien) {
        aliens.add(alien);
    }

    List<Alien> getLowestAliens() {
        if (aliens.isEmpty()) return List.of();
        int lowestLine = aliens.getFirst().pos().y();
        var acc = new ArrayList<Alien>();
        for (var alien : aliens) {
            if (alien.pos().y() == lowestLine) {
                acc.add(alien);
            }
        }
        return acc;
    }

    public void removeDeadAliens(List<Rocket>  rockets) {
        aliens = Utils.onlyLivingAliens(aliens, rockets);
    }


}
