package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Utils {
    public static V2 keyToV2(char pressedKey) {
        return switch (pressedKey) {
            case 'w' -> new V2(0, -1);
            case 'd' -> new V2(1, 0);
            case 's' -> new V2(0, 1);
            case 'a' -> new V2(-1, 0);
            default -> new V2(0, 0);
        };
    }


    public static V2 computeNewDirection(V2 currentDirection, char pressedKey) {
        var maybeNewDir = keyToV2(pressedKey);
        if (!maybeNewDir.equals(new V2(0, 0)) && maybeNewDir.times(currentDirection) == 0) return maybeNewDir;
        else return currentDirection;
    }


    public static <T> List<T> dropLast(List<T> xs) {
        var acc = new ArrayList<T>();
        for (int i = 0; i < xs.size() - 1; i++) {
            acc.add(xs.get(i));
        }
        return acc;
//     var acc = new ArrayList<T>(xs);
//     acc.removeLast()
    }


    public static boolean isOnBoard(V2 v, int cols, int rows) {
        return v.x() < cols && v.x() >= 0 && v.y() < rows && v.y() >= 0;
    }


    public static V2 generateRandomFreeCoordinates(List<V2> blockedCoordinates, int rows, int cols) {
        Random random = new Random();
        while (true) {
            var newAppleX = random.nextInt(0, cols);
            var newAppleY = random.nextInt(0, rows);
            var newAppleCoordinates = new V2(newAppleX, newAppleY);
            if (!blockedCoordinates.contains(newAppleCoordinates)) {
                return newAppleCoordinates;
            }
        }
    }
}
