package com.utils;

import com.scenes.AppScene;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class UIManager {

    private static UIManager instance;
    private final Stage primaryStage;
    private final SceneSwitcher sceneSwitcher;

    private final AppScene mainScene;

    private final String GLOBAL_CSS = "/com/css/global/global.css";

    private UIManager(Stage stage, AppScene mainScene) {
        this.primaryStage = stage;
        this.mainScene = mainScene;
        this.sceneSwitcher = new SceneSwitcher(stage);
    }


    public static void initialize(Stage stage, AppScene mainScene) {
        if (instance == null) {
            instance = new UIManager(stage, mainScene);
        }
    }

    public static UIManager getInstance() {
        if (instance == null) {
            throw new IllegalStateException("UIManager not initialized. Call UIManager.initialize(stage) first.");
        }
        return instance;
    }

    public void switchTo(AppScene appScene) {
        Scene scene = appScene.getScene();
        if (scene != null) {
            // Add global CSS (if not already added)
            String globalCss = getClass().getResource(GLOBAL_CSS).toExternalForm();
            if (!scene.getStylesheets().contains(globalCss)) {
                scene.getStylesheets().add(globalCss);
            }
        }
        sceneSwitcher.switchSceneFullScreen(scene, appScene.getTitle());
    }

    public void switchMainScene() {
        switchTo(mainScene);
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }
}
