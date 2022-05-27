// The castling class is another utility class that stores the state of the board.
// Specifically the castling class keeps track whether a particular side of the board
// has castled. It also provides some functions to help check if castling is possible
public class Castling {
    private boolean hasLeftRookMoved;
    private boolean hasRightRookMoved;
    private boolean hasKingMoved;

    // These are initially set to false. Since the rook and king at the start of the game
    // have not moved.
    public Castling() {
        hasLeftRookMoved = false;
        hasRightRookMoved = false;
        hasKingMoved = false;
    }

    // Functions that help check if castling is possible
    public boolean leftSidePossible() {
        return !hasKingMoved() && !hasLeftRookMoved();
    }

    public boolean rightSidePossible() {
        return !hasKingMoved() && !hasRightRookMoved();
    }

    // Setters and getters
    public boolean hasLeftRookMoved() {
        return hasLeftRookMoved;
    }

    public void setLeftRookMoved(boolean hasLeftRookMoved) {
        this.hasLeftRookMoved = hasLeftRookMoved;
    }

    public boolean hasRightRookMoved() {
        return hasRightRookMoved;
    }

    public void setRightRookMoved(boolean hasRightRookMoved) {
        this.hasRightRookMoved = hasRightRookMoved;
    }

    public boolean hasKingMoved() {
        return hasKingMoved;
    }

    public void setKingMoved(boolean hasKingMoved) {
        this.hasKingMoved = hasKingMoved;
    }
}
