package com.views.other.board;

import java.util.Optional;

import com.controllers.replay.ReplayController;
import com.model.board.Board;
import com.views.other.board.strategy.history.HistoryNavigationController;
import com.views.other.board.strategy.history.NormalHistoryKeyHandlerStrategy;
import com.views.other.board.strategy.movement.NonMovableGraphicPieceMovementStrategy;
import javafx.application.Platform;


public final class ReplayBoard extends GraphicalBoard {

    public ReplayBoard(final ReplayController replayController) {
        super(replayController.getFirstBoard().getRows(), replayController.getFirstBoard().getColumns(),
                new NonMovableGraphicPieceMovementStrategy());
        this.setHistoryKeyHandlerStrategy(new NormalHistoryKeyHandlerStrategy(this, new HistoryNavigationController() {
            @Override
            public Optional<Board> getPreviousBoard() {
                return replayController.getPreviousBoard();
            }

            @Override
            public Optional<Board> getNextBoard() {
                return replayController.getNextBoard();
            }
        }));
        this.createBoard();
        this.redraw(replayController.getFirstBoard());
        Platform.runLater(() -> this.getGrid().requestFocus());
    }
}
