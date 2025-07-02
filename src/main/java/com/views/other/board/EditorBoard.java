package com.views.other.board;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Stream;

import com.model.board.position.BoardPosition;
import com.model.board.position.BoardPositionImpl;
import javafx.beans.binding.DoubleBinding;
import javafx.geometry.HPos;
import javafx.scene.Cursor;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import com.controllers.editor.EditorController;
import com.model.board.Board;
import com.model.piece.Piece;
import com.model.piece.PieceImpl;
import com.model.piece.PieceType;
import com.model.player.PlayerColor;
import com.model.player.PlayerImpl;
import com.views.other.board.strategy.movement.GraphicPieceMovementStrategy;
import com.views.other.component.PieceRectangle;
import com.views.other.component.Tile;
import com.views.other.image.PieceImageLoader;
import com.views.editor.EditorView;

public class EditorBoard extends Pane {

    private final PieceImageLoader pieceImageLoader = new PieceImageLoader();
    private final GridPane guiBoard = new GridPane();
    private final Map<PlayerColor, VBox> pieceSelectors;
    private static final double PIECE_SCALE = 1.5;
    private final Set<PieceRectangle> pieces = new HashSet<>();
    private final EditorController editorController;
    private final EditorView editorView;
    private static final int PIECE_SELECTOR_SCALE_RATIO = 6;
    private static final BoardPosition DEFAULT_POSITION = new BoardPositionImpl(-1, -1);

    private final Supplier<Tile> tileSampleSupplierForBindings = () -> this.guiBoard.getChildren().stream()
            .filter(e -> e instanceof Tile).map(e -> (Tile) e).findAny().get();

    public EditorBoard(final EditorController editorController, final EditorView editorView,
            final VBox whitePieceSelector, final VBox blackPieceSelector) {
        this.pieceSelectors = Map.of(PlayerColor.WHITE, whitePieceSelector, PlayerColor.BLACK, blackPieceSelector);
        this.editorView = editorView;
        this.editorController = editorController;
        this.drawBoard(this.editorController.getBoardStatus());
        this.redraw(this.editorController.getBoardStatus());
        this.getChildren().add(this.guiBoard);
        this.pieces.forEach(this::setPieceListeners);
        this.setupVBoxes();
    }

    private void setupVBoxes() {

        final GraphicPieceMovementStrategy st = new GraphicPieceMovementStrategy() {
            @Override
            public void onPieceReleased(final MouseEvent event) {
                EditorBoard.this.onPieceReleased(event);
            }

            @Override
            public void onPiecePressed(final MouseEvent event) {
                EditorBoard.this.onPieceClick(event);
            }

            @Override
            public void onPieceDragged(final MouseEvent event) {
                EditorBoard.this.onPieceDragged(event);
            }
        };

        Arrays.stream(PlayerColor.values()).forEach(color -> {
            Arrays.stream(PieceType.values()).forEach(pieceType -> {
                final Piece temp = new PieceImpl(pieceType, DEFAULT_POSITION, new PlayerImpl(color, null));
                final PieceRectangle piece = new PieceRectangle(temp, this.pieceImageLoader.getPieceImage(temp),
                        this.pieceSelectors.get(color).heightProperty().divide(PIECE_SELECTOR_SCALE_RATIO), st);
                this.pieceSelectors.get(color).getChildren().add(piece);
            });
        });

    }

    private void createNodeBindings(final PieceRectangle pieceRect, final Image img, final DoubleBinding binder) {
        pieceRect.widthProperty().bind(binder);
        pieceRect.heightProperty().bind(binder);
        this.pieces.add(pieceRect);
        pieceRect.setFill(new ImagePattern(img));
    }

    private void setPieceListeners(final PieceRectangle pieceRect) {
        pieceRect.setOnMousePressed(this::onPieceClick);
        pieceRect.setOnMouseDragged(this::onPieceDragged);
        pieceRect.setOnMouseReleased(this::onPieceReleased);
    }


