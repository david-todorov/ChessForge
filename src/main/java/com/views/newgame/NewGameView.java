package com.views.newgame;

import com.views.interfaces.AbstractJavaFXView;
import com.views.pages.PageLoader;
import com.views.pages.Pages;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public final class NewGameView extends AbstractJavaFXView {

    @Override
    public void init() {
    }

    @FXML
    public void onBackClick(final ActionEvent event) {
        PageLoader.getInstance().switchPage(this.getStage(), Pages.HOME, this.getController().getModel());
    }

    @FXML
    public void onOfflineClick(final ActionEvent event) {
        PageLoader.getInstance().switchPage(this.getStage(), Pages.SELECT_GAME,
                this.getController().getModel());
    }

}
