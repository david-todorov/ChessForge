package com.controllers.history;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.controllers.BasicController;
import com.model.replay.ReplayData;
import com.model.replay.SavedReplay;
import com.model.replay.SavedReplayImpl;


public final class HistoryControllerImpl extends BasicController implements HistoryController {

    private final SavedReplay savedMatch = new SavedReplayImpl();


    @Override
    public List<ReplayData> getAllSavedReplaysOrdered() {
        return this.savedMatch.getAllBoards().stream().sorted(Comparator.comparing(ReplayData::getDate))
                .collect(Collectors.toList());
    }

    @Override
    public void setReplay(final ReplayData boards) {
        this.getModel().setReplay(boards);
    }

}
