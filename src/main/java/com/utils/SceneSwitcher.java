package com.utils;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class SceneSwitcher {

    private final Stage stage;

    public SceneSwitcher(Stage stage) {
        this.stage = stage;
    }

    public void switchSceneFullScreen(String fxmlPath, String title, String styleSheetPath) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlPath));
            Parent root = fxmlLoader.load();

            Scene scene = new Scene(root, 1920, 1080);
            if (styleSheetPath != null) {
                scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource(styleSheetPath)).toExternalForm());
            }

            switchSceneFullScreen(scene, title);

        } catch (IOException e) {
            e.printStackTrace();
            // Or log error or show alert to user
        }
    }

    public void switchSceneFullScreen(Scene scene, String title) {
        stage.setScene(scene);
        stage.setTitle(title);
        stage.setWidth(1920);
        stage.setHeight(1080);
        stage.show();
    }

    // Overload without title if you want
    public void switchSceneFullScreen(Scene scene) {
        switchSceneFullScreen(scene, stage.getTitle());
    }

}
