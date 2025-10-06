
import org.example.Model;
import org.example.TUI;


void main() throws IOException, InterruptedException {

    playSnake(50, 20);


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