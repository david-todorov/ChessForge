package com.model.user;

import java.io.Serializable;
import java.util.Optional;


public interface User extends Serializable {


    String getUsername();

    Optional<String> getHashedPassword();

    int getWinCount();

    int getLostCount();

    int getDrawCount();

    int getPlayedMatchCount();

    void increaseWinCount();

    void increaseDrawCount();

    void increaseLostCount();
}
