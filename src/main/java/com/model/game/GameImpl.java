package com.model.game;

import com.model.game.controllers.GameController;
import com.model.game.types.GameType;
import com.model.movement.manager.MovementManager;

public class GameImpl implements Game {

    private final GameType type;
    private final GameController gameController;
    private final MovementManager movementManager;

    public GameImpl(final GameType type, final GameController gameController, final MovementManager movementManager) {
        this.type = type;
        this.gameController = gameController;
        this.movementManager = movementManager;
    }

    @Override
    public GameController getController() {
        return this.gameController;
    }

    @Override
    public MovementManager getMovementManager() {
        return this.movementManager;
    }

    @Override
    public GameType getType() {
        return this.type;
    }

}
