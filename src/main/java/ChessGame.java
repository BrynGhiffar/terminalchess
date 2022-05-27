import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;

public class ChessGame {

    private VirtualChessGame virtualChessGame;
    Terminal terminal;

    public ChessGame(Terminal terminal) {
        this.terminal = terminal;
        this.virtualChessGame = new VirtualChessGame(terminal);
    }

    public void start() throws IOException, InterruptedException {
        boolean quit = false;
        terminal.setCursorVisible(false);
        do {
            virtualChessGame.draw();
            KeyStroke key = terminal.readInput();
            sendKey(key);
            if (key.getCharacter() != null && key.getCharacter() == 'q') {
                quit = true;
            }
        } while (!quit);
    }

    public void sendKey(KeyStroke key) {
        if (key.getKeyType() == KeyType.ArrowUp) {
            virtualChessGame.moveCursorUp();
        } else if (key.getKeyType() == KeyType.ArrowDown) {
            virtualChessGame.moveCursorDown();
        } else if (key.getKeyType() == KeyType.ArrowLeft) {
            virtualChessGame.moveCursorLeft();
        } else if (key.getKeyType() == KeyType.ArrowRight) {
            virtualChessGame.moveCursorRight();
        } else if (key.getCharacter() != null) {
            if (key.getCharacter() == ' ') {
                if (virtualChessGame.isSelecting())
                    virtualChessGame.moveSelectedPiece();
                else
                    virtualChessGame.selectPiece();

            } else if (key.getCharacter() == 'u') {
                virtualChessGame.undo();
            }
        }
    }

}
