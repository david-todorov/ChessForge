package com.model.board.position;

import java.io.Serializable;

public final class BoardPositionImpl implements BoardPosition, Serializable {


    private static final long serialVersionUID = -7518041140044999585L;
    private final int xPosition;
    private final int yPosition;

    public BoardPositionImpl(final int xPosition, final int yPosition) {
        this.xPosition = xPosition;
        this.yPosition = yPosition;
    }

    public BoardPositionImpl(final BoardPosition pos) {
        this.xPosition = pos.getX();
        this.yPosition = pos.getY();
    }

    @Override
    public int getX() {
        return this.xPosition;
    }

    @Override
    public int getY() {
        return this.yPosition;
    }

    @Override
    public String toString() {
        return "BoardPositionImpl [xPosition=" + xPosition + ", yPosition=" + yPosition + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + xPosition;
        result = prime * result + yPosition;
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final BoardPositionImpl other = (BoardPositionImpl) obj;
        if (xPosition != other.xPosition) {
            return false;
        }
        return yPosition == other.yPosition;
    }

}
