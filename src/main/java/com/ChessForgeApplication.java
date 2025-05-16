package com;

import com.scenes.MainMenuScene;
import com.utils.UIManager;
import javafx.application.Application;
import javafx.stage.Stage;

public class ChessForgeApplication extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        // Main Scene which is our case is the main menu
        MainMenuScene mainMenu = new MainMenuScene();
        UIManager.initialize(stage, mainMenu); // Init once

        UIManager.getInstance().switchMainScene();
    }

    public static void main(String[] args) {
        launch();
    }
}
