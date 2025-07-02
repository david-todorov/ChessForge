package com.model.piece.movements;


import com.model.piece.Piece;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RookAndBishopVariantPieceMovementStrategies extends ClassicNoCastlingPieceMovementStrategies {


    @Override
    protected MovementStrategy getRookMovementStrategy(final Piece piece) {
        return this.getBishopMovementStrategy(piece);
    }


    @Override
    protected MovementStrategy getBishopMovementStrategy(final Piece piece) {
        return (board) -> {
            return Stream.concat(super.getSpecularNoLimitDirection().apply(piece, Vectors.VERTICAL, board).stream(),
                    super.getSpecularNoLimitDirection().apply(piece, Vectors.TOP_RIGHT_BOT_LEFT, board).stream())
                    .collect(Collectors.toSet());
        };
    }
}
