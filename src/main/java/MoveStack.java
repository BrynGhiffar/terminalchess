import java.util.LinkedList;
import java.util.List;

public class MoveStack {

    private List<Move> pastMoves;

    public MoveStack() {
        pastMoves = new LinkedList<>();
    }

    public Move getLastMove() {
        return pastMoves.get(pastMoves.size() - 1);
    }

    public void addMove(Move move) {
        pastMoves.add(move);
    }

    public Move removeLast() {
        Move rm = getLastMove();
        pastMoves.remove(pastMoves.size() - 1);
        return rm;
    }

    public boolean isEmpty() {
        return pastMoves.isEmpty();
    }

}
