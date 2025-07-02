package com.model.leaderboard.strategy;

import com.model.user.User;

@FunctionalInterface
public interface ScoreStrategy {

    int getScore(User user);
}
