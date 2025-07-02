package com.model.movement.manager;

import com.model.movement.PieceMovement;

public interface CastlingManager {

    void checkAndExecuteCastling(PieceMovement movement);

    boolean isCastlingFullyCorrect(PieceMovement movement);

    boolean mightItBeCastle(PieceMovement movement);

}
