package com.model.match;

import com.model.board.Board;
import com.model.board.position.BoardPosition;
import com.model.game.Game;
import com.model.history.History;
import com.model.movement.MovementResult;
import com.model.movement.PieceMovement;
import com.model.piece.Piece;
import com.model.player.Player;
import com.model.player.pair.PlayerPair;
import com.model.timer.Timer;

import java.util.Optional;
import java.util.Set;

public interface Match {

    String getMatchID();

    PlayerPair getPlayers();

    Game getGame();

    Timer getTimer();

    History getHistory();


    void start();

    MovementResult move(PieceMovement movement);

    MatchStatus getMatchStatus();


    Optional<MatchEndType> getEndType();

    Optional<Player> getWinner();

    Board getBoard();

    Set<BoardPosition> getPiecePossibleMoves(Piece piece);

    void resign(Player player);

}
