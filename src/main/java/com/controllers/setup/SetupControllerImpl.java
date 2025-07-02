package com.controllers.setup;

import com.controllers.BasicController;
import com.model.game.types.GameType;
import com.model.match.Match;
import com.model.match.MatchImpl;
import com.model.player.pair.PlayerPair;
import com.model.timer.DefaultTimers;

import java.util.Optional;

public final class SetupControllerImpl extends BasicController implements SetupController {

    private GameType gameType;
    private DefaultTimers timer;
    private WhitePlayerChoice choice;


    @Override
    public void setGameType(final GameType gameType) {
        this.gameType = gameType;
    }


    @Override
    public void setTimer(final DefaultTimers timer) {
        this.timer = timer;
    }


    @Override
    public void setWhitePlayerChoice(final WhitePlayerChoice choice) {
        this.choice = choice;
    }


    @Override
    public Optional<GameType> getSelectedGameType() {
        return Optional.ofNullable(this.gameType);
    }


    @Override
    public Optional<DefaultTimers> getSelectedTimer() {
        return Optional.ofNullable(this.timer);
    }

    @Override
    public Optional<WhitePlayerChoice> getSelectedWhitePlayerChoice() {
        return Optional.ofNullable(this.choice);
    }


    @Override
    public boolean createMatch() {
        if (this.gameType == null || this.timer == null || this.choice == null) {
            return false;
        }
        final PlayerPair players = this.choice.getPlayers(this.getModel().getFirstUser().get(),
                this.getModel().getSecondUser().get());

        final Match match = new MatchImpl(this.gameType.getGameInstance(players), this.timer.getTimer(players));

        this.getModel().setMatch(match);
        return true;
    }

}
