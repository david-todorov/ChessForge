package com.model.board.builders;

import com.model.board.Board;
import com.model.piece.Piece;

public interface BoardBuilder {

    BoardBuilder rows(int rows);


    BoardBuilder columns(int columns);

    BoardBuilder addPiece(Piece piece);

    Board build();
}
