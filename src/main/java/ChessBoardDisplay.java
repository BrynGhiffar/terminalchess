import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;


// The ChessBoardDisplay class is a core class that takes care of displaying the characters
// and highlighting the correct squares for the pieces to move. It is a class that wraps around
// the lanterna terminal api. Methods that work towards displaying the chessboard in the terminal
// are written in this class.
public class ChessBoardDisplay {

    private Terminal terminal;
    private TerminalPosition boardStart = new TerminalPosition(0, 0);
    private TerminalPosition chessBoardStart = new
            TerminalPosition(boardStart.getColumn() + 5, boardStart.getRow() + 4);

    public ChessBoardDisplay(Terminal terminal) {
        this.terminal = terminal;
    }

    public boolean isSquareBlack(int column, int row) {
        return (column + row) % 2 == 1;
    }

    // Displays the board described in The Figure class on the terminal screen
    public void putBoard() throws IOException, InterruptedException {
        for (int i = 0; i < Figure.BOARD.length; i++) {
            terminal.setCursorPosition(boardStart.getColumn(), boardStart.getRow() + i);
            terminal.putString(Figure.BOARD[i]);
        }
        terminal.flush();
    }

    // Translating the position of the board into a cell on the terminal,
    // signifying where the program should start to draw a particular piec.
    public TerminalPosition getStartSquare(int column, int row) {
        // given a position in the board returns the position corresponding
        // to the terminal character position
        return new TerminalPosition(
                chessBoardStart.getColumn() + 9 * column,
                chessBoardStart.getRow() + 5 * row
        );
    }

    // Highlights a particular square on the drawn board.
    public void putHighlightAt(int column, int row, TextColor color, Piece piece) throws IOException {
        terminal.setBackgroundColor(color);
        if (piece != null) {
            putPiece(piece.toFigure(), column, row);
        } else {
            putPiece(Figure.EMPTY, column, row);
        }
        terminal.resetColorAndSGR();

    }

    // Draws a piece in a given column or row on the terminal, with the object string
    // figure as the argument
    public void putPiece(String[] PIECE_CONTENT, int column, int row) throws IOException {
        String[] piece = PIECE_CONTENT.clone();
        TerminalPosition location = getStartSquare(column, row);
        terminal.setCursorPosition(location);
        if (isSquareBlack(column, row)) {
            for (int i = 0; i < piece.length; i++) {
                piece[i] = piece[i].replace(' ', '#');
            }
        }
        for (int i = 0; i < piece.length; i++) {
            piece[i] = piece[i].replace('$', ' ');
            terminal.setCursorPosition(location.getColumn(), location.withRelativeRow(i).getRow());
            terminal.putString(piece[i]);
        }
        terminal.flush();
    }

    // Utility methods based on putPiece that place a specified piece on the board
    public void putWhiteKing(int column, int row) throws IOException {
        putPiece(Figure.WHITE_KING, column, row);
    }

    public void putWhiteQueen(int column, int row) throws IOException {
        putPiece(Figure.WHITE_QUEEN, column, row);
    }

    public void putWhiteBishop(int column, int row) throws IOException {
        putPiece(Figure.WHITE_BISHOP, column, row);

    }

    public void putWhiteKnight(int column, int row) throws IOException {
        putPiece(Figure.WHITE_KNIGHT, column, row);

    }

    public void putWhiteRook(int column, int row) throws IOException {
        putPiece(Figure.WHITE_ROOK, column, row);

    }

    public void putWhitePawn(int column, int row) throws IOException {
        putPiece(Figure.WHITE_PAWN, column, row);
    }

    public void putBlackKing(int column, int row) throws IOException {
        putPiece(Figure.BLACK_KING, column, row);
    }

    public void putBlackQueen(int column, int row) throws IOException {
        putPiece(Figure.BLACK_QUEEN, column, row);
    }

    public void putBlackBishop(int column, int row) throws IOException {
        putPiece(Figure.BLACK_BISHOP, column, row);

    }

    public void putBlackKnight(int column, int row) throws IOException {
        putPiece(Figure.BLACK_KNIGHT, column, row);

    }

    public void putBlackRook(int column, int row) throws IOException {
        putPiece(Figure.BLACK_ROOK, column, row);

    }

    public void putBlackPawn(int column, int row) throws IOException {
        putPiece(Figure.BLACK_PAWN, column, row);
    }

}
