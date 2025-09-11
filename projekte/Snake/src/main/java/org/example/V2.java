package org.example;

public record V2(int x, int y) {

    public V2 plus(V2 other) {
        return new V2(x + other.x(), y + other.y());
    }

    public int times(V2 other) {
        return x * other.x() + y * other.y();
    }

}