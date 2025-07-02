package com.model.board;

import com.model.board.position.BoardPosition;
import com.model.piece.Piece;

import java.io.Serializable;
import java.util.Optional;
import java.util.Set;

public interface Board extends Serializable {

    Set<Piece> getPieces();

    Optional<Piece> getPieceAtPosition(BoardPosition boardPosition);

    boolean contains(BoardPosition positionToCheck);

    boolean contains(Piece pieceToCheck);

    boolean removeAtPosition(BoardPosition positionToRemove);

    boolean remove(Piece pieceToRemove);

    boolean add(Piece pieceToAdd);

    int getColumns();

    int getRows();
}
