package com.views.other.board;

import java.util.Optional;

import com.controllers.match.MatchController;
import com.model.board.Board;
import com.model.board.position.BoardPosition;
import com.model.board.position.BoardPositionImpl;
import com.views.other.board.strategy.history.HistoryNavigationController;
import com.views.other.board.strategy.history.NormalHistoryKeyHandlerStrategy;
import com.views.other.board.strategy.movement.OnlineMatchPieceMovementStrategy;
import javafx.application.Platform;


public final class OnlineMatchBoard extends MatchBoard {

    private final MatchController controller;
    private final boolean isWhite;

    public OnlineMatchBoard(final MatchController controller, final Runnable onMatchFinish, final boolean isWhite) {
        super(controller, onMatchFinish);
        this.isWhite = isWhite;
        this.controller = controller;
        this.setGraphicPieceMovementStrategy(new OnlineMatchPieceMovementStrategy(this, this.controller, isWhite));
        this.setHistoryKeyHandlerStrategy(new NormalHistoryKeyHandlerStrategy(this, new HistoryNavigationController() {
            @Override
            public Optional<Board> getPreviousBoard() {
                return controller.getPreviousBoard();
            }

            @Override
            public Optional<Board> getNextBoard() {
                return controller.getNextBoard();
            }
        }));
        this.createBoard();
        this.redraw(this.controller.getBoard());
        Platform.runLater(() -> this.getGrid().requestFocus());
    }


    @Override
    protected BoardPosition getGridPositionFromBoardPosition(final BoardPosition position) {
        final BoardPosition pos = super.getGridPositionFromBoardPosition(position);
        return this.isWhite ? pos
                : new BoardPositionImpl(pos.getX(), this.controller.getBoard().getRows() - 1 - pos.getY());
    }

}