    private void onPieceClick(final MouseEvent event) {
        final PieceRectangle pieceRect = (PieceRectangle) event.getSource();
        this.editorView.getStage().getScene().setCursor(Cursor.OPEN_HAND);
        // Check if it's over limit

        if (event.getButton().equals(MouseButton.SECONDARY) && isMouseOnBoard(event)) {
            this.removePieceTotally(pieceRect);
        } else {
            if (this.guiBoard.getChildren().contains(pieceRect)) {
                if (event.getButton().equals(MouseButton.MIDDLE)) {
                    this.removePieceTotally(pieceRect);
                    this.drawPieceOnGuiBoard(event, pieceRect);
                } else {
                    this.guiBoard.getChildren().remove(pieceRect);
                }
            } else {
                this.generateNewPieceViewPort(event, pieceRect);
            }
            this.getChildren().add(pieceRect);
        }
    }

    private void generateNewPieceViewPort(final MouseEvent event, final PieceRectangle pieceRect) {
        final int pieceNumber = 6;
        final PieceRectangle newPieceRect = new PieceRectangle(new PieceImpl(pieceRect.getPiece()));
        this.createNodeBindings(newPieceRect, this.pieceImageLoader.getPieceImage(newPieceRect.getPiece()),
                this.pieceSelectors.get(newPieceRect.getPiece().getPlayer().getColor()).heightProperty()
                        .divide(pieceNumber));
        this.pieces.add(newPieceRect);
        this.rearrangeVBox(this.pieceSelectors.get(pieceRect.getPieceColor()), pieceRect, newPieceRect, event);
        this.setPieceListeners(newPieceRect);
    }

    private void rearrangeVBox(final VBox vBoxSelector, final PieceRectangle pieceRect,
            final PieceRectangle newPieceRect, final MouseEvent event) {
        final int pos = vBoxSelector.getChildren().indexOf(pieceRect);
        vBoxSelector.getChildren().remove(pieceRect);
        vBoxSelector.getChildren().add(pos, newPieceRect);
        pieceRect.setY(event.getSceneY()
                - (this.pieceSelectors.get(pieceRect.getPieceColor()).getChildren().indexOf(newPieceRect)
                        * pieceRect.getHeight())
                - (pieceRect.getHeight() / 2));
        pieceRect.setX(event.getSceneX() - (pieceRect.getWidth() / 2));
    }


    private void onPieceDragged(final MouseEvent event) {
        final PieceRectangle piece = (PieceRectangle) event.getSource();
        this.editorView.getStage().getScene().setCursor(Cursor.CLOSED_HAND);
        piece.setX(event.getX() - piece.getWidth() / 2);
        piece.setY(event.getY() - piece.getHeight() / 2);
        if (event.getButton().equals(MouseButton.PRIMARY) && event.isControlDown()) {
            this.drawPieceOnGuiBoard(event, piece);
            this.redraw(this.editorController.getBoardStatus());
        } else if (event.getButton().equals(MouseButton.SECONDARY)) {

            this.pieces.stream()
                    .filter(i -> i.getPiece().getPiecePosition()
                            .equals(this.getBoardPositionsFromGuiCoordinates(event.getSceneX(), event.getSceneY())))
                    .findAny().ifPresent(e -> {
                        this.removePieceTotally(e);
                        this.redraw(this.editorController.getBoardStatus());
                    });
        }
    }

    private void drawPieceOnGuiBoard(final MouseEvent event, final PieceRectangle piece) {
        if (this.getChildren().contains(piece) && this.isMouseOnBoard(event)) {
            final BoardPosition position = this.getBoardPositionsFromGuiCoordinates(event.getSceneX(),
                    event.getSceneY());
            this.editorController.addPieceToBoard(piece.getPiece().getPlayer().getPieceFactory()
                    .getPieceFromPieceType(piece.getPieceType(), position));
            this.guiBoard.requestFocus();
        }
    }

    private void onPieceReleased(final MouseEvent event) {
        final PieceRectangle piece = (PieceRectangle) event.getSource();
        this.editorView.getStage().getScene().setCursor(Cursor.DEFAULT);
        this.updatePiecePositionOnGuiBoard(event, piece);
        this.redraw(this.editorController.getBoardStatus());
    }

