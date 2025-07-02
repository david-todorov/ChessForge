package com.views.other.component;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public final class EndGamePopup extends StackPane {
    private static final int WIDTH = 400;
    private static final int HEIGHT = 200;
    private final Text message;
    private final Button button;
    private final VBox layout = new VBox();
    private final Stage primaryStage;

    public EndGamePopup() {
        this.primaryStage = new Stage();
        this.primaryStage.setScene(new Scene(this));
        this.setPrefWidth(WIDTH);
        this.setPrefHeight(HEIGHT);

        // Set background and padding for the popup itself
        this.setStyle("-fx-background-color: #2e2e2e; -fx-padding: 20; -fx-border-color: #444; -fx-border-width: 2; -fx-border-radius: 8; -fx-background-radius: 8;");

        this.message = new Text();
        this.message.setFill(Color.WHITE);
        this.message.setStyle("-fx-font-size: 18px; -fx-font-family: 'Cascadia Code PL';");

        this.button = new Button("EXIT"); // default
        this.button.setStyle(
                "-fx-background-color: #3a3a3a;" +
                        "-fx-text-fill: white;" +
                        "-fx-font-size: 14px;" +
                        "-fx-font-family: 'Cascadia Code PL';" +
                        "-fx-background-radius: 6;" +
                        "-fx-border-color: #555;" +
                        "-fx-border-radius: 6;" +
                        "-fx-border-width: 1;"
        );

        this.button.setOnMouseEntered(e -> this.button.setStyle(
                "-fx-background-color: #00bfff;" +
                        "-fx-text-fill: black;" +
                        "-fx-font-size: 14px;" +
                        "-fx-font-family: 'Cascadia Code PL';" +
                        "-fx-background-radius: 6;" +
                        "-fx-border-radius: 6;" +
                        "-fx-border-color: #555;" +
                        "-fx-border-width: 1;"
        ));

        this.button.setOnMouseExited(e -> this.button.setStyle(
                "-fx-background-color: #3a3a3a;" +
                        "-fx-text-fill: white;" +
                        "-fx-font-size: 14px;" +
                        "-fx-font-family: 'Cascadia Code PL';" +
                        "-fx-background-radius: 6;" +
                        "-fx-border-color: #555;" +
                        "-fx-border-radius: 6;" +
                        "-fx-border-width: 1;"
        ));

        this.layout.setAlignment(Pos.CENTER);
        this.layout.setSpacing(20);
        this.layout.getChildren().addAll(this.message, this.button);
        this.getChildren().addAll(this.layout);
    }

    public void show() {
        primaryStage.show();
    }

    public void setMessage(final String message) {
        this.message.setText(message);
    }

    public void setButtonText(final String text) {
        this.button.setText(text);
    }

    public void close() {
        this.primaryStage.close();
    }

    public void setButtonAction(final Runnable function) {
        this.button.setOnMouseClicked(e -> {
            function.run();
        });
    }
}
