package org.example;

public class Model {

    public final int cols;
    public final int rows;
    public Snake snake;
    public V2 direction;
    public V2 applePosition;

    public Model(int cols, int rows, Snake snake, V2 direction, V2 applePosition) {
        this.cols = cols;
        this.rows = rows;
        this.snake = snake;
        this.direction = direction;
        this.applePosition = applePosition;
    }


    public Model(int cols, int rows) {
        this(cols,rows,new Snake(new V2(cols / 2, rows / 2)), new V2(1, 0), new V2(cols - 1, rows - 1));

//        this.cols = cols;
//        this.rows = rows;
//        this.snake = new Snake(new V2(cols / 2, rows / 2));
//        this.direction = new V2(1, 0);
//        this.applePosition = new V2(cols - 1, rows - 1);
    }


    public boolean snakeIsAlive() {
        return Utils.isOnBoard(snake.head(), cols, rows) && !snake.tailBitten();
    }

    public boolean boardIsFull() {
        return snake.getCoordinates().size() == rows * cols;
    }

    public boolean gameOngoing() {
        return snakeIsAlive() && !boardIsFull();
    }

    public UIState getUIState() {
        return new UIState(snake.head(), snake.tail(), applePosition);
    }

    public void moveSnake() {
        snake = snake.move(direction, applePosition);
        if (snake.digesting() && !boardIsFull()) {
            applePosition = Utils.generateRandomFreeCoordinates(snake.getCoordinates(), rows, cols);
        }
    }

    public void setDirection(char key) {
        direction = Utils.computeNewDirection(direction, key);
    }

    public String getEndMessage() {
        if (boardIsFull()) return "won";
        else return "lost";
    }

}