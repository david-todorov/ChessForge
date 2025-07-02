package com.model.movement;


import com.model.board.position.BoardPosition;
import com.model.board.position.BoardPositionImpl;

public final class BasicMovementImpl implements BasicMovement {

    private static final long serialVersionUID = -3832679850012025490L;

    private final BoardPosition destination;
    private final BoardPosition origin;

    public BasicMovementImpl(final BoardPosition origin, final BoardPosition destination) {
        this.destination = new BoardPositionImpl(destination);
        this.origin = new BoardPositionImpl(origin);
    }


    @Override
    public BoardPosition getDestination() {
        return this.destination;
    }

    @Override
    public BoardPosition getOrigin() {
        return this.origin;
    }

    @Override
    public String toString() {
        return "MovementImpl [" + "origin= " + this.origin + " ,destination=" + destination + "]";
    }
}
