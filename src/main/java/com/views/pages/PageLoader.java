package com.views.pages;

import com.controllers.Controller;
import com.model.Model;
import com.other.settings.SettingMediator;
import com.views.interfaces.JavaFXView;
import javafx.animation.FadeTransition;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;

public final class PageLoader {

    private static final int ANIMATION_DURATION = 350;
    private static final String PATH_START = "pages/";
    private static final String PATH_END = ".fxml";

    private static final class LazyHolder {
        private static final PageLoader SINGLETON = new PageLoader();
    }

    private PageLoader() {
    }

    public static PageLoader getInstance() {
        return LazyHolder.SINGLETON;
    }

    private void loadStyle(final Stage stage) {
        stage.getScene().getStylesheets().clear();
        try {
            stage.getScene().getStylesheets()
                    .add(SettingMediator.getSavedApplicatioStyle().getFilePath().toUri().toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void switchPage(final Stage stage, final Pages page, final Model applicationInstance) {
        final Controller controller = page.getNewControllerInstance();
        controller.setModel(applicationInstance);
        this.switchPageWithSpecifiedController(stage, page, controller);
    }

    public void switchPageWithSpecifiedController(final Stage stage, final Pages page, final Controller controller) {

        String fullPath = "/" + PATH_START + page.getName() + PATH_END; // e.g. "/pages/loading.fxml"

        FXMLLoader loader = new FXMLLoader(getClass().getResource(fullPath));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (stage.getScene() == null) {
            stage.setScene(new Scene(root));
        } else {
            stage.getScene().setRoot(root);
        }

        this.loadStyle(stage);

        stage.setMinHeight(((AnchorPane) stage.getScene().getRoot()).getMinHeight());
        stage.setMinWidth(((AnchorPane) stage.getScene().getRoot()).getMinWidth());

        if (root != null) {

            root.scaleXProperty().bind(Bindings.min(stage.widthProperty().divide(stage.minWidthProperty()),
                    stage.heightProperty().divide(stage.minHeightProperty())));

            root.scaleYProperty().bind(root.scaleXProperty());
        }

        final JavaFXView view = loader.getController();
        controller.setView(view);

        view.setController(controller);
        view.setStage(stage);
        view.init();

        final FadeTransition fadeIn = new FadeTransition(Duration.millis(ANIMATION_DURATION), root);
        fadeIn.setFromValue(0.5);
        fadeIn.setToValue(1.0);
        fadeIn.play();
        //stage.setMaximized(true);
        stage.show();
    }

    public void newPage(final Pages page, final Model applicationInstance) {
        final Stage stage = new Stage();
        this.switchPage(stage, page, applicationInstance);
    }

    public void newPageWithSpecifiedController(final Pages page, final Controller controller) {
        final Stage stage = new Stage();
        this.switchPageWithSpecifiedController(stage, page, controller);
    }

}
