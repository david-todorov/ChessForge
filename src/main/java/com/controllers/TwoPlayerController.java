package com.controllers;

import com.utils.UIManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;

public class TwoPlayerController {

    @FXML
    public static VBox boardPane;
    public void onBackToMainMenu(ActionEvent actionEvent) {
        UIManager.getInstance().switchMainScene();
    }
}
