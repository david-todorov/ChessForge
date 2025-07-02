package com.model.game.controllers;

import com.model.board.Board;
import com.model.piece.PieceType;
import com.model.piece.movements.PieceMovementStrategies;
import com.model.player.pair.PlayerPair;

public final class PieceSwapVariantGameController extends ClassicGameController {

    public PieceSwapVariantGameController(final Board board, final PieceMovementStrategies pieceMovementStrategies,
                                          final PlayerPair players) {
        super(board, pieceMovementStrategies, players);
    }

    @Override
    protected boolean insufficientMaterialToWin() {
        return this.getBoard().getPieces().stream().filter(i -> !i.getType().equals(PieceType.KING)).count() == 0;

    }

}
