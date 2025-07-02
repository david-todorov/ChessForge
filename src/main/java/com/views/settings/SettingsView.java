package com.views.settings;

import com.controllers.settings.SettingsController;
import com.other.settings.config.ApplicationStyleConfigStrategy;
import com.other.settings.config.PieceStyleConfigStrategy;
import com.other.settings.filegetter.ApplicationStyleListStrategy;
import com.other.settings.filegetter.PieceStyleListStrategy;
import com.views.interfaces.AbstractJavaFXView;
import com.views.pages.PageLoader;
import com.views.pages.Pages;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Slider;

public final class SettingsView extends AbstractJavaFXView {

    @FXML
    private ChoiceBox<ApplicationStyleConfigStrategy> styleListChoiceBox;

    @FXML
    private ChoiceBox<PieceStyleConfigStrategy> piecesListChoiceBox;

    @FXML
    private Slider volumeSlider;

    private SettingsController getSettingController() {
        return (SettingsController) this.getController();
    }

    @Override
    public void init() {

        this.styleListChoiceBox.getItems().addAll(new ApplicationStyleListStrategy().getAll());
        this.piecesListChoiceBox.getItems().addAll(new PieceStyleListStrategy().getAll());

        this.styleListChoiceBox.setValue(this.getSettingController().getCurrentApplicationStyle());
        this.piecesListChoiceBox.setValue(this.getSettingController().getCurrentPieceStyle());

        this.volumeSlider.setValue(this.getSettingController().getCurrentApplicationVolume());

    }

    @FXML
    public void onBackClick(final ActionEvent event) {
        PageLoader.getInstance().switchPage(this.getStage(), Pages.HOME, this.getController().getModel());
    }

    @FXML
    public void initialize() {
    }

    @FXML
    public void saveButton(final Event event) {

        this.getSettingController().setApplicationStyle(this.styleListChoiceBox.getValue());
        this.getSettingController().setPieceStyle(this.piecesListChoiceBox.getValue());
        this.getSettingController().setApplicationVolume(this.volumeSlider.getValue());
        PageLoader.getInstance().switchPage(this.getStage(), Pages.SETTINGS,
                this.getController().getModel());

    }

}
