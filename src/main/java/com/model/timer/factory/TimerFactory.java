package com.model.timer.factory;

import com.model.player.Player;
import com.model.timer.Timer;

import java.util.List;
import java.util.Map;

public interface TimerFactory {

    Timer defaultTimer(List<Player> players);


    Timer equalTimer(List<Player> players, double duration);


    Timer incrementableTimer(List<Player> players, double duration, int increment);


    Timer fromTimerMap(Map<Player, Double> playersTimer);
}
