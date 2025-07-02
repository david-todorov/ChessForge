package com.views.other.board.strategy.history;

import java.util.Optional;

import com.model.board.Board;


public interface HistoryNavigationController {

    Optional<Board> getPreviousBoard();

    Optional<Board> getNextBoard();
}
