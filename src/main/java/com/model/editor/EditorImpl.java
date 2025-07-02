package com.model.editor;

import com.model.board.Board;
import com.model.board.builders.BoardBuilder;
import com.model.board.builders.BoardBuilderImpl;
import com.model.board.position.BoardPosition;
import com.model.piece.Piece;
import com.model.piece.PieceType;
import com.model.player.PlayerColor;

import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public final class EditorImpl implements Editor {

    private static final int DEFAULT_COLUMNS = 8;
    private static final int DEFAULT_ROWS = 8;
    private static final int MAX_NUMBER_OF_ROWS_AND_COLS = 26;
    private Board board;
    private StringBoard stringBoard;

    private final Map<PieceType, String> pieceTypeToLetter = Map.of(PieceType.KING, "k", PieceType.QUEEN, "q",
            PieceType.BISHOP, "b", PieceType.ROOK, "r", PieceType.PAWN, "p", PieceType.KNIGHT, "n");

    public EditorImpl() {
        final BoardBuilder boardBuilder = new BoardBuilderImpl();
        this.board = boardBuilder.columns(DEFAULT_COLUMNS).rows(DEFAULT_ROWS).build();
    }

    private boolean checkNewBoardDimension(final int cols, final int rows) {
        return cols > 0 && rows > 0 && cols <= MAX_NUMBER_OF_ROWS_AND_COLS && rows <= MAX_NUMBER_OF_ROWS_AND_COLS;
    }


    @Override
    public void addPieceToBoard(final Piece piece) {
        this.board.add(piece);
    }


    @Override
    public Board getBoardStatus() {
        return this.board;
    }


    @Override
    public void changeBoardDimensions(final int columns, final int rows) {
        if (this.checkNewBoardDimension(columns, rows)) {
            final BoardBuilder boardBuilder = new BoardBuilderImpl();
            this.board = boardBuilder.columns(columns).rows(rows).build();
        }
    }


    @Override
    public boolean changePiecePosition(final Piece piece, final BoardPosition position) {
        if (this.board.getPieceAtPosition(position).isPresent()) {
            return false;
        }
        piece.setPosition(position);
        return true;
    }


    @Override
    public void removePiece(final BoardPosition position) {
        this.getBoardStatus().removeAtPosition(position);
    }


    @Override
    public void createStartingBoard() {
        this.stringBoard = this.fromBoard(this.board);
    }

    @Override
    public StringBoard stringBoardFromNormal(final Board startingBoard) {
        return this.fromBoard(startingBoard);
    }

    private StringBoard fromBoard(final Board board) {
        return new StringBoardImpl(
                board.getPieces().stream()
                        .map(i -> this.getPieceStringCap(i) + "," + i.getPiecePosition().getX() + ","
                                + i.getPiecePosition().getY() + "/")
                        .collect(Collectors.joining()),
                board.getColumns(), board.getRows());
    }

    private String getPieceStringCap(final Piece piece) {
        final String letter = this.pieceTypeToLetter.get(piece.getType());
        return piece.getPlayer().getColor().equals(PlayerColor.WHITE) ? letter.toUpperCase(Locale.ITALIAN)
                : letter.toLowerCase(Locale.ITALIAN);
    }


    @Override
    public Optional<StringBoard> getCreatedBoard() {
        return Optional.ofNullable(this.stringBoard);
    }

}
