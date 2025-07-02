package com.views.loading;

import com.controllers.loading.LoadingController;
import com.views.interfaces.AbstractJavaFXView;
import com.views.pages.PageLoader;
import com.views.pages.Pages;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.ProgressBar;
import javafx.stage.StageStyle;

public final class LoadingView extends AbstractJavaFXView {

    private static final int LOADING_TIME = 1500;
    private volatile boolean loaded;

    @FXML
    private ProgressBar progress;

    @Override
    public void init() {
        this.getStage().resizableProperty().set(false);
        this.getStage().initStyle(StageStyle.UNDECORATED);
        new Thread(this::load).start();
        new Thread(this::runLoadingBar).start();
    }

    private void runLoadingBar() {
        final double threshold = 0.70;
        for (double i = 1; i <= 100; i++) {
            final double percentageValue = i / 100;
            while (percentageValue > threshold && !this.loaded) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            Platform.runLater(() -> this.progress.setProgress(percentageValue));
            try {
                Thread.sleep(LOADING_TIME / 100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Platform.runLater(() -> {
            this.getStage().close();
            PageLoader.getInstance().newPage(Pages.LOGIN, this.getController().getModel());
        });
    }

    private void load() {
        this.getLoadingController().load();
        this.loaded = true;
    }

    private LoadingController getLoadingController() {
        return (LoadingController) this.getController();
    };

}