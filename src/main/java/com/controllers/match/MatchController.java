package com.controllers.match;

import com.controllers.Controller;
import com.model.board.Board;
import com.model.board.position.BoardPosition;
import com.model.match.MatchEndType;
import com.model.match.MatchStatus;
import com.model.movement.MovementResult;
import com.model.piece.Piece;
import com.model.player.Player;
import com.model.player.pair.PlayerPair;
import com.model.timer.Timer;

import java.io.IOException;
import java.util.Optional;
import java.util.Set;

public interface MatchController extends Controller {


    MovementResult move(BoardPosition origin, BoardPosition destination);


    Player getWhitePlayer();


    Player getBlackPlayer();


    Optional<Player> getWinner();


    PlayerPair getPlayers();


    Timer getTimer();


    Board getBoard();


    Player getPlayerTurn();


    Set<BoardPosition> getPiecePossibleMoves(Piece piece);


    boolean isInNavigationMode();

    double getWhiteRemainingTime();


    double getBlackRemainingTime();


    void start();


    void stopTimer();


    MatchStatus getStatus();


    Optional<MatchEndType> getEndType();


    void saveMatch() throws IOException;

    void deleteMatch();


    boolean isMatchPresent();


    void resign(Player player);


    Optional<Board> getPreviousBoard();


    Optional<Board> getNextBoard();
}
