package com.model.piece.movements;


import com.model.board.position.BoardPosition;
import com.model.piece.Piece;
import com.model.player.PlayerColor;

import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class PawnVariantPieceMovementStrategies extends ClassicWithCastlingPieceMovementStrategies {

    @Override
    protected MovementStrategy getPawnMovementStrategy(final Piece piece) {
        return (board) -> {
            final int increment = piece.getPlayer().getColor().equals(PlayerColor.WHITE) ? SINGLE_INCREMENT
                    : -SINGLE_INCREMENT;

            final Predicate<BoardPosition> checkDirectionAndDistance = (
                    pos) -> Math.signum((pos.getY() - piece.getPiecePosition().getY()) * increment) >= 0
                            && super.pieceDistanceFromPositionLessThan(piece, pos, SINGLE_INCREMENT);

            return Stream.concat(
                    super.getRookMovementStrategy(piece).getPossibleMoves(board).stream()
                            .filter(checkDirectionAndDistance),
                    super.getBishopMovementStrategy(piece).getPossibleMoves(board).stream()
                            .filter(checkDirectionAndDistance))
                    .collect(Collectors.toSet());
        };
    }
}
