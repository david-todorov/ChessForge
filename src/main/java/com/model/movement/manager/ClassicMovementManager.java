package com.model.movement.manager;

import java.util.Iterator;
import java.util.Set;
import java.util.stream.Stream;

import com.model.board.Board;
import com.model.board.position.BoardPosition;
import com.model.game.GameStatus;
import com.model.game.controllers.GameController;
import com.model.movement.MovementResult;
import com.model.movement.PieceMovement;
import com.model.piece.Piece;
import com.model.piece.PieceType;
import com.model.player.Player;
import com.model.player.pair.PlayerPair;

public class ClassicMovementManager implements MovementManager {

    private final Board board;
    private final GameController gameController;
    private final Iterator<Player> playerTurnIterator;
    private Player actualPlayersTurn;
    private final MovementHandlerStrategy movementHandlerStrategy;
    private final CastlingManager castlingManager;

    public ClassicMovementManager(final GameController gameController) {
        this.gameController = gameController;
        this.movementHandlerStrategy = new ClassicMovementHandlerStrategy(this.gameController);
        this.castlingManager = new CastlingManagerImpl(this.gameController);
        this.board = this.gameController.getBoard();

        this.playerTurnIterator = Stream.generate(() -> this.gameController.getPlayers()).flatMap(PlayerPair::stream)
                .iterator();

        this.actualPlayersTurn = this.playerTurnIterator.next();
    }


    @Override
    public MovementResult move(final PieceMovement movement) {
        if (this.isItThisPlayersTurn(movement) && this.movementHandlerStrategy.isMovementPossible(movement)) {
            final boolean hasCaptured = this.board.removeAtPosition(movement.getDestination());
            this.handleMovementSideEffects(movement);
            return this.resultingMovement(hasCaptured);
        }
        return MovementResult.INVALID_MOVE;
    }

    private void handleMovementSideEffects(final PieceMovement movement) {
        movement.execute();
        this.castlingManager.checkAndExecuteCastling(movement);
        this.conditionalPawnUpgrade(movement);
        this.actualPlayersTurn = this.playerTurnIterator.next();
    }

    protected final boolean isItThisPlayersTurn(final PieceMovement movement) {
        return this.getPlayerTurn().equals(movement.getPieceInvolved().getPlayer());
    }

    protected final void conditionalPawnUpgrade(final PieceMovement movement) {
        if (this.hasPawnReachedBoardUpperOrLowerBound(movement)) {
            this.upgradePawnToQueen(movement);
        }
    }

    private void upgradePawnToQueen(final PieceMovement movement) {
        this.board.remove(movement.getPieceInvolved());
        this.board.add(movement.getPieceInvolved().getPlayer().getPieceFactory().getQueen(movement.getDestination()));
    }

    private boolean hasPawnReachedBoardUpperOrLowerBound(final PieceMovement movement) {
        return movement.getPieceInvolved().getType().equals(PieceType.PAWN)
                && (movement.getDestination().getY() == 0 || movement.getDestination().getY() == board.getRows() - 1);
    }

    protected final MovementResult resultingMovement(final boolean hasCaptured) {

        final GameStatus matchStatus = this.gameController.getGameStatus(this.actualPlayersTurn);

        if (matchStatus.equals(GameStatus.CHECKMATE) || matchStatus.equals(GameStatus.DRAW)) {
            return MovementResult.OVER;
        } else if (this.gameController.isInCheck(this.actualPlayersTurn)) {
            return MovementResult.CHECKED;
        } else if (hasCaptured) {
            return MovementResult.CAPTURED;
        }
        return MovementResult.MOVED;

    }

    @Override
    public final Set<BoardPosition> filterOnPossibleMovesBasedOnGameController(final Piece piece) {
        return this.movementHandlerStrategy.possibleDestinations(piece);
    }

    @Override
    public final Player getPlayerTurn() {
        return this.actualPlayersTurn;
    }

    protected final void setActualPlayersTurn(final Player actualPlayersTurn) {
        this.actualPlayersTurn = actualPlayersTurn;
    }

    protected final MovementHandlerStrategy getMovementHandlerStrategy() {
        return movementHandlerStrategy;
    }

    protected final GameController getGameController() {
        return gameController;
    }

    protected final Iterator<Player> getPlayerTurnIterator() {
        return playerTurnIterator;
    }

}
