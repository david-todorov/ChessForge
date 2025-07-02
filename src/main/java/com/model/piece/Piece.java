package com.model.piece;

import com.model.board.position.BoardPosition;
import com.model.player.Player;

import java.io.Serializable;

public interface Piece extends Serializable {

    PieceType getType();

    String getIdentifier();

    void setPosition(BoardPosition positionalDestination);

    BoardPosition getPiecePosition();

    Player getPlayer();

    boolean hasAlreadyBeenMoved();

    void setHasMoved(boolean moved);

}
