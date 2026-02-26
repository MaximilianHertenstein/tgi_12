package org.example;

import java.util.ArrayList;
import java.util.List;

public class Utils {
    public static ArrayList<Alien> onlyLivingAliens(List<Alien> aliens, List<Rocket> rockets) {
        var acc = new ArrayList<Alien>();
        for (var alien : aliens) {
            if (!alien.collidesWith(rockets)) acc.add(alien);
        }
        return acc;
    }
}
