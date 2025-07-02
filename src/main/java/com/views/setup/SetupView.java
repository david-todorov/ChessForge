package com.views.setup;

import com.controllers.setup.SetupController;
import com.controllers.setup.WhitePlayerChoice;
import com.model.game.types.GameType;
import com.model.timer.DefaultTimers;
import com.other.Pair;
import com.views.interfaces.AbstractJavaFXView;
import com.views.pages.PageLoader;
import com.views.pages.Pages;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public final class SetupView extends AbstractJavaFXView {

    @FXML
    private ScrollPane scrollpane;

    @FXML
    private AnchorPane container;

    @FXML
    private Label modeInfoTitle;

    @FXML
    private Label modeInfoDescription;

    @FXML
    private ChoiceBox<DefaultTimers> timerChoice;

    @FXML
    private ChoiceBox<WhitePlayerChoice> whitePlayerChoice;

    private final GridPane grid = new GridPane();

    private GameType selectedGameType;

    private void onSelectedGameTypeChange() {
        this.getSetupController().setGameType(this.selectedGameType);
    }

    private void onTimerChoiceChange() {
        this.getSetupController().setTimer(this.timerChoice.getValue());
    }

    private void onWhitePlayerChoiceChange() {
        this.getSetupController().setWhitePlayerChoice(this.whitePlayerChoice.getValue());
    }

    private void setupWhitePlayerChoice() {
        this.whitePlayerChoice.getItems().addAll(WhitePlayerChoice.values());
        this.whitePlayerChoice.getSelectionModel().select(WhitePlayerChoice.RANDOM);
        this.whitePlayerChoice.setOnAction(e -> this.onWhitePlayerChoiceChange());
    }

    private void setupTimer() {
        this.timerChoice.getItems().addAll(Arrays.asList(DefaultTimers.values()));
        this.timerChoice.getSelectionModel().select(DefaultTimers.TEN_MINUTES);
        this.timerChoice.setOnAction(e -> this.onTimerChoiceChange());
    }

    private void setupDefaultValues() {
        // Setup the default game type
        this.selectedGameType = GameType.CLASSIC;
        this.modeInfoTitle.setText(this.selectedGameType.getName());
        this.modeInfoDescription.setText(this.selectedGameType.getDescription());

        this.getSetupController().setGameType(this.selectedGameType);
        this.getSetupController().setTimer(this.timerChoice.getValue());
        this.getSetupController().setWhitePlayerChoice(this.whitePlayerChoice.getValue());
    }

    private void setupModesGrid() {
        final List<GameType> validModes = Arrays.stream(GameType.values()).filter(this::isPlayableGameType)
                .collect(Collectors.toList());
        IntStream.range(0, validModes.size()).mapToObj(i -> new Pair<>(i, this.gameTypeToStackPane(validModes.get(i))))
                .forEach(x -> this.addStackPaneToGrid(x.getX(), x.getY()));
    }

    private boolean isPlayableGameType(final GameType gameType) {
        return !gameType.equals(GameType.CHESS_PROBLEM) && !gameType.equals(GameType.CUSTOM_BOARD_VARIANT);
    }

    private void onModeClick(final GameType gameType) {
        this.getSetupController().setGameType(gameType);
        this.selectedGameType = gameType;
        this.modeInfoTitle.setText(gameType.getName());
        this.modeInfoDescription.setText(gameType.getDescription());
        this.onSelectedGameTypeChange();
    }

    private StackPane gameTypeToStackPane(final GameType gameType) {
        final Button btn = new Button(gameType.getName());
        btn.setOnMouseClicked(e -> this.onModeClick(gameType));
        final StackPane p = new StackPane(btn);
        p.getStyleClass().add("mode-tab");
        return p;
    }

    private void addStackPaneToGrid(final int number, final StackPane pane) {
        final String[] colors = { "first", "second" };
        pane.getStyleClass().add(colors[number % 2]);
        this.grid.add(pane, number % 3, number / 3);
        GridPane.setHalignment(pane, HPos.CENTER);
        GridPane.setValignment(pane, VPos.CENTER);
    }

    private void setupBindings() {
        this.container.minWidthProperty().bind(this.scrollpane.widthProperty());
        this.grid.minWidthProperty().bind(this.container.widthProperty());
    }


    @Override
    public void init() {
        this.container.getChildren().add(grid);
        this.setupBindings();
        this.setupTimer();
        this.setupWhitePlayerChoice();
        this.setupModesGrid();
        this.setupDefaultValues();


        whitePlayerChoice.setVisible(false);
        whitePlayerChoice.setDisable(true);

    }

    @FXML
    public void onSelectClick(final ActionEvent event) {
        this.getSetupController().createMatch();
        PageLoader.getInstance().switchPage(this.getStage(), Pages.MATCH,
                this.getController().getModel());
    }

    @FXML
    public void onBackClick(final ActionEvent event) {
        PageLoader.getInstance().switchPage(this.getStage(), Pages.SELECT_GAME,
                this.getController().getModel());
    }

    private SetupController getSetupController() {
        return (SetupController) this.getController();
    }

}
