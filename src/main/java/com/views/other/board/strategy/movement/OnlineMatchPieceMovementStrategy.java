package com.views.other.board.strategy.movement;

import com.controllers.match.MatchController;
import com.model.board.position.BoardPosition;
import com.model.board.position.BoardPositionImpl;
import com.model.piece.Piece;
import com.views.other.board.MatchBoard;
import com.views.other.component.PieceRectangle;
import javafx.scene.input.MouseEvent;



public final class OnlineMatchPieceMovementStrategy extends NormalMatchPieceMovementStrategy {

    private final boolean isWhite;

    public OnlineMatchPieceMovementStrategy(final MatchBoard board, final MatchController controller,
                                            final boolean isWhite) {
        super(board, controller);
        this.isWhite = isWhite;
    }


    @Override
    public void onPiecePressed(final MouseEvent event) {
        final PieceRectangle piece = (PieceRectangle) event.getSource();
        if (!this.isLocalPlayerPiece(piece.getPiece())) {
            System.out.println("NO PLAYER");
            return;
        }
        super.onPiecePressed(event);
    }


    @Override
    public void onPieceDragged(final MouseEvent event) {
        final PieceRectangle piece = (PieceRectangle) event.getSource();
        if (!this.isLocalPlayerPiece(piece.getPiece())) {
            return;
        }
        super.onPieceDragged(event);
    }

    @Override
    public void onPieceReleased(final MouseEvent event) {
        final PieceRectangle piece = (PieceRectangle) event.getSource();
        if (!this.isLocalPlayerPiece(piece.getPiece())) {
            return;
        }
        super.onPieceReleased(event);
    }

    private boolean isLocalPlayerPiece(final Piece piece) {
        return this.isWhite ? this.getMatchController().getWhitePlayer().equals(piece.getPlayer())
                : this.getMatchController().getBlackPlayer().equals(piece.getPlayer());
    }


    @Override
    public BoardPosition getBoardPositionsFromGridCoordinates(final double x, final double y) {
        final BoardPosition position = super.getBoardPositionsFromGridCoordinates(x, y);
        return this.isWhite ? position
                : new BoardPositionImpl(position.getX(),
                        this.getMatchController().getBoard().getRows() - 1 - position.getY());
    }


    @Override
    public BoardPosition getGridCoordinateFromBoardPosition(final BoardPosition position) {
        final BoardPosition pos = super.getGridCoordinateFromBoardPosition(position);
        return this.isWhite ? pos
                : new BoardPositionImpl(pos.getX(), this.getMatchController().getBoard().getRows() - 1 - pos.getY());
    }

}
