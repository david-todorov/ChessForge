package com.model.movement.manager;

import java.util.Set;

import com.model.board.position.BoardPosition;
import com.model.game.controllers.GameController;
import com.model.movement.PieceMovement;
import com.model.movement.PieceMovementImpl;
import com.model.piece.Piece;
import com.model.piece.movements.PieceMovementStrategies;
import one.util.streamex.StreamEx;

public final class ClassicMovementHandlerStrategy implements MovementHandlerStrategy {

    private final GameController gameController;
    private final PieceMovementStrategies pieceMovementStrategies;
    private final CastlingManager castlingManager;

    public ClassicMovementHandlerStrategy(final GameController gameController) {
        this.gameController = gameController;
        this.castlingManager = new CastlingManagerImpl(this.gameController);
        this.pieceMovementStrategies = gameController.getPieceMovementStrategies();
    }

    @Override
    public boolean isMovementPossible(final PieceMovement movement) {
        return this.possibleDestinations(movement.getPieceInvolved()).contains(movement.getDestination());
    }

    @Override
    public Set<BoardPosition> possibleDestinations(final Piece piece) {
        return StreamEx
                .of(this.pieceMovementStrategies.getPieceMovementStrategy(piece)
                        .getPossibleMoves(this.gameController.getBoard()))
                .map(pos -> new PieceMovementImpl(piece, piece.getPiecePosition(), pos))
                .filter(this::arePreliminarChecksOnCastlingValid).filter(this.gameController::wouldNotBeInCheck)
                .map(PieceMovement::getDestination).toSet();
    }

    private boolean arePreliminarChecksOnCastlingValid(final PieceMovement movement) {
        return !this.castlingManager.mightItBeCastle(movement) || this.castlingManager.isCastlingFullyCorrect(movement);
    }

}
