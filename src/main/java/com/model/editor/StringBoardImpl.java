package com.model.editor;

public final class StringBoardImpl implements StringBoard {

    private final String board;
    private final int columns;
    private final int rows;

    public StringBoardImpl(final String board, final int columns, final int rows) {
        this.board = board;
        this.columns = columns;
        this.rows = rows;
    }


    @Override
    public String getBoard() {
        return this.board;
    }


    @Override
    public int getRows() {
        return this.rows;
    }


    @Override
    public int getColumns() {
        return this.columns;
    }

}
