package com.model.movement.manager;

import java.util.List;
import java.util.ListIterator;
import java.util.Set;


import com.model.board.position.BoardPosition;
import com.model.game.controllers.GameController;
import com.model.movement.BasicMovement;
import com.model.movement.PieceMovement;
import com.model.movement.MovementResult;
import com.model.piece.Piece;
import com.model.player.Player;

public final class ChessProblemsMovementManager implements MovementManager {

    private final ClassicMovementManager classicMovementManager;
    private final ListIterator<BasicMovement> problemCorrectMovesIterator;

    public ChessProblemsMovementManager(final GameController gameController,
            final List<BasicMovement> problemCorrectMoves) {
        this.classicMovementManager = new ClassicMovementManager(gameController);
        this.problemCorrectMovesIterator = problemCorrectMoves.listIterator();
    }

    @Override
    public MovementResult move(final PieceMovement movement) {
        if (this.problemCorrectMovesIterator.hasNext() && this.isMovementCorrectBasedOnProblemSequence(movement)) {
            final MovementResult movementResult = this.classicMovementManager.move(movement);
            this.executeOpponentNextMoveIfPresent();
            return movementResult;
        }
        return MovementResult.INVALID_MOVE;
    }

    private boolean isMovementCorrectBasedOnProblemSequence(final PieceMovement movement) {
        final BasicMovement moveToWhichCompareUsersMovement = this.problemCorrectMovesIterator.next();
        if (movement.getDestination().equals(moveToWhichCompareUsersMovement.getDestination())
                && movement.getOrigin().equals(moveToWhichCompareUsersMovement.getOrigin())) {
            return true;
        }
        this.problemCorrectMovesIterator.previous();
        return false;
    }

    private void executeOpponentNextMoveIfPresent() {
        if (this.problemCorrectMovesIterator.hasNext()) {
            final BasicMovement blackMovement = this.problemCorrectMovesIterator.next();
            final Piece pieceToMove = this.classicMovementManager.getGameController().getBoard()
                    .getPieceAtPosition(blackMovement.getOrigin()).get();
            this.classicMovementManager.getGameController().getBoard()
                    .removeAtPosition(blackMovement.getDestination());
            pieceToMove.setPosition(blackMovement.getDestination());
            this.classicMovementManager
                    .setActualPlayersTurn(this.classicMovementManager.getPlayerTurnIterator().next());
        }
    }

    @Override
    public Player getPlayerTurn() {
        return this.classicMovementManager.getPlayerTurn();
    }

    @Override
    public Set<BoardPosition> filterOnPossibleMovesBasedOnGameController(final Piece piece) {
        return this.classicMovementManager.filterOnPossibleMovesBasedOnGameController(piece);
    }

}
