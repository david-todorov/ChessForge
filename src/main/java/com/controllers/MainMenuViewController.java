package com.controllers;

import com.scenes.ComputerScene;
import com.scenes.TwoPlayerScene;
import com.units.figures.Identifier;
import com.units.figures.King;
import com.units.figures.Position;
import com.utils.UIManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class MainMenuViewController {

    @FXML
    private void startTwoPlayerMode(ActionEvent event) {
        TwoPlayerScene twoPlayerScene = new TwoPlayerScene();
        UIManager.getInstance().switchTo(twoPlayerScene);
    }

    @FXML
    private void startComputerMode(ActionEvent event) {
        ComputerScene computerScene = new ComputerScene();

        UIManager.getInstance().switchTo(computerScene);
    }

}
