package com.model.piece.movements;


import com.model.board.position.BoardPosition;
import com.model.board.position.BoardPositionImpl;
import com.model.piece.Piece;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class OneDimensionPieceMovementStrategies extends ClassicNoCastlingPieceMovementStrategies {


    @Override
    protected MovementStrategy getKnightMovementStrategy(final Piece piece) {
        return (board) -> {
            final Set<BoardPosition> positions = new HashSet<>();
            Set.of(DOUBLE_INCREMENT, -DOUBLE_INCREMENT).forEach(y -> {
                positions.addAll(super.getDestinationsFromFunction(
                        pos -> new BoardPositionImpl(pos.getX(), pos.getY() + y), piece, board, SINGLE_INCREMENT));
            });
            return Collections.unmodifiableSet(positions);
        };
    }
}
