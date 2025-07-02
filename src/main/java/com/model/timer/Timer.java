package com.model.timer;

import com.model.player.Player;

import java.util.Optional;

public interface Timer {


    double getRemaningTime(Player player);


    void start(Player player);


    void stop();


    boolean isRunning();


    void switchPlayer(Player player);


    boolean isModifiable();


    void setModifiable(boolean modifiable);


    void setIncrement(int increment);


    int getIncrement();


    boolean updatePlayerTime(Player player, double second);


    boolean addTimeToPlayer(Player player, double seconds);


    Optional<Player> getPlayersWithoutTime();

}