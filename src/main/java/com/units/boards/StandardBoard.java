package com.units.boards;

import com.units.figures.interfaces.Piece;

public class StandardBoard {

    private Piece[][] board;

    public StandardBoard() {
        board = new Piece[8][8];
    }
}
