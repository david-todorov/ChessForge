package com.scenes;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;
import java.util.Objects;

public class MainMenuScene implements AppScene {

    private final String fxmlPath = "/com/fxml/mainmenu/mainmenu.fxml";
    private final String cssPath = "/com/css/mainmenu/mainmenu.css";
    private final String title = "Chess Forge";

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
