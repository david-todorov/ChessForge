package com.model.piece.factory;

import com.model.board.position.BoardPosition;
import com.model.piece.Piece;
import com.model.piece.PieceImpl;
import com.model.piece.PieceType;
import com.model.player.Player;

public final class PieceFactoryImpl implements PieceFactory {


    private static final long serialVersionUID = 3341059957079038770L;
    private final Player owner;

    public PieceFactoryImpl(final Player owner) {
        this.owner = owner;
    }


    @Override
    public Piece getPawn(final BoardPosition piecePosition) {
        return this.getPieceFromPieceType(PieceType.PAWN, piecePosition);
    }

    @Override
    public Piece getKing(final BoardPosition piecePosition) {
        return this.getPieceFromPieceType(PieceType.KING, piecePosition);
    }

    @Override
    public Piece getQueen(final BoardPosition piecePosition) {
        return this.getPieceFromPieceType(PieceType.QUEEN, piecePosition);
    }

    @Override
    public Piece getBishop(final BoardPosition piecePosition) {
        return this.getPieceFromPieceType(PieceType.BISHOP, piecePosition);
    }

    @Override
    public Piece getKnight(final BoardPosition piecePosition) {
        return this.getPieceFromPieceType(PieceType.KNIGHT, piecePosition);
    }

    @Override
    public Piece getRook(final BoardPosition piecePosition) {
        return this.getPieceFromPieceType(PieceType.ROOK, piecePosition);
    }

    @Override
    public Piece getPieceFromPieceType(final PieceType type, final BoardPosition piecePosition) {
        return new PieceImpl(type, piecePosition, this.owner);
    }
}
