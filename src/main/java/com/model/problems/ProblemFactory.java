package com.model.problems;

import com.model.player.pair.PlayerPair;

public interface ProblemFactory {

    Problem problemOne(PlayerPair players);

    Problem problemTwo(PlayerPair players);

    Problem problemThree(PlayerPair players);

    Problem problemFour(PlayerPair players);

    Problem problemFive(PlayerPair players);
}
