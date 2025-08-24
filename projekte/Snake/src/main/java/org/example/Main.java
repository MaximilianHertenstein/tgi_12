import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.swing.SwingTerminalFontConfiguration;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.TerminalSize;
import java.util.Random;


import static java.lang.Thread.sleep;

void main() throws IOException, InterruptedException {

    playSnake(50, 20);


}


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
        var random = new Random();
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


class Model {

    private final int cols;
    private final int rows;
    private Snake snake;
    private V2 direction;
    private V2 applePosition;

    Model(int cols, int rows, Snake snake, V2 direction, V2 applePosition) {
        this.cols = cols;
        this.rows = rows;
        this.snake = snake;
        this.direction = direction;
        this.applePosition = applePosition;
    }


    Model(int cols, int rows) {
        this(cols,rows,new Snake(new V2(cols / 2, rows / 2)), new V2(1, 0), new V2(cols - 1, rows - 1));

//        this.cols = cols;
//        this.rows = rows;
//        this.snake = new Snake(new V2(cols / 2, rows / 2));
//        this.direction = new V2(1, 0);
//        this.applePosition = new V2(cols - 1, rows - 1);
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
        if (snake.digesting && !boardIsFull()) {
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

record UIState(V2 snakeHead, List<V2> snakeTail, V2 applePosition) {
}


class TUI {
    private final int cols;
    private final int rows;
    private final TerminalScreen screen;
    private final TextGraphics textGraphics;


    TUI(int cols, int rows) throws IOException {
        this.cols = cols;
        this.rows = rows;
        this.screen = new DefaultTerminalFactory().setPreferTerminalEmulator(true).setInitialTerminalSize(new TerminalSize(cols + 2, rows + 2))
                .setTerminalEmulatorFontConfiguration(
                        SwingTerminalFontConfiguration.getDefaultOfSize(30)
                ).createScreen();

        screen.startScreen();
        screen.setCursorPosition(null);
        textGraphics = screen.newTextGraphics();
    }
    // private val screen = DefaultTerminalFactory().setInitialTerminalSize(TerminalSize(columns + 2, rows + 2)).createScreen()


    void print(UIState uiState) throws IOException, InterruptedException {
        screen.clear();
        textGraphics.putString(uiState.snakeHead.x + 1, uiState.snakeHead.y + 1, "Q");
        textGraphics.putString(uiState.applePosition.x + 1, uiState.applePosition.y + 1, "@");
        for (var v : uiState.snakeTail) {
            textGraphics.putString(v.x + 1, v.y + 1, "O");
        }
        for (int i = 0; i < rows + 2; i++) {
            textGraphics.putString(0, i, "▒");
            textGraphics.putString(cols + 1, i, "▒");
        }
        for (int i = 0; i < cols + 2; i++) {
            textGraphics.putString(i, 0, "▒");
            textGraphics.putString(i, rows + 1, "▒");
        }
        textGraphics.putString(1, rows + 2, "Points: " + uiState.snakeTail.size());
        screen.refresh();
        sleep(600);
    }

    void close() throws IOException {
        screen.close();
    }

    void printString(String s) throws IOException, InterruptedException {
        screen.clear();
        textGraphics.putString(cols / 2, rows / 2, s);
        screen.refresh();
        sleep(10000);

    }

    char getPressedKey() throws IOException {
        var input = screen.pollInput();
        if (input != null) {
            return input.getCharacter();
        }
        return ' ';
    }
}


void playSnake(int columns, int rows) throws IOException, InterruptedException {
    var tui = new TUI(columns, rows);
    var model = new Model(columns, rows);
    tui.print(model.getUIState());
    while (model.gameOngoing()) {
        var input = tui.getPressedKey();
        model.setDirection(input);
        model.moveSnake();
        tui.print(model.getUIState());
    }
    tui.print(model.getUIState());
    tui.printString(model.getEndMessage());
    tui.close();
}