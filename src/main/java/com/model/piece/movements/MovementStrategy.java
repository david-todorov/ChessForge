package com.model.piece.movements;


import com.model.board.Board;
import com.model.board.position.BoardPosition;

import java.util.Set;

@FunctionalInterface
public interface MovementStrategy {

    Set<BoardPosition> getPossibleMoves(Board actualBoard);
}
