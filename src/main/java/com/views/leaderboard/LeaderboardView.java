package com.views.leaderboard;

import com.controllers.leaderboard.LeaderboardController;
import com.model.leaderboard.adapter.LeaderboardUserAdapter;
import com.views.interfaces.AbstractJavaFXView;
import com.views.pages.PageLoader;
import com.views.pages.Pages;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.io.IOException;

public final class LeaderboardView extends AbstractJavaFXView {

    @FXML
    private TableView<LeaderboardUserAdapter> table;

    @Override
    public void init() {

        final TableColumn<LeaderboardUserAdapter, String> nameColumn = new TableColumn<>("Username");
        nameColumn.setCellValueFactory(u -> new SimpleStringProperty(u.getValue().getUsername()));

        final TableColumn<LeaderboardUserAdapter, Integer> winColumn = new TableColumn<>("Wins");
        winColumn.setCellValueFactory(u -> new SimpleIntegerProperty(u.getValue().getWinCount()).asObject());

        final TableColumn<LeaderboardUserAdapter, Integer> drawColumn = new TableColumn<>("Draws");
        drawColumn.setCellValueFactory(u -> new SimpleIntegerProperty(u.getValue().getDrawCount()).asObject());

        final TableColumn<LeaderboardUserAdapter, Integer> loseColumn = new TableColumn<>("Loses");
        loseColumn.setCellValueFactory(u -> new SimpleIntegerProperty(u.getValue().getLostCount()).asObject());

        final TableColumn<LeaderboardUserAdapter, Integer> totalMatchColumn = new TableColumn<>("Total Match");
        totalMatchColumn.setCellValueFactory(u -> new SimpleIntegerProperty(u.getValue().getPlayedMatchCount()).asObject());

        final TableColumn<LeaderboardUserAdapter, Integer> score = new TableColumn<>("Score");
        score.setCellValueFactory(u -> new SimpleIntegerProperty(u.getValue().getScore()).asObject());

        table.getColumns().addAll(nameColumn, winColumn, drawColumn, loseColumn, totalMatchColumn, score);

        try {
            this.table.setItems(FXCollections.observableArrayList(this.getLeaderboardController().getUsers()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public LeaderboardController getLeaderboardController() {
        return (LeaderboardController) this.getController();
    }

    @FXML
    public void onBackClick(final ActionEvent event) throws IOException {
        PageLoader.getInstance().switchPage(this.getStage(), Pages.HOME, this.getController().getModel());
    }
}
