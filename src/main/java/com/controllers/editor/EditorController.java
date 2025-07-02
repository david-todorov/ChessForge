package com.controllers.editor;

import com.controllers.setup.SetupController;
import com.model.board.Board;
import com.model.board.position.BoardPosition;
import com.model.piece.Piece;

public interface EditorController extends SetupController {


    void addPieceToBoard(Piece piece);


    Board getBoardStatus();


    void createCustomizedStartingBoard();


    boolean updatePiecePosition(Piece piece, BoardPosition position);


    void removePieceAtPosition(BoardPosition position);


    void resetBoard(int columns, int rows);

}
