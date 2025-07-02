package com.model.problems;

import java.util.function.Function;

import com.model.player.pair.PlayerPair;

public enum Problems {

    PROBLEM_ONE("Problem 1", new ProblemFactoryImpl()::problemOne),


    PROBLEM_TWO("Problem 2", new ProblemFactoryImpl()::problemTwo),

    PROBLEM_THREE("Problem 3", new ProblemFactoryImpl()::problemThree),

    PROBLEM_FOUR("Problem 4", new ProblemFactoryImpl()::problemFour),

    PROBLEM_FIVE("Problem 5", new ProblemFactoryImpl()::problemFive);

    private final String name;
    private final Function<PlayerPair, Problem> chessProblemFunction;

    Problems(final String name, final Function<PlayerPair, Problem> chessProblem) {
        this.name = name;
        this.chessProblemFunction = chessProblem;
    }

    public Problem getChessProblem(final PlayerPair players) {
        return this.chessProblemFunction.apply(players);
    }

    public String getName() {
        return this.name;
    }

}
