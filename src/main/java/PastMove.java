public class PastMove {
    private final ChessPosition from;
    private final ChessPosition to;
    private final Piece piece;

    public PastMove(ChessPosition from, ChessPosition to, Piece piece) {
        this.from = from;
        this.to = to;
        this.piece = piece;
    }

    public PastMove(PastMove pastMove) {
        this.from = new ChessPosition(pastMove.from);
        this.to = new ChessPosition(pastMove.to);
        this.piece = pastMove.piece;
    }

    public ChessPosition getFrom() {
        return from;
    }

    public ChessPosition getTo() {
        return to;
    }

    public Piece getPiece() {
        return piece;
    }
}

