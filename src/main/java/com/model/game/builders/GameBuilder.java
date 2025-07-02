package com.model.game.builders;

import com.model.game.Game;
import com.model.game.controllers.GameController;
import com.model.game.types.GameType;
import com.model.movement.manager.MovementManager;

public interface GameBuilder {


    GameBuilder type(GameType type);

    GameBuilder gameController(GameController gameController);

    GameBuilder movementManager(MovementManager movementManager);

    Game build();
}
