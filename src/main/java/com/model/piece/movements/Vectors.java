package com.model.piece.movements;

import com.other.Pair;

import java.util.function.Function;

public enum Vectors {

    VERTICAL(new Pair<>(0, 1)),

    HORIZONTAL(new Pair<>(1, 0)),

    TOP_LEFT_BOT_RIGHT(new Pair<>(1, -1)),

    TOP_RIGHT_BOT_LEFT(new Pair<>(1, 1));

    private Pair<Integer, Integer> axis;
    private Function<Pair<Integer, Integer>, Pair<Integer, Integer>> oppositeAxis = (axis) -> new Pair<>(-axis.getX(),
            -axis.getY());

    Vectors(final Pair<Integer, Integer> axis) {
        this.axis = axis;
    }

    Pair<Integer, Integer> getAxis() {
        return this.axis;
    }

    Pair<Integer, Integer> getOpposite() {
        return this.oppositeAxis.apply(this.getAxis());
    }
}

