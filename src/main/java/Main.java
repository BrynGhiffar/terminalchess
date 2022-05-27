import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;

public class Main {

    public static void testRegularChessGamePosition(Terminal terminal) throws IOException, InterruptedException{
        ChessBoardDisplay chessBoardDisplay = new ChessBoardDisplay(terminal);
        chessBoardDisplay.putBoard();
        chessBoardDisplay.putWhiteRook(7,7);
        chessBoardDisplay.putWhiteKnight(6,7);
        chessBoardDisplay.putWhiteBishop(5,7);
        chessBoardDisplay.putWhiteKing(4, 7);
        chessBoardDisplay.putWhiteQueen(3, 7);
        chessBoardDisplay.putWhiteBishop(2,7);
        chessBoardDisplay.putWhiteKnight(1,7);
        chessBoardDisplay.putWhiteRook(0,7);

        chessBoardDisplay.putBlackRook(7,0);
        chessBoardDisplay.putBlackKnight(6,0);
        chessBoardDisplay.putBlackBishop(5,0);
        chessBoardDisplay.putBlackKing(4, 0);
        chessBoardDisplay.putBlackQueen(3, 0);
        chessBoardDisplay.putBlackBishop(2,0);
        chessBoardDisplay.putBlackKnight(1,0);
        chessBoardDisplay.putBlackRook(0,0);
        
        for (int i = 0; i < 8; i++) {
            chessBoardDisplay.putWhitePawn(i,6);
            chessBoardDisplay.putBlackPawn(i, 1);
        }
    }



    public static void main(String... args) throws IOException, InterruptedException {
        DefaultTerminalFactory defaultTerminalFactory = new DefaultTerminalFactory();
        defaultTerminalFactory.setInitialTerminalSize(new TerminalSize(100, 100));
        Terminal terminal = defaultTerminalFactory.createTerminal();
//        testRegularChessGamePosition(terminal);
//        VirtualChessGame virtualChessGame = new VirtualChessGame(terminal);
//        virtualChessGame.draw();
        ChessGame chessGame = new ChessGame(terminal);
        chessGame.start();
    }
}
