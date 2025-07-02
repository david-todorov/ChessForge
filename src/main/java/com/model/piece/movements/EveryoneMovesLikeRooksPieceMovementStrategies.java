package com.model.piece.movements;


import com.model.piece.Piece;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class EveryoneMovesLikeRooksPieceMovementStrategies extends AbstractPieceMovementStrategies {


    @Override
    protected MovementStrategy getPawnMovementStrategy(final Piece piece) {
        return this.getRookMovementStrategy(piece);
    }

    @Override
    protected MovementStrategy getRookMovementStrategy(final Piece piece) {
        return (board) -> {
            return Stream
                    .concat(super.getSpecularNoLimitDirection().apply(piece, Vectors.VERTICAL, board).stream(),
                            super.getSpecularNoLimitDirection().apply(piece, Vectors.HORIZONTAL, board).stream())
                    .collect(Collectors.toSet());
        };
    }

    @Override
    protected MovementStrategy getKnightMovementStrategy(final Piece piece) {
        return this.getRookMovementStrategy(piece);
    }

    @Override
    protected MovementStrategy getBishopMovementStrategy(final Piece piece) {
        return this.getRookMovementStrategy(piece);
    }

    @Override
    protected MovementStrategy getQueenMovementStrategy(final Piece piece) {
        return this.getRookMovementStrategy(piece);
    }

    @Override
    protected MovementStrategy getKingMovementStrategy(final Piece piece) {
        return this.getRookMovementStrategy(piece);
    }

}
