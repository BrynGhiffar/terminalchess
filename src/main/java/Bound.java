public class Bound {

    private int rowLowerBound;
    private int rowUpperBound;
    private int colLowerBound;
    private int colUpperBound;

    public Bound(int rowLowerBound, int rowUpperBound, int colLowerBound, int colUpperBound) {
        this.rowLowerBound = rowLowerBound;
        this.rowUpperBound = rowUpperBound;
        this.colLowerBound = colLowerBound;
        this.colUpperBound = colUpperBound;
    }

    public Bound(Bound bound) {
        this.rowLowerBound = bound.rowLowerBound;
        this.rowUpperBound = bound.rowUpperBound;
        this.colLowerBound = bound.colLowerBound;
        this.colUpperBound = bound.colUpperBound;
    }

    public boolean withinBound(int row, int col) {
        return (rowLowerBound <= row) && (row <= rowUpperBound) && (colLowerBound <= col) && (col <= colUpperBound);
    }

}
