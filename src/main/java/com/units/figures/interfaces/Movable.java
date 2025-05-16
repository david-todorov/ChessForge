package com.units.figures.interfaces;

import com.units.figures.Position;

import java.util.List;

public interface Movable {

    Position getPosition();

    void setPosition(Position position);

    List<Position> getAvailablePositions();
}
