package com.model.piece.factory;

import com.model.board.position.BoardPosition;
import com.model.piece.Piece;
import com.model.piece.PieceType;

import java.io.Serializable;

public interface PieceFactory extends Serializable {


    Piece getPieceFromPieceType(PieceType type, BoardPosition piecePosition);

    Piece getPawn(BoardPosition piecePosition);

    Piece getKing(BoardPosition piecePosition);

    Piece getQueen(BoardPosition piecePosition);

    Piece getBishop(BoardPosition piecePosition);

    Piece getKnight(BoardPosition piecePosition);

    Piece getRook(BoardPosition piecePosition);
}
