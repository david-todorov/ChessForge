package com.views.other.component;

import com.model.piece.Piece;
import com.model.piece.PieceType;
import com.model.player.PlayerColor;
import com.views.other.board.strategy.movement.GraphicPieceMovementStrategy;
import javafx.beans.binding.DoubleBinding;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;


public final class PieceRectangle extends Rectangle {

    private final Piece piece;

    public PieceRectangle(final Piece piece) {
        this.piece = piece;
    }

    public PieceRectangle(final Piece piece, final Image image, final DoubleBinding widthProperty,
            final GraphicPieceMovementStrategy movementStrategy) {
        this.piece = piece;
        this.setFill(new ImagePattern(image));
        this.widthProperty().bind(widthProperty);
        this.heightProperty().bind(widthProperty);
        this.setOnMousePressed(movementStrategy::onPiecePressed);
        this.setOnMouseDragged(movementStrategy::onPieceDragged);
        this.setOnMouseReleased(movementStrategy::onPieceReleased);
    }

    public Piece getPiece() {
        return this.piece;
    }

    public PlayerColor getPieceColor() {
        return this.piece.getPlayer().getColor();
    }

    public PieceType getPieceType() {
        return this.piece.getType();
    }

}
