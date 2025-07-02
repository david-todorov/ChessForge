package com.controllers.match;

import com.controllers.BasicController;
import com.model.board.Board;
import com.model.board.position.BoardPosition;
import com.model.game.types.GameType;
import com.model.match.MatchEndType;
import com.model.match.MatchStatus;
import com.model.movement.MovementResult;
import com.model.movement.PieceMovementImpl;
import com.model.piece.Piece;
import com.model.player.Player;
import com.model.player.pair.PlayerPair;
import com.model.replay.ReplayBuilder;
import com.model.replay.ReplayData;
import com.model.replay.SavedReplay;
import com.model.replay.SavedReplayImpl;
import com.model.timer.Timer;
import com.model.user.management.UsersManagerSingleton;

import java.io.IOException;
import java.util.Date;
import java.util.Optional;
import java.util.Set;

public final class MatchControllerImpl extends BasicController implements MatchController {

    private int index;


    @Override
    public MovementResult move(final BoardPosition origin, final BoardPosition destination) {

        if (this.getBoard().getPieceAtPosition(origin).isPresent()) {
            final Piece piece = this.getBoard().getPieceAtPosition(origin).get();
            final MovementResult result = this.getModel().getMatch().get()
                    .move(new PieceMovementImpl(piece, origin, destination));
            if (!result.equals(MovementResult.INVALID_MOVE)) {
                this.index = this.getModel().getMatch().get().getHistory().getAllBoards().size() - 1;
            }
            return result;
        }
        return MovementResult.INVALID_MOVE;
    }


    @Override
    public Board getBoard() {
        return this.getModel().getMatch().get().getBoard();
    }


    @Override
    public Optional<Board> getPreviousBoard() {
        return this.index > 0
                ? Optional.of(this.getModel().getMatch().get().getHistory().getBoardAtIndex(--this.index))
                : Optional.empty();
    }


    @Override
    public Optional<Board> getNextBoard() {
        return this.index < this.getModel().getMatch().get().getHistory().getAllBoards().size() - 1
                ? Optional.of(this.getModel().getMatch().get().getHistory().getBoardAtIndex(++this.index))
                : Optional.empty();
    }



    private void savePlayers() throws IOException {
        this.getModel().getMatch().ifPresent(m -> {
            if (m.getMatchStatus().equals(MatchStatus.ENDED)) {
                if (m.getEndType().get().equals(MatchEndType.CHECKMATE)
                        || m.getEndType().get().equals(MatchEndType.TIMEOUT)
                        || m.getEndType().get().equals(MatchEndType.RESIGN)) {
                    m.getWinner().ifPresent(winner -> {
                        winner.getUser().increaseWinCount();
                        this.getPlayers().stream().filter(loser -> !loser.equals(winner)).findAny()
                                .ifPresent(p -> p.getUser().increaseLostCount());
                    });
                } else if (m.getEndType().get().equals(MatchEndType.DRAW)) {
                    this.getPlayers().getWhitePlayer().getUser().increaseDrawCount();
                    this.getPlayers().getBlackPlayer().getUser().increaseDrawCount();
                }
            }
        });
        UsersManagerSingleton.getInstance().put(this.getPlayers().getWhitePlayer().getUser());
        UsersManagerSingleton.getInstance().put(this.getPlayers().getBlackPlayer().getUser());
    }


    @Override
    public boolean isInNavigationMode() {
        return this.index != this.getModel().getMatch().get().getHistory().getAllBoards().size() - 1;
    }


    @Override
    public void start() {
        this.getModel().getMatch().get().start();
    }


    @Override
    public double getWhiteRemainingTime() {
        return this.getModel().getMatch().get().getTimer().getRemaningTime(this.getWhitePlayer());
    }


    @Override
    public double getBlackRemainingTime() {
        return this.getModel().getMatch().get().getTimer().getRemaningTime(this.getBlackPlayer());
    }


    @Override
    public MatchStatus getStatus() {
        return this.getModel().getMatch().get().getMatchStatus();
    }


    @Override
    public Set<BoardPosition> getPiecePossibleMoves(final Piece piece) {
        return this.getModel().getMatch().get().getPiecePossibleMoves(piece);
    }


    @Override
    public Player getPlayerTurn() {
        return this.getModel().getMatch().get().getGame().getMovementManager().getPlayerTurn();
    }


    @Override
    public void deleteMatch() {
        this.getTimer().stop();
        this.getModel().deleteMatch();
    }


    @Override
    public PlayerPair getPlayers() {
        return this.getModel().getMatch().get().getPlayers();
    }


    @Override
    public Timer getTimer() {
        return this.getModel().getMatch().get().getTimer();
    }


    @Override
    public Player getWhitePlayer() {
        return this.getPlayers().getWhitePlayer();
    }


    @Override
    public Player getBlackPlayer() {
        return this.getPlayers().getBlackPlayer();
    }


    @Override
    public void stopTimer() {
        this.getModel().getMatch().get().getTimer().stop();
    }


    @Override
    public boolean isMatchPresent() {
        return this.getModel().getMatch().isPresent();
    }


    @Override
    public Optional<Player> getWinner() {
        return this.getModel().getMatch().get().getWinner();
    }


    @Override
    public Optional<MatchEndType> getEndType() {
        return this.getModel().getMatch().get().getEndType();
    }


    @Override
    public void resign(final Player player) {
        this.getModel().getMatch().get().resign(player);
    }

    @Override
    public void saveMatch() throws IOException {
        if (this.getModel().getMatch().isPresent()
                && !this.getModel().getMatch().get().getGame().getType().equals(GameType.CHESS_PROBLEM)) {

            final ReplayData matchSaved = new ReplayBuilder().date(new Date())
                    .matchID(this.getModel().getMatch().get().getMatchID())
                    .whiteUser(this.getModel().getFirstUser().get())
                    .blackUser(this.getModel().getSecondUser().get())
                    .boards(this.getModel().getMatch().get().getHistory().getAllBoards())
                    .gameType(this.getModel().getMatch().get().getGame().getType()).build();

            final SavedReplay replay = new SavedReplayImpl();
            replay.save(matchSaved);

            this.savePlayers();

        }
    }

}
