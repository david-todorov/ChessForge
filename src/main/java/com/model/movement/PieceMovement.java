package com.model.movement;

import com.model.piece.Piece;

public interface PieceMovement extends BasicMovement {

    Piece getPieceInvolved();


    void execute();
}
