package com.model.movement;


import com.model.board.position.BoardPosition;
import com.model.board.position.BoardPositionImpl;
import com.model.piece.Piece;

public final class PieceMovementImpl implements PieceMovement {

    private static final long serialVersionUID = 1018763736571721881L;
    private final Piece pieceInvolved;
    private final BoardPosition destination;
    private final BoardPosition origin;

    public PieceMovementImpl(final Piece piece, final BoardPosition destination) {
        this(piece, new BoardPositionImpl(piece.getPiecePosition()), destination);
    }

    public PieceMovementImpl(final Piece piece, final BoardPosition origin, final BoardPosition destination) {
        this.pieceInvolved = piece;
        this.destination = new BoardPositionImpl(destination);
        this.origin = new BoardPositionImpl(origin);
    }


    @Override
    public Piece getPieceInvolved() {
        return this.pieceInvolved;
    }


    @Override
    public BoardPosition getDestination() {
        return this.destination;
    }


    @Override
    public void execute() {
        this.pieceInvolved.setPosition(this.destination);
        this.pieceInvolved.setHasMoved(true);
    }


    @Override
    public BoardPosition getOrigin() {
        return this.origin;
    }


    @Override
    public String toString() {
        return "MovementImpl [pieceInvolved=" + pieceInvolved + ", destination=" + destination + ", origin=" + origin
                + "]";
    }

}
