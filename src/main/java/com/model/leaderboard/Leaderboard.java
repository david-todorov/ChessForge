package com.model.leaderboard;

import java.util.List;

import com.model.leaderboard.adapter.LeaderboardUserAdapter;

public interface Leaderboard {

    List<LeaderboardUserAdapter> getUsers();
}
