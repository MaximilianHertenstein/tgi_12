package org.example;

import java.util.List;

public record UIState(V2 snakeHead, List<V2> snakeTail, V2 applePosition) {
}