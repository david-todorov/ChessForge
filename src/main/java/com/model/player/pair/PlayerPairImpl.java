package com.model.player.pair;

import com.model.player.Player;
import com.model.player.PlayerColor;

import java.util.stream.Stream;

public class PlayerPairImpl implements PlayerPair {

    private final Player whitePlayer;
    private final Player blackPlayer;

    public PlayerPairImpl(final Player whitePlayer, final Player blackPlayer) {
        if (!whitePlayer.getColor().equals(PlayerColor.WHITE)) {
            throw new IllegalArgumentException("White Player can't be black");
        }
        if (!blackPlayer.getColor().equals(PlayerColor.BLACK)) {
            throw new IllegalArgumentException("Black Player can't be white");
        }
        this.whitePlayer = whitePlayer;
        this.blackPlayer = blackPlayer;
    }

    @Override
    public Player getWhitePlayer() {
        return whitePlayer;
    }

    @Override
    public Player getBlackPlayer() {
        return blackPlayer;
    }

    @Override
    public Stream<Player> stream() {
        return Stream.of(this.whitePlayer, this.blackPlayer);
    }

}
