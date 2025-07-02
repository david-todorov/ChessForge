package com.model.game;

import com.model.game.builders.GameBuilder;
import com.model.game.builders.GameBuilderImpl;
import com.model.game.controllers.GameController;
import com.model.game.types.GameType;
import com.model.movement.manager.MovementManager;

public interface Game {

    GameType getType();

    GameController getController();

    MovementManager getMovementManager();

    static GameBuilder builder() {
        return new GameBuilderImpl();
    }
}
