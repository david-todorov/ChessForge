package com.scenes;

import com.controllers.TwoPlayerController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;
import java.util.Objects;

public class TwoPlayerScene implements AppScene {

    private final String fxmlPath = "/com/fxml/two-player/two-player.fxml";
    private final String cssPath = "/com/css/two-player/two-player.css";
    private final String title = "Two Player";


    @Override
    public Scene getScene() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Parent root = loader.load();
            Scene scene = new Scene(root);

            scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource(cssPath)).toExternalForm());
            return scene;
        } catch (IOException e) {
            e.printStackTrace();
            return null; // Or throw a custom runtime exception if preferred
        }
    }

    @Override
    public String getTitle() {
        return title;
    }
}
