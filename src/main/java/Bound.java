// The bounds object is a useful utility class, designed to hold the bounds of an 8 x 8 chessboard.
// Using the bound object we can easily tell whether a certain position is within the bounds that
// we had previously specified
public class Bound {

    private int rowLowerBound;
    private int rowUpperBound;
    private int colLowerBound;
    private int colUpperBound;

    // Bound constructor specifying limits
    public Bound(int rowLowerBound, int rowUpperBound, int colLowerBound, int colUpperBound) {
        this.rowLowerBound = rowLowerBound;
        this.rowUpperBound = rowUpperBound;
        this.colLowerBound = colLowerBound;
        this.colUpperBound = colUpperBound;
    }

    // Bound copy constructor
    public Bound(Bound bound) {
        this.rowLowerBound = bound.rowLowerBound;
        this.rowUpperBound = bound.rowUpperBound;
        this.colLowerBound = bound.colLowerBound;
        this.colUpperBound = bound.colUpperBound;
    }

    // Accepts rows and columns and checks whether the particular rows and columns
    // are within bounds
    public boolean withinBound(int row, int col) {
        return (rowLowerBound <= row) && (row <= rowUpperBound) && (colLowerBound <= col) && (col <= colUpperBound);
    }

}
