package com.model.replay;

import java.io.IOException;
import java.util.Set;

public interface SavedReplay {

    void save(ReplayData replayData) throws IOException;

    ReplayData getSavedReplay(String matchID);

    Set<ReplayData> getAllBoards();

}
