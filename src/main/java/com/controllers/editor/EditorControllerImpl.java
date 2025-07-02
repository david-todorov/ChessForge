package com.controllers.editor;

import com.controllers.BasicController;
import com.controllers.setup.SetupController;
import com.controllers.setup.SetupControllerImpl;
import com.controllers.setup.WhitePlayerChoice;
import com.model.Model;
import com.model.board.Board;
import com.model.board.position.BoardPosition;
import com.model.editor.Editor;
import com.model.editor.EditorImpl;
import com.model.game.factory.GameFactoryImpl;
import com.model.game.types.GameType;
import com.model.match.Match;
import com.model.match.MatchImpl;
import com.model.piece.Piece;
import com.model.player.pair.PlayerPair;
import com.model.timer.DefaultTimers;

import java.util.Optional;

public final class EditorControllerImpl extends BasicController implements EditorController {

    private final Editor editor = new EditorImpl();
    private final SetupController setupController = new SetupControllerImpl();


    @Override
    public void setModel(final Model applicationInstance) {
        this.setupController.setModel(applicationInstance);
        super.setModel(applicationInstance);
    }


    @Override
    public void setTimer(final DefaultTimers timer) {
        this.setupController.setTimer(timer);
    }


    @Override
    public void setGameType(final GameType gameType) {
        this.setupController.setGameType(gameType);
    }


    @Override
    public void setWhitePlayerChoice(final WhitePlayerChoice choice) {
        this.setupController.setWhitePlayerChoice(choice);
    }


    @Override
    public Optional<GameType> getSelectedGameType() {
        return this.setupController.getSelectedGameType();
    }


    @Override
    public Optional<DefaultTimers> getSelectedTimer() {
        return this.setupController.getSelectedTimer();
    }


    @Override
    public Optional<WhitePlayerChoice> getSelectedWhitePlayerChoice() {
        return this.setupController.getSelectedWhitePlayerChoice();
    }


    @Override
    public void addPieceToBoard(final Piece piece) {
        this.editor.addPieceToBoard(piece);
    }


    @Override
    public Board getBoardStatus() {
        return this.editor.getBoardStatus();
    }


    @Override
    public void resetBoard(final int columns, final int rows) {
        this.editor.changeBoardDimensions(columns, rows);
    }


    @Override
    public boolean updatePiecePosition(final Piece piece, final BoardPosition position) {
        return this.editor.changePiecePosition(piece, position);
    }


    @Override
    public void removePieceAtPosition(final BoardPosition position) {
        this.editor.removePiece(position);
    }


    @Override
    public void createCustomizedStartingBoard() {
        this.editor.createStartingBoard();
    }


    @Override
    public boolean createMatch() {

        if (this.getSelectedTimer().isEmpty() || this.getSelectedGameType().isEmpty()
                || this.editor.getCreatedBoard().isEmpty()
                || this.editor.getCreatedBoard().get().getBoard().isBlank()) {
            return false;
        }

        final PlayerPair players = this.getSelectedWhitePlayerChoice().get().getPlayers(
                this.getModel().getFirstUser().get(),
                this.getModel().getSecondUser().get());

        final Match match = new MatchImpl(
                new GameFactoryImpl().customizedBoardVariantGame(players, this.editor.getCreatedBoard().get()),
                this.getSelectedTimer().get().getTimer(players));

        this.getModel().setMatch(match);

        return true;
    }

}
