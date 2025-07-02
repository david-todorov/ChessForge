package com.controllers.history;

import java.util.List;

import com.controllers.Controller;
import com.model.replay.ReplayData;


public interface HistoryController extends Controller {


    List<ReplayData> getAllSavedReplaysOrdered();

    void setReplay(ReplayData replay);
}
