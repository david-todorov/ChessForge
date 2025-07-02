package com.model;

import java.util.Optional;

import com.model.match.Match;
import com.model.replay.ReplayData;
import com.model.user.User;


public interface Model {

    // USERS FUNCTIONALITY

    void setFirstUser(User user);


    void setSecondUser(User user);

    Optional<User> getFirstUser();


    Optional<User> getSecondUser();


    void setMatch(Match match);


    Optional<Match> getMatch();


    void deleteMatch();


    void setReplay(ReplayData replay);

    Optional<ReplayData> getReplay();


    void deleteReplay();

}
