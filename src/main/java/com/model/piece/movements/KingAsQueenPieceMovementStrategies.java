package com.model.piece.movements;


import com.model.piece.Piece;

public class KingAsQueenPieceMovementStrategies extends ClassicNoCastlingPieceMovementStrategies {


    @Override
    protected MovementStrategy getKingMovementStrategy(final Piece piece) {
        return this.getQueenMovementStrategy(piece);
    }
}
