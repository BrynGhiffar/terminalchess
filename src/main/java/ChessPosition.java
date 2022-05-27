public class ChessPosition {

    private int row;
    private int col;
    final public static int MAX_CHESS_ROW = 8;
    final public static int MIN_CHESS_ROW = 0;

    final public static int MAX_CHESS_COL = 8;
    final public static int MIN_CHESS_COL = 0;
    private boolean focus;


    ChessPosition(int row, int col) {
        setRow(row);
        setCol(col);
        focus = true;
    }

    ChessPosition(ChessPosition chessPosition) {
        set(chessPosition);
        focus = true;
    }

    public boolean isFocus() {
        return focus;
    }

    public ChessPosition focus() {
        ChessPosition chessPosition = new ChessPosition(getRow(), getCol());
        chessPosition.focus = true;
        return chessPosition;
    }

    public ChessPosition unFocus() {
        ChessPosition chessPosition = new ChessPosition(getRow(), getCol());
        chessPosition.focus = false;
        return chessPosition;
    }



    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public boolean withinBound(Bound bound) {
        return bound.withinBound(getRow(), getCol());
    }

    public ChessPosition addRow(int dRow) {
        return new ChessPosition(getRow() + dRow, getCol());
    }

    public ChessPosition addCol(int dCol) {
        return new ChessPosition(getRow(), getCol() + dCol);
    }

    public ChessPosition subRow(int dRow) {
        return new ChessPosition(getRow() - dRow, getCol());
    }

    public ChessPosition subCol(int dCol) {
        return new ChessPosition(getRow(), getCol() - dCol);
    }

    public boolean atSecondRank() {
        return getRow() == 6;
    }

    public boolean atFirstColumn() {
        return getCol() == 0;
    }

    public boolean atLastColumn() {
        return getCol() == 7;
    }

    public boolean atSeventhRank() {
        return getRow() == 1;
    }

    public boolean equals(ChessPosition position) {
        return (position.getRow() == getRow()) && (position.getCol() == getCol());
    }

    public void set(ChessPosition chessPosition) {
        setCol(chessPosition.getCol());
        setRow(chessPosition.getRow());
    }
}
