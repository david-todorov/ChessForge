package com.controllers;

import com.utils.UIManager;
import javafx.event.ActionEvent;

public class ComputerController {
    public void onBackToMainMenu(ActionEvent actionEvent) {
        UIManager.getInstance().switchMainScene();
    }
}
