package com.model.editor;

import com.model.board.Board;
import com.model.board.position.BoardPosition;
import com.model.piece.Piece;

import java.util.Optional;

public interface Editor {


    void addPieceToBoard(Piece piece);

    Optional<StringBoard> getCreatedBoard();


    void removePiece(BoardPosition position);


    StringBoard stringBoardFromNormal(Board startingBoard);


    void createStartingBoard();


    boolean changePiecePosition(Piece piece, BoardPosition position);


    Board getBoardStatus();


    void changeBoardDimensions(int columns, int rows);
}
