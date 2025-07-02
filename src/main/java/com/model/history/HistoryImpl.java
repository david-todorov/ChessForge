package com.model.history;

import com.model.board.Board;
import com.model.board.builders.BoardBuilder;
import com.model.board.builders.BoardBuilderImpl;
import com.model.board.position.BoardPositionImpl;
import com.model.piece.PieceImpl;

import java.util.ArrayList;
import java.util.List;

public final class HistoryImpl implements History {

    private final List<Board> status = new ArrayList<>();
    private final Board actualBoardStatus;

    public HistoryImpl(final Board board) {
        this.actualBoardStatus = board;
        this.addCloneBoardToHistory(this.actualBoardStatus);
    }

    private Board cloneBoard(final Board board) {
        final BoardBuilder boardBuilder = new BoardBuilderImpl();

        board.getPieces().stream()
                .map(x -> new PieceImpl(x.getType(), new BoardPositionImpl(x.getPiecePosition()), x.getPlayer()))
                .forEach(boardBuilder::addPiece);

        return boardBuilder.rows(board.getRows()).columns(board.getColumns()).build();
    }

    @Override
    public void updateHistory() {
        this.addCloneBoardToHistory(this.actualBoardStatus);
    }

    @Override
    public Board getBoardAtIndex(final int index) {
        return this.status.get(index);
    }

    @Override
    public List<Board> getAllBoards() {
        return this.status;
    }

    private void addCloneBoardToHistory(final Board toCloneBoard) {
        this.status.add(this.cloneBoard(toCloneBoard));
    }

    @Override
    public void updateWithNewHistory(final List<Board> boardHistory) {
        this.status.clear();
        boardHistory.forEach(x -> {
            this.addCloneBoardToHistory(x);
        });
    }

}
