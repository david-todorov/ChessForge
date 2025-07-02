package com.views.editor;

import com.controllers.editor.EditorController;
import com.controllers.setup.WhitePlayerChoice;
import com.model.game.types.GameType;
import com.model.timer.DefaultTimers;
import com.views.interfaces.AbstractJavaFXView;
import com.views.other.board.EditorBoard;
import com.views.pages.PageLoader;
import com.views.pages.Pages;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.io.IOException;

public final class EditorView extends AbstractJavaFXView {

    @FXML
    private VBox whitePiecesSelector;

    @FXML
    private VBox blackPiecesSelector;

    @FXML
    private TextField columnsSelector;

    @FXML
    private TextField rowsSelector;

    @FXML
    private StackPane container;

    private EditorBoard editorBoard;

    @Override
    public void init() {
        this.editorBoard = new EditorBoard(this.getEditorController(), this, this.whitePiecesSelector,
                this.blackPiecesSelector);

        this.columnsSelector.setPromptText("COLS[1-26]:");
        this.rowsSelector.setPromptText("ROWS[1-26]:");

        this.editorBoard.maxWidthProperty()
                .bind(Bindings.min(this.container.widthProperty(), this.container.heightProperty()));
        this.editorBoard.maxHeightProperty()
                .bind(Bindings.min(this.container.widthProperty(), this.container.heightProperty()));

        this.container.getChildren().add(this.editorBoard);

        this.getEditorController().setGameType(GameType.CLASSIC);
        this.getEditorController().setTimer(DefaultTimers.TEN_MINUTES);
        this.getEditorController().setWhitePlayerChoice(WhitePlayerChoice.RANDOM);
    }

    private boolean checkIfInputIsCorrect() {
        try {
            Integer.parseInt(this.columnsSelector.getText());
            Integer.parseInt(this.rowsSelector.getText());
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    @FXML
    public void onBoardSizeChangeClick(final ActionEvent event) {
        if (this.checkIfInputIsCorrect()) {
            this.blackPiecesSelector.getChildren().clear();
            this.whitePiecesSelector.getChildren().clear();
            this.container.getChildren().remove(this.editorBoard);
            this.getEditorController().resetBoard(Integer.parseInt(this.columnsSelector.getText()),
                    Integer.parseInt(this.rowsSelector.getText()));

            this.init();
        }
    }

    @FXML
    public void onBackClick(final ActionEvent event) throws IOException {
        PageLoader.getInstance().switchPage(this.getStage(), Pages.SELECT_GAME,
                this.getEditorController().getModel());
    };

    @FXML
    public void onStartClick(final Event event) throws IOException {
        this.getEditorController().createCustomizedStartingBoard();
        if (this.getEditorController().createMatch()) {
            PageLoader.getInstance().switchPage(this.getStage(), Pages.MATCH,
                    this.getEditorController().getModel());
        }
    };

    public EditorController getEditorController() {
        return (EditorController) this.getController();
    }

}
