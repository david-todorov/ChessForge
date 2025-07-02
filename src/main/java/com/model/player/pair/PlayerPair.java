package com.model.player.pair;

import com.model.player.Player;

import java.util.stream.Stream;

public interface PlayerPair {

    Player getWhitePlayer();

    Player getBlackPlayer();

    Stream<Player> stream();

}
