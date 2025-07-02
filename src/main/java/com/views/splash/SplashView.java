package com.views.splash;

import com.Launcher;
import com.views.interfaces.AbstractJavaFXView;
import com.views.pages.PageLoader;
import com.views.pages.Pages;
import javafx.event.Event;
import javafx.fxml.FXML;

public final class SplashView extends AbstractJavaFXView {

    private void startCommandLine() {
        Launcher.main(new String[] { "-cmd" });
    }


    @FXML
    public void onCommandLineClick(final Event event) {
        this.getStage().close();
        new Thread(this::startCommandLine).start();
    }


    @FXML
    public void onJavaFxClick(final Event event) {
        PageLoader.getInstance().switchPage(this.getStage(), Pages.LOGIN,
                this.getController().getModel());
    }

    @Override
    public void init() {
    }

}
