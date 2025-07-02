package com.model.game.types;

import com.model.game.Game;
import com.model.game.factory.GameFactory;
import com.model.player.pair.PlayerPair;

public interface GameGenerationStrategy {

    Game generate(GameFactory gameTypeFactory, PlayerPair players);
}
