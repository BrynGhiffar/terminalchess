public class Castling {
    private boolean hasLeftRookMoved;
    private boolean hasRightRookMoved;
    private boolean hasKingMoved;

    public Castling() {
        hasLeftRookMoved = false;
        hasRightRookMoved = false;
        hasKingMoved = false;
    }

    public boolean leftSidePossible() {
        return !hasKingMoved() && !hasLeftRookMoved();
    }

    public boolean rightSidePossible() {
        return !hasKingMoved() && !hasRightRookMoved();
    }

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
