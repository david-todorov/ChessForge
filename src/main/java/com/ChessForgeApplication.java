package com;

import com.model.ApplicationInstance;
import com.views.pages.PageLoader;
import com.views.pages.Pages;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class ChessForgeApplication extends Application {

    public static void main(final String[] args) {
        launch(args);
    }

    @Override
    public void start(final Stage primaryStage) throws IOException {
        PageLoader.getInstance().switchPage(primaryStage, Pages.LOADING, new ApplicationInstance());
    }

}
