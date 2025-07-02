package com.model.player;

import com.model.piece.factory.PieceFactory;
import com.model.user.User;

public interface Player {

    PlayerColor getColor();

    User getUser();

    PieceFactory getPieceFactory();
}
