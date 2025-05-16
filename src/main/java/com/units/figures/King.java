package com.units.figures;

import com.units.figures.interfaces.Movable;
import com.units.figures.interfaces.Piece;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.List;

public class King implements Piece {

    private String imagePath = "/com/images/black/black-king.png";
    private Position position;

    private Identifier identifier;

    public King(Position position, Identifier identifier) {
        this.position = position;
        this.identifier = identifier;
    }

    @Override
    public Position getPosition() {
        return this.position;
    }

    @Override
    public void setPosition(Position position) {
        this.position = position;
    }

    @Override
    public List<Position> getAvailablePositions() {

        int x = position.getX();
        int y = position.getY();

        return List.of(
                new Position(x - 1, y - 1),
                new Position(x, y - 1),
                new Position(x + 1, y - 1),
                new Position(x - 1, y),
                new Position(x + 1, y),
                new Position(x - 1, y + 1),
                new Position(x, y + 1),
                new Position(x + 1, y + 1)
        );
    }

    @Override
    public Node getVisual() {


        ImageView imageView = new ImageView(new Image(imagePath));
        imageView.setFitWidth(40);
        imageView.setFitHeight(40);

        Button button = new Button();
        button.setGraphic(imageView);
        // button.setStyle("-fx-background-color: transparent;"); // optional
        button.setPrefSize(50, 50);

        return button;
    }

    @Override
    public String getId() {
        return this.identifier.toString();
    }
}
