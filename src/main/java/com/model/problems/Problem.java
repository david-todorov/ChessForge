package com.model.problems;

import java.util.List;

import com.model.board.Board;
import com.model.movement.BasicMovement;

public interface Problem {

    List<BasicMovement> getCorrectMoves();


    Board getStartingBoard();
}
