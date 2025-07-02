package com.model.movement.manager;

import java.util.Set;


import com.model.board.position.BoardPosition;
import com.model.movement.PieceMovement;
import com.model.movement.MovementResult;
import com.model.piece.Piece;
import com.model.player.Player;

public interface MovementManager {


    MovementResult move(PieceMovement movement);


    Player getPlayerTurn();


    Set<BoardPosition> filterOnPossibleMovesBasedOnGameController(Piece piece);

}
