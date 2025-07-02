package com.views.home;

import com.controllers.home.HomeController;
import com.controllers.login.LoginController;
import com.controllers.login.LoginControllerImpl;
import com.views.interfaces.AbstractJavaFXView;
import com.views.pages.PageLoader;
import com.views.pages.Pages;
import com.model.user.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public final class HomeView extends AbstractJavaFXView {

    @Override
    public void init() {

    }

    @FXML
    public void onFirstUserLoginClick(final MouseEvent event) {
        final LoginController loginController = new LoginControllerImpl();
        loginController.setModel(this.getController().getModel());
        PageLoader.getInstance().switchPageWithSpecifiedController(this.getStage(), Pages.LOGIN, loginController);
    }

    @FXML
    public void onSecondUserLoginClick(final MouseEvent event) {
        final LoginController loginController = new LoginControllerImpl(false);
        loginController.setModel(this.getController().getModel());
        PageLoader.getInstance().switchPageWithSpecifiedController(this.getStage(), Pages.LOGIN, loginController);
    }

    @FXML
    public void onNewGameClick(final ActionEvent event) {
        PageLoader.getInstance().switchPage(this.getStage(), Pages.SELECT_GAME,
                this.getController().getModel());
    }

    @FXML
    public void onSettingsClick(final ActionEvent event) {
        PageLoader.getInstance().switchPage(this.getStage(), Pages.SETTINGS,
                this.getController().getModel());
    }

    private HomeController getHomeController() {
        return (HomeController) this.getController();
    }

    public void onBackClick(ActionEvent actionEvent) {
        PageLoader.getInstance().switchPage(this.getStage(), Pages.LOGIN,
                this.getController().getModel());
    }
}
