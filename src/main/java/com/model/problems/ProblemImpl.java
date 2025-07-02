package com.model.problems;

import com.model.board.Board;
import com.model.movement.BasicMovement;

import java.util.List;


public final class ProblemImpl implements Problem {

    private final List<BasicMovement> correctMoves;
    private final Board problemStartingBoard;

    public ProblemImpl(final List<BasicMovement> correctMoves, final Board problemStartingBoard) {
        this.problemStartingBoard = problemStartingBoard;
        this.correctMoves = correctMoves;
    }

    @Override
    public List<BasicMovement> getCorrectMoves() {
        return this.correctMoves;
    }

    @Override
    public Board getStartingBoard() {
        return this.problemStartingBoard;
    }

}
