package com.controllers.setup;

import com.model.player.Player;
import com.model.player.PlayerColor;
import com.model.player.PlayerImpl;
import com.model.player.pair.PlayerPair;
import com.model.player.pair.PlayerPairImpl;
import com.model.user.User;

import java.util.List;
import java.util.Random;
import java.util.function.BiFunction;

public enum WhitePlayerChoice {


    FIRST_USER((first, second) -> first),

    SECOND_USER((first, second) -> second),


    RANDOM((first, second) -> List.of(first, second).get(new Random().nextInt(2)));

    private final BiFunction<User, User, User> whiteUserChooser;

    WhitePlayerChoice(final BiFunction<User, User, User> whiteUserChooser) {
        this.whiteUserChooser = whiteUserChooser;
    }


    public PlayerPair getPlayers(final User firstUser, final User secondUser) {
        final User whiteUser = this.whiteUserChooser.apply(firstUser, secondUser);
        final Player whitePlayer = new PlayerImpl(PlayerColor.WHITE, whiteUser);
        final Player blackPlayer = new PlayerImpl(PlayerColor.BLACK,
                firstUser.equals(whiteUser) ? secondUser : firstUser);
        return new PlayerPairImpl(whitePlayer, blackPlayer);
    }
}
