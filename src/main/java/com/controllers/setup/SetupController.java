package com.controllers.setup;

import com.controllers.Controller;
import com.model.game.types.GameType;
import com.model.timer.DefaultTimers;

import java.util.Optional;

public interface SetupController extends Controller {


    void setGameType(GameType gameType);


    void setTimer(DefaultTimers timer);


    void setWhitePlayerChoice(WhitePlayerChoice choice);


    Optional<GameType> getSelectedGameType();


    Optional<DefaultTimers> getSelectedTimer();


    Optional<WhitePlayerChoice> getSelectedWhitePlayerChoice();


    boolean createMatch();
}
