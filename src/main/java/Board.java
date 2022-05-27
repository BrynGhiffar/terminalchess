// The board class is used as the data structure to store the information of the chessboard
// in memory. It's primary responsibilities are simply storing the pieces within a two-dimensional
// array of chess pieces. By default, the board data structure initializes with the default layout
// of chess.
public class Board {

    private Piece[][] board;
    private Bound bound;
    private boolean whiteRightCastle;
    private boolean whiteLeftCastle;


    // Default constructor, using default chess piece layout
    public Board() {
        bound = new Bound(0, 7, 0, 7);
        board = new Piece[8][8];

        // assume default chess arrangement is on the board
        ChessPosition origin = new ChessPosition(0, 0);
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                setEmptyAt(origin.addRow(i).addCol(j));
            }
        }
        setBlackRookAt(origin.addRow(0).addCol(0));
        setBlackKnightAt(origin.addRow(0).addCol(1));
        setBlackBishopAt(origin.addRow(0).addCol(2));
        setBlackQueenAt(origin.addRow(0).addCol(3));
        setBlackKingAt(origin.addRow(0).addCol(4));
        setBlackBishopAt(origin.addRow(0).addCol(5));
        setBlackKnightAt(origin.addRow(0).addCol(6));
        setBlackRookAt(origin.addRow(0).addCol(7));

        setWhiteRookAt(origin.addRow(7).addCol(0));
        setWhiteKnightAt(origin.addRow(7).addCol(1));
        setWhiteBishopAt(origin.addRow(7).addCol(2));
        setWhiteQueenAt(origin.addRow(7).addCol(3));
        setWhiteKingAt(origin.addRow(7).addCol(4));
        setWhiteBishopAt(origin.addRow(7).addCol(5));
        setWhiteKnightAt(origin.addRow(7).addCol(6));
        setWhiteRookAt(origin.addRow(7).addCol(7));

        for (int i = 0; i < 8; i++) {
            setBlackPawnAt(origin.addRow(1).addCol(i));
            setWhitePawnAt(origin.addRow(6).addCol(i));
        }
    }

    // Copy constructor of the board class
    public Board(Board board) {
        this.bound = new Bound(board.bound);
        this.board = new Piece[8][8];
        ChessPosition current;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                current = new ChessPosition(i, j);
                set(current, board.at(current));
            }
        }
    }

    // Setters and getters that are meant as shorthands whenever
    // a particular square on the board is needed to be set to a piece

    public Bound bound() {
        return bound;
    }

    public void set(ChessPosition position, Piece piece) {
        board[position.getRow()][position.getCol()] = piece;
    }
    
    public Piece at(ChessPosition position) {
        return board[position.getRow()][position.getCol()];
    }

    public void setWhiteKingAt(ChessPosition position) {
        set(position, Piece.WHITE_KING);
    }

    public void setWhiteQueenAt(ChessPosition position) {
        set(position, Piece.WHITE_QUEEN);
    }

    public void setWhiteBishopAt(ChessPosition position) {
        set(position, Piece.WHITE_BISHOP);
    }

    public void setWhiteKnightAt(ChessPosition position) {
        set(position, Piece.WHITE_KNIGHT);
    }

    public void setWhiteRookAt(ChessPosition position) {
        set(position, Piece.WHITE_ROOK);
    }

    public void setWhitePawnAt(ChessPosition position) {
        set(position, Piece.WHITE_PAWN);
    }

    public void setBlackKingAt(ChessPosition position) {
        set(position, Piece.BLACK_KING);
    }

    public void setBlackQueenAt(ChessPosition position) {
        set(position, Piece.BLACK_QUEEN);
    }

    public void setBlackBishopAt(ChessPosition position) {
        set(position, Piece.BLACK_BISHOP);
    }

    public void setBlackKnightAt(ChessPosition position) {
        set(position, Piece.BLACK_KNIGHT);
    }

    public void setBlackRookAt(ChessPosition position) {
        set(position, Piece.BLACK_ROOK);
    }

    public void setBlackPawnAt(ChessPosition position) {
        set(position, Piece.BLACK_PAWN);
    }

    public void setEmptyAt(ChessPosition position) {
        set(position, Piece.EMPTY);
    }
}
