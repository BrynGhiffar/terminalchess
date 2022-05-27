import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class VirtualChessGame {

    private ChessBoardDisplay chessBoardDisplay;
    private ChessPosition cursorPosition;

    // Selected piece position will not be empty on focus
    private ChessPosition selectedPiecePosition;
    private Board board;
    // list of possible moves for a given selected piece
    private List<Move> moves;
    private MoveStack moveStack;
    private Castling whiteCastling;
    private Castling blackCastling;

    public VirtualChessGame(Terminal terminal) {
        // set the initial cursor
        cursorPosition = new ChessPosition(4, 4);
        selectedPiecePosition = cursorPosition.unFocus();
        this.chessBoardDisplay = new ChessBoardDisplay(terminal);
        this.board = new Board();
        this.moveStack = new MoveStack();
        this.whiteCastling = new Castling();
        this.blackCastling = new Castling();
    }

//        ▄▄ ▄▄ ▄▄ ▄▄ █▀▄ █ █▀ █▀█ █░░ ▄▀█ █▄█ ▄▄ ▄▄ ▄▄ ▄▄
//        ░░ ░░ ░░ ░░ █▄▀ █ ▄█ █▀▀ █▄▄ █▀█ ░█░ ░░ ░░ ░░ ░░

    public void draw() throws IOException, InterruptedException {
        // draw the board based on virtualBoard
        chessBoardDisplay.putBoard();
        ChessPosition origin = new ChessPosition(0, 0);
        for (int i = 0; i < 8; i++)
            for (int j = 0; j < 8; j++) {
                switch (board.at(origin.addRow(i).addCol(j))) {
                    case WHITE_KING -> chessBoardDisplay.putWhiteKing(j, i);
                    case WHITE_QUEEN -> chessBoardDisplay.putWhiteQueen(j, i);
                    case WHITE_BISHOP -> chessBoardDisplay.putWhiteBishop(j, i);
                    case WHITE_KNIGHT -> chessBoardDisplay.putWhiteKnight(j, i);
                    case WHITE_ROOK -> chessBoardDisplay.putWhiteRook(j, i);
                    case WHITE_PAWN -> chessBoardDisplay.putWhitePawn(j, i);

                    case BLACK_KING -> chessBoardDisplay.putBlackKing(j, i);
                    case BLACK_QUEEN -> chessBoardDisplay.putBlackQueen(j, i);
                    case BLACK_BISHOP -> chessBoardDisplay.putBlackBishop(j, i);
                    case BLACK_KNIGHT -> chessBoardDisplay.putBlackKnight(j, i);
                    case BLACK_ROOK -> chessBoardDisplay.putBlackRook(j, i);
                    case BLACK_PAWN -> chessBoardDisplay.putBlackPawn(j, i);
                }
            }

        // draw the selected piece
        if (selectedPiecePosition.isFocus()) {
            chessBoardDisplay.putHighlightAt(selectedPiecePosition.getCol(), selectedPiecePosition.getRow(),
                    new TextColor.RGB(0, 255, 0), board.at(selectedPiecePosition));
            for (Move move : moves) {
                chessBoardDisplay.putHighlightAt(move.getTo().getCol(), move.getTo().getRow(),
                        new TextColor.RGB(0, 0, 255), board.at(move.getTo()));
            }
        }

        // Draw the cursor
        chessBoardDisplay.putHighlightAt(cursorPosition.getCol(), cursorPosition.getRow(),
                new TextColor.RGB(255, 0, 0), board.at(cursorPosition));

    }
//        █▀▀ █▀█ █▄░█ ▀█▀ █▀█ █▀█ █░░ █▀
//        █▄▄ █▄█ █░▀█ ░█░ █▀▄ █▄█ █▄▄ ▄█

    public void moveCursorUp() {
        if (cursorPosition.subRow(1).withinBound(board.bound())) {
            cursorPosition = cursorPosition.subRow(1);
        }
    }

    public void moveCursorDown() {
        if (cursorPosition.addRow(1).withinBound(board.bound())) {
            cursorPosition = cursorPosition.addRow(1);
        }
    }

    public void moveCursorLeft() {
        if (cursorPosition.subCol(1).withinBound(board.bound())) {
            cursorPosition = cursorPosition.subCol(1);
        }
    }

    public void moveCursorRight() {
        if (cursorPosition.addCol(1).withinBound(board.bound())) {
            cursorPosition = cursorPosition.addCol(1);
        }
    }

    public void selectPiece() {
        if (!selectedPiecePosition.isFocus() && !board.at(cursorPosition).isEmpty()) {
            selectedPiecePosition = new ChessPosition(cursorPosition);
            moves = generateMoves();
        } else {
            deselectPiece();
        }
    }

    public void deselectPiece() {
        selectedPiecePosition = selectedPiecePosition.unFocus();
        moves = new LinkedList<>();
    }

    public boolean isSelecting() {
        return selectedPiecePosition.isFocus();
    }

    public void moveSelectedPiece() {
        List<Move> selectedMoves = moves.stream().filter(move -> move.getTo().equals(cursorPosition)).toList();
        if (selectedPiecePosition.isFocus() && selectedMoves.size() > 0) {

            Move move = selectedMoves.get(0);
            Piece piece = move.getPiece();
            if (piece.isWhiteKing()) {
                whiteCastling.setKingMoved(true);
            }
            if (piece.isBlackKing()) {
                blackCastling.setKingMoved(true);
            }
            if (piece.isWhiteRook()) {
                if (move.getFrom().atFirstColumn())
                    whiteCastling.setLeftRookMoved(true);
                if (move.getFrom().atLastColumn())
                    whiteCastling.setRightRookMoved(true);
            }

            if (piece.isBlackRook()) {
                if (move.getFrom().atFirstColumn())
                    blackCastling.setLeftRookMoved(true);
                if (move.getFrom().atLastColumn())
                    blackCastling.setRightRookMoved(true);
            }

            board.setEmptyAt(move.getFrom());
            board.set(move.getTo(), piece);

            if (move.isKingSideCastling()) {
                if (piece.isWhite()) {
                    board.setWhiteRookAt(move.getTo().subCol(1));
                    board.setEmptyAt(move.getFrom().addCol(3));
                    whiteCastling.setRightRookMoved(true);
                    whiteCastling.setKingMoved(true);
                }
                if (piece.isBlack()) {
                    board.setBlackRookAt(move.getTo().subCol(1));
                    board.setEmptyAt(move.getFrom().addCol(3));
                    blackCastling.setRightRookMoved(true);
                    blackCastling.setKingMoved(true);
                }
            }

            if (move.isQueenSideCastling()) {
                if (piece.isWhite()) {
                    board.setWhiteRookAt(move.getTo().addCol(1));
                    board.setEmptyAt(move.getFrom().subCol(4));
                    whiteCastling.setLeftRookMoved(true);
                    whiteCastling.setKingMoved(true);
                }
                if (piece.isBlack()) {
                    board.setBlackRookAt(move.getTo().addCol(1));
                    board.setEmptyAt(move.getFrom().subCol(4));
                    whiteCastling.setLeftRookMoved(true);
                    whiteCastling.setKingMoved(true);
                }
            }

            moveStack.addMove(move);
            selectedPiecePosition = selectedPiecePosition.unFocus();

            if (move.isLeftEnPassant()) {
                board.setEmptyAt(move.getFrom().subCol(1));
            }
            if (move.isRightEnPassant()) {
                board.setEmptyAt(move.getFrom().addCol(1));
            }
        } else {
            deselectPiece();
        }
    }

    public void undo() {
        // Edge case: undoing castling
        if (!moveStack.isEmpty()) {
            Move lastMove = moveStack.removeLast();
            if (lastMove.isCapture()) {
                if (lastMove.isRightEnPassant()) {
                    Piece capturedPiece = lastMove.getCapturedPiece();
                    Piece movedPiece = lastMove.getPiece();
                    board.set(lastMove.getFrom(), movedPiece);
                    board.setEmptyAt(lastMove.getTo());
                    board.set(lastMove.getFrom().addCol(1), capturedPiece);
                } else if (lastMove.isLeftEnPassant()) {
                    Piece capturedPiece = lastMove.getCapturedPiece();
                    Piece movedPiece = lastMove.getPiece();
                    board.setEmptyAt(lastMove.getTo());
                    board.set(lastMove.getFrom(), movedPiece);
                    board.set(lastMove.getFrom().subCol(1), capturedPiece);
                } else {
                    Piece capturedPiece = lastMove.getCapturedPiece();
                    Piece movedPiece = lastMove.getPiece();
                    board.set(lastMove.getFrom(), movedPiece);
                    board.set(lastMove.getTo(), capturedPiece);
                }
            } else {
                Piece movedPiece = lastMove.getPiece();
                board.set(lastMove.getFrom(), movedPiece);
                board.setEmptyAt(lastMove.getTo());

                // First king move undo
                if (lastMove.isFirstKingMove()) {
                    if (movedPiece.isWhite())
                        whiteCastling.setKingMoved(false);
                    else if (movedPiece.isBlack())
                        blackCastling.setKingMoved(false);
                }

                if (lastMove.isFirstRookMove()) {
                }

                // Castling undo
                if (lastMove.isKingSideCastling()) {
                    if (movedPiece.isWhite()) {
                        board.setEmptyAt(lastMove.getTo().subCol(1));
                        board.setWhiteRookAt(lastMove.getFrom().addCol(3));
                        whiteCastling.setKingMoved(false);
                        whiteCastling.setRightRookMoved(false);
                    }
                    if (movedPiece.isBlack()) {
                        board.setEmptyAt(lastMove.getTo().subCol(1));
                        board.setBlackRookAt(lastMove.getFrom().addCol(3));
                        blackCastling.setKingMoved(false);
                        blackCastling.setRightRookMoved(false);
                    }
                }
                if (lastMove.isQueenSideCastling()) {
                    if (movedPiece.isWhite()) {
                        board.setEmptyAt(lastMove.getTo().addCol(1));
                        board.setWhiteRookAt(lastMove.getFrom().subCol(4));
                        whiteCastling.setLeftRookMoved(false);
                        whiteCastling.setKingMoved(false);
                    }
                    if (movedPiece.isBlack()) {
                        board.setEmptyAt(lastMove.getTo().addCol(1));
                        board.setBlackRookAt(lastMove.getFrom().subCol(4));
                        blackCastling.setLeftRookMoved(false);
                        blackCastling.setKingMoved(false);
                    }
                }
            }
        }
    }

//          █▀▄▀█ █▀█ █░█ █▀▀   █▀▀ █▀▀ █▄░█ █▀▀ █▀█ ▄▀█ ▀█▀ █ █▀█ █▄░█
//          █░▀░█ █▄█ ▀▄▀ ██▄   █▄█ ██▄ █░▀█ ██▄ █▀▄ █▀█ ░█░ █ █▄█ █░▀█

    public List<Move> generateMoves() {
        // what do you need to know before you know if a move is valid:
        //
        // generate possible moves pointed to by selected piece
        List<Move> moves = new LinkedList<>();
        Piece piece = board.at(selectedPiecePosition);
        if (!piece.isEmpty()) {
            if (piece.isPawn()) {
                ChessPosition target;

                // Sideways pawn capture
                if (piece.isWhitePawn())
                    target = selectedPiecePosition.subRow(1).subCol(1);
                else
                    target = selectedPiecePosition.addRow(1).subCol(1);
                if (target.withinBound(board.bound()) && board.at(target).enemyPiece(piece)) {
                    var move = new Move(selectedPiecePosition, target, piece);
                    move.setCapturedPiece(board.at(target));
                    moves.add(move);

                }
                // en-passant is a sort of sideways pawn capture
                if (selectedPiecePosition.subCol(1).withinBound(board.bound())) {
                    ChessPosition enemyPiecePosition = selectedPiecePosition.subCol(1);
                    Piece enemyPiece = board.at(selectedPiecePosition.subCol(1));
                    if (target.withinBound(board.bound()) &&
                            piece.enemyPiece(enemyPiece) &&
                            enemyPiece.isPawn() &&
                            moveStack.getLastMove().getTo().equals(enemyPiecePosition) &&
                            moveStack.getLastMove().isPawnJump()
                    ) {
                        var move = new Move(
                                selectedPiecePosition, target, piece
                        );
                        move.setLeftEnPassant(true);
                        move.setCapturedPiece(enemyPiece);
                        moves.add(move);
                    }
                }

                if (piece.isWhitePawn())
                    target = selectedPiecePosition.subRow(1).addCol(1);
                else
                    target = selectedPiecePosition.addRow(1).addCol(1);
                if (target.withinBound(board.bound()) && board.at(target).enemyPiece(piece)) {
                    var move = new Move(selectedPiecePosition, target, piece);
                    move.setCapturedPiece(board.at(target));
                    moves.add(move);
                }
                if (selectedPiecePosition.addCol(1).withinBound(board.bound())) {
                    ChessPosition enemyPiecePosition = selectedPiecePosition.addCol(1);
                    Piece enemyPiece = board.at(selectedPiecePosition.addCol(1));
                    if (target.withinBound(board.bound()) &&
                            piece.enemyPiece(enemyPiece) &&
                            // Is a pawn
                            enemyPiece.isPawn() &&
                            moveStack.getLastMove().getTo().equals(enemyPiecePosition) &&
                            moveStack.getLastMove().isPawnJump()
                    ) {
                        var move = new Move(
                                selectedPiecePosition, target, piece
                        );
                        move.setRightEnPassant(true);
                        move.setCapturedPiece(enemyPiece);
                        moves.add(move);
                    }
                }

                // Pawn one move forward
                if (piece.isWhitePawn())
                    target = selectedPiecePosition.subRow(1);
                else
                    target = selectedPiecePosition.addRow(1);
                if (target.withinBound(board.bound()) && board.at(target).isEmpty()) {
                    moves.add(new Move(selectedPiecePosition, target, piece));

                    // Pawn two move forward
                    // Can only move two ahead if one ahead is possible
                    if (piece.isWhitePawn())
                        target = selectedPiecePosition.subRow(2);
                    else
                        target = selectedPiecePosition.addRow(2);
                    if (board.at(target).isEmpty() && target.withinBound(board.bound())) {
                        if ((selectedPiecePosition.atSecondRank() && piece.isWhite()) ||
                                (piece.isBlack() && selectedPiecePosition.atSeventhRank()))
                            moves.add(new Move(selectedPiecePosition, target, piece));
                    }
                }

                // Nice moving pieces
            } else if (piece.isQueen()) {
                moves.addAll(generateDiagonalMoves());
                moves.addAll(generateHorizontalVerticalMoves());
            } else if (piece.isBishop()) {
                moves.addAll(generateDiagonalMoves());
            } else if (piece.isRook()) {
                moves.addAll(generateHorizontalVerticalMoves());
            } else if (piece.isKnight()) {
                moves.addAll(generateLMoves());
            } else if (piece.isKing()) {
                moves.addAll(generateAllSideOneMoves());
                if (piece.isWhite()) {
                    if (whiteCastling.leftSidePossible()) {
                        // No piece between the rook and the king
                        var from = selectedPiecePosition;
                        // If there are no pieces in between the king and the rook
                        if (Arrays.asList(1, 2, 3).stream().map(d -> board.at(from.subCol(d))).allMatch(Piece::isEmpty)) {
                            var move = new Move(from, from.subCol(2), piece);
                            move.setQueenSideCastling(true);
                            moves.add(move);
                        }
                    }
                    if (whiteCastling.rightSidePossible()) {
                        var from = selectedPiecePosition;
                        if (Arrays.asList(1, 2).stream().map(d -> board.at(from.addCol(d))).allMatch(Piece::isEmpty)) {
                            var move = new Move(from, from.addCol(2), piece);
                            move.setKingSideCastling(true);
                            moves.add(move);
                        }
                    }
                }
                if (piece.isBlack()) {
                    if (blackCastling.leftSidePossible()) {
                        // No piece between the rook and the king
                        var from = selectedPiecePosition;
                        // If there are no pieces in between the king and the rook
                        if (Arrays.asList(1, 2, 3).stream().map(d -> board.at(from.subCol(d))).allMatch(Piece::isEmpty)) {
                            var move = new Move(from, from.subCol(2), piece);
                            move.setQueenSideCastling(true);
                            moves.add(move);
                        }
                    }
                    if (blackCastling.rightSidePossible()) {

                        var from = selectedPiecePosition;
                        if (Arrays.asList(1, 2).stream().map(d -> board.at(from.addCol(d))).allMatch(Piece::isEmpty)) {
                            var move = new Move(from, from.addCol(2), piece);
                            move.setKingSideCastling(true);
                            moves.add(move);
                        }
                    }
                }
                moves = moves.stream().map(move -> {
                        var newMove = new Move(move);
                        if (piece.isWhite() && !whiteCastling.hasKingMoved())
                            newMove.setFirstKingMove(true);
                        if (piece.isBlack() && !blackCastling.hasKingMoved())
                            newMove.setFirstKingMove(true);
                        return newMove;
                    }).toList();
            }
        }
        return moves;
    }

    public List<Move> generateAllSideOneMoves() {
        var direction = new LinkedList<List<Integer>>();
        direction.add(Arrays.asList(0, 1));
        direction.add(Arrays.asList(0, -1));
        direction.add(Arrays.asList(1, 0));
        direction.add(Arrays.asList(-1, 0));
        direction.add(Arrays.asList(1, 1));
        direction.add(Arrays.asList(1, -1));
        direction.add(Arrays.asList(-1, 1));
        direction.add(Arrays.asList(-1, -1));

        var moves = new LinkedList<Move>();
        var from = selectedPiecePosition;
        for (var dir: direction) {
            int d1 = dir.get(0), d2 = dir.get(1);
            var to = from.addCol(d1).addRow(d2);
            if (!to.withinBound(board.bound()))
                continue;
            if (board.at(to).allyPiece(board.at(from)))
                continue;
            var move = new Move(from, to, board.at(from));
            if (board.at(from).enemyPiece(board.at(to)))
                move.setCapturedPiece(board.at(to));
            moves.add(move);
        }
        return moves;
    }

    public List<Move> generateLMoves() {
        var direction = new LinkedList<List<Integer>>();
        direction.add(Arrays.asList(2, 1));
        direction.add(Arrays.asList(1, 2));
        direction.add(Arrays.asList(2, -1));
        direction.add(Arrays.asList(-1, 2));
        direction.add(Arrays.asList(-2, 1));
        direction.add(Arrays.asList(1, -2));
        direction.add(Arrays.asList(-2, -1));
        direction.add(Arrays.asList(-1, -2));

        var moves = new LinkedList<Move>();
        var from = selectedPiecePosition;
        for (var dir: direction) {
            int d1 = dir.get(0), d2 = dir.get(1);
            var to = from.addCol(d1).addRow(d2);
            if (!to.withinBound(board.bound()))
                continue;
            if (board.at(to).allyPiece(board.at(from)))
                continue;
            var move = new Move(from, to, board.at(from));
            if (board.at(from).enemyPiece(board.at(to)))
                move.setCapturedPiece(board.at(to));
            moves.add(move);
        }
        return moves;
    }

    public List<Move> generateHorizontalVerticalMoves() {
        var direction = new LinkedList<List<Integer>>();
        direction.add(Arrays.asList(0, 1));
        direction.add(Arrays.asList(0, -1));
        direction.add(Arrays.asList(1, 0));
        direction.add(Arrays.asList(-1, 0));

        var moves = new LinkedList<Move>();
        var from = selectedPiecePosition;
        for (var dir: direction) {
            int d1 = dir.get(0), d2 = dir.get(1);
            do {
                var to = from.addCol(d1).addRow(d2);
                if (!to.withinBound(board.bound()))
                    break;
                if (board.at(to).sameColor(board.at(from)))
                    break;
                var move = new Move(from, to, board.at(from));
                if (board.at(from).enemyPiece(board.at(to)))
                    move.setCapturedPiece(board.at(to));
                moves.add(move);
                if (board.at(from).enemyPiece(board.at(to)))
                    break;
                d1 += dir.get(0);
                d2 += dir.get(1);
            } while (true);
        }
        return moves;
    }

    public List<Move> generateDiagonalMoves() {
        var direction = new LinkedList<List<Integer>>();
        direction.add(Arrays.asList(1, 1));
        direction.add(Arrays.asList(1, -1));
        direction.add(Arrays.asList(-1, 1));
        direction.add(Arrays.asList(-1, -1));

        var moves = new LinkedList<Move>();
        var from = selectedPiecePosition;
        for (var dir: direction) {
            int d1 = dir.get(0), d2 = dir.get(1);
            do {
                var to = from.addCol(d1).addRow(d2);
                if (!to.withinBound(board.bound()))
                    break;
                if (board.at(to).sameColor(board.at(from)))
                    break;
                var move = new Move(from, to, board.at(from));
                if (board.at(from).enemyPiece(board.at(to)))
                    move.setCapturedPiece(board.at(to));
                moves.add(move);
                if (board.at(from).enemyPiece(board.at(to)))
                    break;
                d1 += dir.get(0);
                d2 += dir.get(1);
            } while (true);
        }
        return moves;
    }
}
