package com.model.history;

import com.model.board.Board;

import java.util.List;

public interface History {

    void updateHistory();

    void updateWithNewHistory(List<Board> boardHistory);

    Board getBoardAtIndex(int index);

    List<Board> getAllBoards();

}