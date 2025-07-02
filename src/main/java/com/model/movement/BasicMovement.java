package com.model.movement;

import com.model.board.position.BoardPosition;

import java.io.Serializable;


public interface BasicMovement extends Serializable {


    BoardPosition getDestination();


    BoardPosition getOrigin();
}
