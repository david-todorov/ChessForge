package com.model.movement.manager;

import java.util.Set;


import com.model.board.position.BoardPosition;
import com.model.movement.PieceMovement;
import com.model.piece.Piece;

public interface MovementHandlerStrategy {

    boolean isMovementPossible(PieceMovement movement);


    Set<BoardPosition> possibleDestinations(Piece piece);
}
