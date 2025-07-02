package com.views.selectgame;

import com.views.interfaces.AbstractJavaFXView;
import com.views.pages.PageLoader;
import com.views.pages.Pages;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public final class SelectGameView extends AbstractJavaFXView {

    @Override
    public void init() {
    }

    @FXML
    public void onNormalClick(final ActionEvent event) {
        PageLoader.getInstance().switchPage(this.getStage(), Pages.SETUP,
                this.getController().getModel());
    }

    @FXML
    public void onCustomizedClick(final ActionEvent event) {
        PageLoader.getInstance().switchPage(this.getStage(), Pages.EDITOR,
                this.getController().getModel());
    }

    @FXML
    public void onProblemClick(final ActionEvent event) {
        PageLoader.getInstance().switchPage(this.getStage(), Pages.PROBLEM,
                this.getController().getModel());
    }

    @FXML
    public void onBackClick(final ActionEvent event) {
        PageLoader.getInstance().switchPage(this.getStage(), Pages.HOME,
                this.getController().getModel());
    }
}
