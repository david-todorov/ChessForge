package com.controllers.leaderboard;

import com.controllers.Controller;
import com.model.leaderboard.adapter.LeaderboardUserAdapter;

import java.io.IOException;
import java.util.List;

public interface LeaderboardController extends Controller {

    List<LeaderboardUserAdapter> getUsers() throws IOException;
}
