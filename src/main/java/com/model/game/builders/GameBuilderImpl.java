package com.model.game.builders;

import com.model.game.Game;
import com.model.game.GameImpl;
import com.model.game.controllers.GameController;
import com.model.game.types.GameType;
import com.model.movement.manager.MovementManager;

public class GameBuilderImpl implements GameBuilder {

    private GameType type;
    private GameController gameController;
    private MovementManager movementManager;
    private boolean built;

    @Override
    public GameBuilder type(final GameType type) {
        this.type = type;
        return this;
    }

    @Override
    public GameBuilder gameController(final GameController gameController) {
        this.gameController = gameController;
        return this;
    }

    @Override
    public GameBuilder movementManager(final MovementManager movementManager) {
        this.movementManager = movementManager;
        return this;
    }

    @Override
    public Game build() {
        if (this.built) {
            throw new IllegalStateException("Alredy Built");
        }
        if (this.type == null || this.gameController == null || this.movementManager == null) {
            throw new IllegalStateException("All fields must be setted");
        }
        this.built = true;
        return new GameImpl(this.type, this.gameController, this.movementManager);
    }
}
