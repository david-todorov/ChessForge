package com.model.game.controllers;

import com.model.board.Board;
import com.model.game.GameStatus;
import com.model.movement.PieceMovement;
import com.model.movement.PieceMovementImpl;
import com.model.piece.Piece;
import com.model.piece.PieceType;
import com.model.piece.movements.PieceMovementStrategies;
import com.model.player.Player;
import com.model.player.pair.PlayerPair;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class ClassicGameController implements GameController {

    private final Board board;
    private final PieceMovementStrategies pieceMovementStrategies;
    private final PlayerPair players;

    public ClassicGameController(final Board board, final PieceMovementStrategies pieceMovementStrategies,
                                 final PlayerPair players) {
        this.board = board;
        this.pieceMovementStrategies = pieceMovementStrategies;
        this.players = players;
    }

    @Override
    public final synchronized GameStatus getGameStatus(final Player playerTurn) {
        if (this.isDraw(playerTurn)) {
            return GameStatus.DRAW;
        } else if (this.isWinner(this.players.getWhitePlayer()) || this.isWinner(this.players.getBlackPlayer())) {
            return GameStatus.CHECKMATE;
        } else if (this.isInCheck(playerTurn)) {
            return GameStatus.CHECK;
        }
        return GameStatus.ACTIVE;

    }

    private boolean isDraw(final Player playerTurn) {
        return this.insufficientMaterialToWin() || this.isBlocked(playerTurn) && !this.isInCheck(playerTurn);
    }

    protected boolean insufficientMaterialToWin() {
        final Supplier<Stream<Piece>> boardPieceStreamWithoutKings = () -> this.board.getPieces().stream()
                .filter(i -> !i.getType().equals(PieceType.KING));

        return boardPieceStreamWithoutKings.get().count() == 0
                || this.areThereLessThanOrEqualTwoNonKingPieces(boardPieceStreamWithoutKings)
                && (this.isThereOnlyOneKnight(boardPieceStreamWithoutKings)
                || this.isThereOnlyOneBishop(boardPieceStreamWithoutKings)
                || this.areThereTwoOppositeBishops(boardPieceStreamWithoutKings));
    }

    private boolean areThereTwoOppositeBishops(final Supplier<Stream<Piece>> boardStreamWithoutKings) {
        return boardStreamWithoutKings.get().allMatch(i -> i.getType().equals(PieceType.BISHOP))
                && boardStreamWithoutKings.get().filter(i -> i.getType().equals(PieceType.BISHOP)).map(Piece::getPlayer)
                .distinct().count() == 2;
    }

    private boolean isThereOnlyOneKnight(final Supplier<Stream<Piece>> boardStreamWithoutKings) {
        return boardStreamWithoutKings.get().count() == 1
                && boardStreamWithoutKings.get().allMatch(i -> i.getType().equals(PieceType.KNIGHT));
    }

    private boolean isThereOnlyOneBishop(final Supplier<Stream<Piece>> boardStreamWithoutKings) {
        return boardStreamWithoutKings.get().count() == 1
                && boardStreamWithoutKings.get().allMatch(i -> i.getType().equals(PieceType.BISHOP));
    }

    private boolean areThereLessThanOrEqualTwoNonKingPieces(final Supplier<Stream<Piece>> boardStreamWithoutKings) {
        return boardStreamWithoutKings.get().count() <= 2;
    }

    @Override
    public final boolean isInCheck(final Player player) {
        final Optional<Piece> king = this.board.getPieces().stream()
                .filter(i -> i.getPlayer().equals(player) && i.getType().equals(PieceType.KING)).findAny();

        return king.isPresent()
                && this.board.getPieces().stream().filter(i -> !i.getPlayer().equals(player))
                .filter(piece -> this.pieceMovementStrategies.getPieceMovementStrategy(piece)
                        .getPossibleMoves(this.board).contains(king.get().getPiecePosition()))
                .findAny().isPresent();
    }

    private boolean isLoser(final Player player) {
        return this.isInCheck(player) && this.isBlocked(player);
    }

    @Override
    public final boolean isWinner(final Player player) {
        return this.players.getWhitePlayer().equals(player) ? this.isLoser(this.players.getBlackPlayer())
                : this.isLoser(this.players.getWhitePlayer());
    }

    private boolean isBlocked(final Player player) {
        final Set<Piece> supportBoard = new HashSet<>(this.board.getPieces());

        return supportBoard.stream().filter(i -> i.getPlayer().equals(player))
                .filter(pieceToCheck -> this.pieceMovementStrategies.getPieceMovementStrategy(pieceToCheck)
                        .getPossibleMoves(this.board).stream().map(dest -> new PieceMovementImpl(pieceToCheck, dest))
                        .filter(this::wouldNotBeInCheck).findAny().isPresent())
                .findAny().isEmpty();

    }

    @Override
    public final boolean wouldNotBeInCheck(final PieceMovement movement) {
        final Optional<Piece> oldPiece = this.board.getPieceAtPosition(movement.getDestination());
        oldPiece.ifPresent(this.board::remove);

        movement.getPieceInvolved().setPosition(movement.getDestination());
        final boolean result = !this.isInCheck(movement.getPieceInvolved().getPlayer());
        movement.getPieceInvolved().setPosition(movement.getOrigin());

        oldPiece.ifPresent(this.board::add);
        return result;
    }

    @Override
    public final Board getBoard() {
        return this.board;
    }

    @Override
    public final PlayerPair getPlayers() {
        return this.players;
    }

    @Override
    public final PieceMovementStrategies getPieceMovementStrategies() {
        return this.pieceMovementStrategies;
    }

}
