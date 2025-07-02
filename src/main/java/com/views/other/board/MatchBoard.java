package com.views.other.board;

import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;

import com.controllers.match.MatchController;
import com.model.board.Board;
import com.model.board.position.BoardPosition;
import com.model.match.MatchStatus;
import com.model.movement.MovementResult;
import com.model.movement.PieceMovement;
import com.model.piece.Piece;
import com.other.settings.media.sound.Sound;
import com.other.settings.media.sound.SoundsEnum;
import com.views.other.board.strategy.history.HistoryNavigationController;
import com.views.other.board.strategy.history.NormalHistoryKeyHandlerStrategy;
import com.views.other.board.strategy.movement.NormalMatchPieceMovementStrategy;
import com.views.other.component.Tile;
import javafx.application.Platform;


public class MatchBoard extends GraphicalBoard {

    private final Runnable onMatchFinish;
    private final MatchController matchController;

    public MatchBoard(final MatchController matchController, final Runnable onMatchFinish) {
        super(matchController.getBoard().getRows(), matchController.getBoard().getColumns());

        this.matchController = matchController;
        this.onMatchFinish = onMatchFinish;
    }


    public final void setup() {
        this.setGraphicPieceMovementStrategy(new NormalMatchPieceMovementStrategy(this, this.matchController));
        this.setHistoryKeyHandlerStrategy(new NormalHistoryKeyHandlerStrategy(this, new HistoryNavigationController() {
            @Override
            public Optional<Board> getPreviousBoard() {
                return matchController.getPreviousBoard();
            }

            @Override
            public Optional<Board> getNextBoard() {
                return matchController.getNextBoard();
            }
        }));
        this.createBoard();
        this.redraw(this.matchController.getBoard());
        Platform.runLater(() -> this.getGrid().requestFocus());
    }

    public final void onMovement(final Board newBoard, final PieceMovement movement,
            final MovementResult movementResult) {
        this.resetHightlightedPositions();
        this.redraw(newBoard);
        this.highlightMovement(movement);
        Sound.play(SoundsEnum.fromMovementResult(movementResult));
        this.checkMatchStatus();
    }


    public final void highlightMovement(final PieceMovement movement) {
        this.resetHighlightedMovements();
        final Predicate<Tile> isPositionInvoledInMovement = (
                tile) -> tile.getBoardPosition().equals(movement.getOrigin())
                        || tile.getBoardPosition().equals(movement.getDestination());
        this.getTiles().stream().filter(isPositionInvoledInMovement).forEach(Tile::highlightMovement);
    }


    public final void resetHighlightedMovements() {
        this.getTiles().stream().forEach(Tile::resetHighlightMovement);
    }


    public final void hightlightPositons(final Set<BoardPosition> positions) {
        this.getTiles().stream().filter(x -> positions.contains(x.getBoardPosition())).forEach(x -> x.highlightPosition(
                this.matchController.getBoard().getPieceAtPosition(x.getBoardPosition()).isPresent()));
    }


    public final void resetHightlightedPositions() {
        this.getTiles().forEach(Tile::resetHighlightPosition);
    }


    public final void drawPossibleDestinations(final Piece piece) {
        this.resetHightlightedPositions();
        this.hightlightPositons(this.matchController.getPiecePossibleMoves(piece));

    }

    private void checkMatchStatus() {
        if (this.matchController.getStatus().equals(MatchStatus.ENDED)) {
            this.onMatchFinish.run();
        }
    }

}
