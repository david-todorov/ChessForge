package com.model.piece.movements;

import com.model.board.position.BoardPosition;
import com.model.piece.Piece;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class ClassicNoCastlingPieceMovementStrategies extends ClassicWithCastlingPieceMovementStrategies {

    @Override
    protected MovementStrategy getKingMovementStrategy(final Piece piece) {
        return (board) -> {
            final Set<BoardPosition> positions = new HashSet<>();
            positions.addAll(this.getQueenMovementStrategy(piece).getPossibleMoves(board).stream()
                    .filter(pos -> super.pieceDistanceFromPositionLessThan(piece, pos, SINGLE_INCREMENT))
                    .collect(Collectors.toSet()));
            return Collections.unmodifiableSet(positions);
        };
    }
}
