package org.example;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.swing.SwingTerminalFontConfiguration;

import java.io.IOException;

import static java.lang.Thread.sleep;

public class TUI {
    private final int cols;
    private final int rows;
    private final TerminalScreen screen;
    private final TextGraphics textGraphics;


    public TUI(int cols, int rows) throws IOException {
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


    public void print(UIState uiState) throws IOException, InterruptedException {
        screen.clear();
        textGraphics.putString(uiState.snakeHead().x() + 1, uiState.snakeHead().y() + 1, "Q");
        textGraphics.putString(uiState.applePosition().x() + 1, uiState.applePosition().y() + 1, "@");
        for (var v : uiState.snakeTail()) {
            textGraphics.putString(v.x() + 1, v.y() + 1, "O");
        }
        for (int i = 0; i < rows + 2; i++) {
            textGraphics.putString(0, i, "▒");
            textGraphics.putString(cols + 1, i, "▒");
        }
        for (int i = 0; i < cols + 2; i++) {
            textGraphics.putString(i, 0, "▒");
            textGraphics.putString(i, rows + 1, "▒");
        }
        textGraphics.putString(1, rows + 2, "Points: " + uiState.snakeTail().size());
        screen.refresh();
        sleep(600);
    }

    public void close() throws IOException {
        screen.close();
    }

    public void printString(String s) throws IOException, InterruptedException {
        screen.clear();
        textGraphics.putString(cols / 2, rows / 2, s);
        screen.refresh();
        sleep(10000);

    }

    public char getPressedKey() throws IOException {
        var input = screen.pollInput();
        if (input != null) {
            return input.getCharacter();
        }
        return ' ';
    }
}