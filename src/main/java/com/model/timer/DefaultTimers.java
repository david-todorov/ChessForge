package com.model.timer;

import com.model.player.pair.PlayerPair;
import com.model.timer.factory.TimerFactoryImpl;

import java.util.stream.Collectors;

public enum DefaultTimers {


    ONE_MINUTE("One Minute", 60),

    THREE_MINUTES("Three Minutes", 180),

    TEN_MINUTES("Ten Minutes", 600),


    TEN_SECONDS("Ten Seconds", 10),


    TEN_SECONDS_PLUS_ONE("Ten Seconds + One", 10, 1),


    NO_LIMIT("No limit", Double.POSITIVE_INFINITY);


    private final String stringify;


    private final double seconds;


    private final int increment;


    DefaultTimers(final String stringify, final double seconds) {
        this(stringify, seconds, 0);
    }


    DefaultTimers(final String stringify, final double seconds, final Integer increment) {
        this.stringify = stringify;
        this.seconds = seconds;
        this.increment = increment;
    }


    public double getSeconds() {
        return this.seconds;
    }


    public Timer getTimer(final PlayerPair players) {
        final Timer timer = new TimerFactoryImpl().equalTimer(players.stream().collect(Collectors.toList()),
                this.seconds);
        timer.setIncrement(this.increment);
        return timer;
    }

    @Override
    public String toString() {
        return this.stringify;
    }

}