    private void updatePiecePositionOnGuiBoard(final MouseEvent event, final PieceRectangle piece) {
        if (this.getChildren().contains(piece) && this.isMouseOnBoard(event)) {

            final BoardPosition position = this.getBoardPositionsFromGuiCoordinates(event.getSceneX(),
                    event.getSceneY());
            this.getChildren().remove(piece);
            piece.getPiece().setPosition(position);
            this.editorController.addPieceToBoard(piece.getPiece());

        } else if (!this.isMouseOnBoard(event)) {
            this.removePieceTotally(piece);
        }
    }

    private void removePieceTotally(final PieceRectangle piece) {
        this.editorController.removePieceAtPosition(piece.getPiece().getPiecePosition());
        this.getChildren().remove(piece);
        this.guiBoard.getChildren().remove(piece);
        this.pieces.remove(piece);
        this.redraw(this.editorController.getBoardStatus());
    }

    private boolean isMouseOnBoard(final MouseEvent event) {
        return this.editorController.getBoardStatus()
                .contains(this.getBoardPositionsFromGuiCoordinates(event.getSceneX(), event.getSceneY()));
    }

    private void drawPiece(final Piece piece) {
        final PieceRectangle pieceViewPort = new PieceRectangle(piece);
        this.createNodeBindings(pieceViewPort, this.pieceImageLoader.getPieceImage(piece),
                this.tileSampleSupplierForBindings.get().widthProperty().divide(PIECE_SCALE));
        this.setPieceListeners(pieceViewPort);
        this.pieces.add(pieceViewPort);

        final BoardPosition realPosition = this.getRealPositionFromBoardPosition(piece.getPiecePosition());

        this.guiBoard.add(pieceViewPort, realPosition.getX(), realPosition.getY());
        GridPane.setHalignment(pieceViewPort, HPos.CENTER);
    }

    private void redraw(final Board board) {
        this.guiBoard.getChildren().removeAll(this.pieces);
        board.getPieces().forEach(this::drawPiece);
    }

    public void drawBoard(final Board board) {
        this.guiBoard.getChildren().clear();
        final int bigger = Integer.max(board.getColumns(), board.getRows());
        Stream.iterate(0, i -> i + 1).limit(board.getRows()).forEach(i -> {
            Stream.iterate(0, j -> j + 1).limit(board.getColumns()).forEach(j -> {
                final Tile tile = new Tile(this.getRealPositionFromBoardPosition(new BoardPositionImpl(j, i)),
                        this.widthProperty().divide(bigger), this.editorController.getBoardStatus().getRows());
                this.guiBoard.add(tile, j, i);
            });
        });
    }

    private BoardPosition getBoardPositionsFromGuiCoordinates(final double x, final double y) {

        final Tile tile = this.guiBoard.getChildren().stream().filter(i -> i instanceof Tile).map(i -> (Tile) i)
                .findAny().get();

        final double xMargin = this.localToScene(this.getBoundsInLocal()).getMinX();
        final double yMargin = this.localToScene(this.getBoundsInLocal()).getMinY();

        final int column = (int) ((((x - xMargin) / this.getScene().getRoot().getScaleX())
                / (tile.getWidth() * this.editorController.getBoardStatus().getColumns()))
                * this.editorController.getBoardStatus().getColumns());

        int row = (int) ((((y - yMargin) / this.getScene().getRoot().getScaleY())
                / (tile.getHeight() * this.editorController.getBoardStatus().getRows()))
                * this.editorController.getBoardStatus().getRows());
        row = this.editorController.getBoardStatus().getRows() - 1 - row;

        return new BoardPositionImpl(column, row);
    }

    private BoardPosition getRealPositionFromBoardPosition(final BoardPosition position) {
        return new BoardPositionImpl(position.getX(),
                this.editorController.getBoardStatus().getRows() - 1 - position.getY());
    }

}
