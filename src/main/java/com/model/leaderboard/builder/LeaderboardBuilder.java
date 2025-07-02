package com.model.leaderboard.builder;

import java.util.Collection;
import java.util.Comparator;
import java.util.function.Predicate;

import com.model.leaderboard.Leaderboard;
import com.model.leaderboard.strategy.ScoreStrategy;
import com.model.user.User;


public interface LeaderboardBuilder {

    LeaderboardBuilder addUsers(Collection<User> users);


    LeaderboardBuilder addFilter(Predicate<User> predicate);


    LeaderboardBuilder comparator(Comparator<User> comparator);


    LeaderboardBuilder strategy(ScoreStrategy strategy);


    Leaderboard build();
}
