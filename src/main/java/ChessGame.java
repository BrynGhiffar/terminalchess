import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;

public class ChessGame extends VirtualChessGame {

//    private VirtualChessGame virtualChessGame;
    Terminal terminal;

    public ChessGame(Terminal terminal) {
        super(terminal);
        this.terminal = terminal;
//        this.virtualChessGame = new VirtualChessGame(terminal);
    }

    public void start() throws IOException, InterruptedException {
        boolean quit = false;
        terminal.setCursorVisible(false);
        do {
            draw();
            KeyStroke key = terminal.readInput();
            sendKey(key);
            if (key.getCharacter() != null && key.getCharacter() == 'q') {
                quit = true;
            }
        } while (!quit);
    }

    public void sendKey(KeyStroke key) {
        if (key.getKeyType() == KeyType.ArrowUp) {
            moveCursorUp();
        } else if (key.getKeyType() == KeyType.ArrowDown) {
            moveCursorDown();
        } else if (key.getKeyType() == KeyType.ArrowLeft) {
            moveCursorLeft();
        } else if (key.getKeyType() == KeyType.ArrowRight) {
            moveCursorRight();
        } else if (key.getCharacter() != null) {
            if (key.getCharacter() == ' ') {
                if (isSelecting())
                    moveSelectedPiece();
                else
                    selectPiece();

            } else if (key.getCharacter() == 'u') {
                undo();
            }
        }
    }

}
