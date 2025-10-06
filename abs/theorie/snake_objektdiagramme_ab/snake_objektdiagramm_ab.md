---
title: Snake
codebraid:
  jupyter: true
---

```{.java .cb-run}
//import java.util.Random;


record V2(int x, int y) {

    V2 plus(V2 other) {
        return new V2(x + other.x(), y + other.y());
    }

    int times(V2 other) {
        return x * other.x() + y * other.y();
    }

}


V2 keyToV2(char pressedKey) {
    return switch (pressedKey) {
        case 'w' -> new V2(0, -1);
        case 'd' -> new V2(1, 0);
        case 's' -> new V2(0, 1);
        case 'a' -> new V2(-1, 0);
        default -> new V2(0, 0);
    };
}


V2 computeNewDirection(V2 currentDirection, char pressedKey) {
    var maybeNewDir = keyToV2(pressedKey);
    if (!maybeNewDir.equals(new V2(0, 0)) && maybeNewDir.times(currentDirection) == 0) return maybeNewDir;
    else return currentDirection;
}


static <T> List<T> dropLast(List<T> xs) {
    var acc = new ArrayList<T>();
    for (int i = 0; i < xs.size() - 1; i++) {
        acc.add(xs.get(i));
    }
    return acc;
//     var acc = new ArrayList<T>(xs);
//     acc.removeLast()
}


boolean isOnBoard(V2 v, int cols, int rows) {
    return v.x() < cols && v.x() >= 0 && v.y() < rows && v.y() >= 0;
}


V2 generateRandomFreeCoordinates(List<V2> blockedCoordinates, int rows, int cols) {
    while (true) {
        Random random = new Random();
        var newAppleX = random.nextInt(0, cols);
        var newAppleY = random.nextInt(0, rows);
        var newAppleCoordinates = new V2(newAppleX, newAppleY);
        if (!blockedCoordinates.contains(newAppleCoordinates)) {
            return newAppleCoordinates;
        }
    }
}

record Snake(
        V2 head,
        List<V2> tail,
        boolean digesting
) {

    Snake(V2 head) {
        this(head, List.of(), false);
    }

    List<V2> getCoordinates() {
        var res = new ArrayList<V2>();
        res.add(head);
        for (var v2 : tail) {
            res.add(v2);
        }
        return res;
    }

    boolean tailBitten() {
        return tail.contains(head);
    }

    Snake move(V2 direction, V2 applePosition) {
        var newSnakeHead = direction.plus(head);
        var newDigesting = newSnakeHead.equals(applePosition);
        var newSnakeBody = getCoordinates();
        if (!digesting) {
            newSnakeBody = dropLast(newSnakeBody);
        }

        return new Snake(newSnakeHead, newSnakeBody, newDigesting);
    }


}

record UIState(V2 snakeHead, List<V2> snakeTail, V2 applePosition) {
}

class Model {

     final int cols;
     final int rows;
     Snake snake;
    V2 direction;
     V2 applePosition;

    Model(int cols, int rows, Snake snake, V2 direction, V2 applePosition) {
        this.cols = cols;
        this.rows = rows;
        this.snake = snake;
        this.direction = direction;
        this.applePosition = applePosition;
    }
    
    
    Model(int cols, int rows) {
        this(cols,rows,new Snake(new V2(cols / 2, rows / 2)), new V2(1, 0), new V2(cols - 1, rows - 1));
    }

    boolean snakeIsAlive() {
        return isOnBoard(snake.head(), cols, rows) && !snake.tailBitten();
    }

    boolean boardIsFull() {
        return snake.getCoordinates().size() == rows * cols;
    }

    boolean gameOngoing() {
        return snakeIsAlive() && !boardIsFull();
    }

    UIState getUIState() {
        return new UIState(snake.head(), snake.tail(), applePosition);
    }

    void moveSnake() {
        snake = snake.move(direction, applePosition);
        if (snake.digesting() && !boardIsFull()) {
            applePosition = generateRandomFreeCoordinates(snake.getCoordinates(), rows, cols);
        }
    }

    void setDirection(char key) {
        direction = computeNewDirection(direction, key);
    }

    String getEndMessage() {
        if (boardIsFull()) return "won";
        else return "lost";
    }

}





```
<!-- 
# Aufgabe 

Erstelle ein Klassendiagramm, in dem die Klassen `V2`, `Snake`, `Model` und `UIState` dargestellt sind. -->


# Aufgabe 

Erstelle ein Objektdiagramm für das Objekt `snake`.


```{.java .cb-nb first_number=1}
var snake = new Snake(new V2(6, 5), List.of(new V2(5, 5), new V2(4, 5), new V2(4, 6)), true);
```


# Aufgabe 

Die Schlange wird folgendermaßen bewegt.

\footnotesize
```{.java .cb-nb first_number=1}
var newSnake = snake.move(new V2(1, 0), new V2(7, 9));
newSnake;
```
\normalsize


Erstelle ein Objektdiagramm für das Objekt `newSnake`.


# Aufgabe 

Erstelle ein Objektdiagramm für das Objekt `model`!


```{.java .cb-nb first_number=1}
var model = new Model(8, 9, snake, new V2(1, 0), new V2(3, 2));
```


# Aufgabe 

Erstelle ein weiteres Objektdiagramm für das Objekt `model` nach dem die Methode `moveSnake` aufgerufen wurde!

\footnotesize

```{.java .cb-nb first_number=1}
model.moveSnake();
System.out.println(model.snake);
System.out.println(model.applePosition);
```
\normalsize
