package com.model.game.controllers;

import com.model.board.Board;
import com.model.game.GameStatus;
import com.model.movement.PieceMovement;
import com.model.piece.movements.PieceMovementStrategies;
import com.model.player.Player;
import com.model.player.pair.PlayerPair;

public interface GameController {

    GameStatus getGameStatus(Player playerTurn);

    boolean isInCheck(Player player);

    boolean wouldNotBeInCheck(PieceMovement movement);

    boolean isWinner(Player player);

    Board getBoard();

    PlayerPair getPlayers();

    PieceMovementStrategies getPieceMovementStrategies();
}
