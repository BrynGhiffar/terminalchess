public class Move {
    private final ChessPosition from;
    private final ChessPosition to;
    private final Piece piece;
    private boolean isLeftEnPassant = false;
    private boolean isRightEnPassant = false;
    private boolean isKingSideCastling = false;
    private boolean isQueenSideCastling = false;
    private boolean isFirstKingMove = false;
    private boolean isFirstRookMove = false;
    private Piece capturedPiece = null;

    public Move(ChessPosition from, ChessPosition to, Piece piece) {
        this.from = from;
        this.to = to;
        this.piece = piece;
    }

    public Move(Move move) {
        this.from = move.from;
        this.to = move.to;
        this.piece = move.piece;
        this.isLeftEnPassant = move.isLeftEnPassant;
        this.isRightEnPassant = move.isRightEnPassant;
        this.isKingSideCastling = move.isKingSideCastling;
        this.isQueenSideCastling = move.isQueenSideCastling;
        this.isFirstKingMove = move.isFirstKingMove;
        this.isFirstRookMove = move.isFirstRookMove;
        this.capturedPiece = move.capturedPiece;

    }

    public ChessPosition getFrom() {
        return from;
    }

    public ChessPosition getTo() {
        return to;
    }

    public void setCapturedPiece(Piece capturedPiece) {
        this.capturedPiece = capturedPiece;
    }

    public Piece getCapturedPiece() {
        return this.capturedPiece;
    }

    public Piece getPiece() {
        return piece;
    }

    public boolean isCapture() {
        return capturedPiece != null;
    }

    public void setLeftEnPassant(boolean leftEnPassant) {
        isLeftEnPassant = leftEnPassant;
    }

    public void setRightEnPassant(boolean rightEnPassant) {
        isRightEnPassant = rightEnPassant;
    }

    public void setKingSideCastling(boolean kingSideCastling) {
        isKingSideCastling = kingSideCastling;
    }

    public void setQueenSideCastling(boolean queenSideCastling) {
        isQueenSideCastling = queenSideCastling;
    }

    public void setFirstKingMove(boolean firstKingMove) {
        isFirstKingMove = firstKingMove;
    }

    public void setFirstRookMove(boolean firstRookMove) {
        isFirstRookMove = firstRookMove;
    }

    public boolean isLeftEnPassant() {
        return isLeftEnPassant;
    }

    public boolean isRightEnPassant() {
        return isRightEnPassant;
    }

    public boolean isKingSideCastling() {
        return isKingSideCastling;
    }

    public boolean isQueenSideCastling() {
        return isQueenSideCastling;
    }

    public boolean isFirstRookMove() {
        return isFirstRookMove;
    }

    public boolean isFirstKingMove() {
        return isFirstKingMove;
    }

    public boolean isPawnJump() {
        // White pawn jump
        if (piece.isWhitePawn()) {
            return to.addRow(2).atSecondRank();
        }
        if (piece.isBlackPawn()) {
            return to.subRow(2).atSeventhRank();
        }
        return false;
    }
}
