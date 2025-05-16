package com.scenes;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;
import java.util.Objects;

public class ComputerScene implements AppScene {

    private final String fxmlPath = "/com/fxml/computer/computer.fxml";
    private final String cssPath = "/com/css/computer/computer.css";
    private final String title = "Computer";

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
