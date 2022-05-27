public enum Piece {

    WHITE_KING(true, Figure.WHITE_KING),
    WHITE_QUEEN(true, Figure.WHITE_QUEEN),
    WHITE_BISHOP(true, Figure.WHITE_BISHOP),
    WHITE_KNIGHT(true, Figure.WHITE_KNIGHT),
    WHITE_ROOK(true, Figure.WHITE_ROOK),
    WHITE_PAWN(true, Figure.WHITE_PAWN),

    BLACK_KING(false, Figure.BLACK_KING),
    BLACK_QUEEN(false, Figure.BLACK_QUEEN),
    BLACK_BISHOP(false, Figure.BLACK_BISHOP),
    BLACK_KNIGHT(false, Figure.BLACK_KNIGHT),
    BLACK_ROOK(false, Figure.BLACK_ROOK),
    BLACK_PAWN(false, Figure.BLACK_PAWN),
    EMPTY(false, Figure.EMPTY);

    private Boolean isWhite;
    private String[] figure;

    Piece(Boolean isWhite, String[] figure) {
        this.isWhite = isWhite;
        this.figure = figure.clone();
    }

    public Boolean isWhite() {
        return isWhite;
    }

    public Boolean isBlack() {
        return !isWhite() && !isEmpty();
    }

    // Will return false when one of the pieces are empty
    public Boolean sameColor(Piece piece) {
        return (piece.isWhite() && isWhite()) || (piece.isBlack() && isBlack()) || (piece.isEmpty() && isEmpty());
    }

    // Will return true when one of the pieces are empty
    public Boolean differentColor(Piece piece) {
        return !sameColor(piece);
    }

    // Returns true if the piece is an enemy piece
    public Boolean enemyPiece(Piece piece) {
        return differentColor(piece) && !(isEmpty() || piece.isEmpty());
    }

    public Boolean allyPiece(Piece piece) {
        return sameColor(piece) && !(isEmpty() || piece.isEmpty());
    }

    public boolean isKing() {
        return isWhiteKing() || isBlackKing();
    }

    public boolean isQueen() {
        return isWhiteQueen() || isBlackQueen();
    }

    public boolean isBishop() {
        return isWhiteBishop() || isBlackBishop();
    }

    public boolean isKnight() {
        return isWhiteKnight() || isBlackKnight();
    }

    public boolean isRook() {
        return isWhiteRook() || isBlackRook();
    }

    public boolean isPawn() {
        return isWhitePawn() || isBlackPawn();
    }

    public String[] toFigure() {
        return figure.clone();
    }

    public boolean isEmpty() {
        return this == EMPTY;
    }

    public boolean isWhiteKing() {
        return this == WHITE_KING;
    }

    public boolean isWhiteQueen() {
        return this == WHITE_QUEEN;
    }

    public boolean isWhiteBishop() {
        return this == WHITE_BISHOP;
    }

    public boolean isWhiteKnight() {
        return this == WHITE_KNIGHT;
    }

    public boolean isWhiteRook() {
        return this == WHITE_ROOK;
    }

    public boolean isWhitePawn() {
        return this == WHITE_PAWN;
    }

    public boolean isBlackKing() {
        return this == BLACK_KING;
    }

    public boolean isBlackQueen() {
        return this == BLACK_QUEEN;
    }

    public boolean isBlackBishop() {
        return this == BLACK_BISHOP;
    }

    public boolean isBlackKnight() {
        return this == BLACK_KNIGHT;
    }

    public boolean isBlackRook() {
        return this == BLACK_ROOK;
    }

    public boolean isBlackPawn() {
        return this == BLACK_PAWN;
    }

}
