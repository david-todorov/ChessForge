package com.model.piece.movements;


import com.model.piece.Piece;

public interface PieceMovementStrategies {

    MovementStrategy getPieceMovementStrategy(Piece piece);
}
