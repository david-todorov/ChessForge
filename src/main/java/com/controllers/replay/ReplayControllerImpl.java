package com.controllers.replay;

import java.util.Optional;

import com.controllers.BasicController;
import com.model.board.Board;
import com.model.user.User;


public final class ReplayControllerImpl extends BasicController implements ReplayController {

    private static final int FIRST_BOARD_INDEX = 0;
    private int index;


    @Override
    public Optional<Board> getPreviousBoard() {
        return this.index > 0
                ? Optional.of(this.getModel().getReplay().get().getBoards().get(--this.index))
                : Optional.empty();
    }


    @Override
    public Optional<Board> getNextBoard() {
        return this.index < this.getModel().getReplay().get().getBoards().size() - 1
                ? Optional.of(this.getModel().getReplay().get().getBoards().get(++this.index))
                : Optional.empty();
    }


    @Override
    public Board getFirstBoard() {
        return this.getModel().getReplay().get().getBoards().get(FIRST_BOARD_INDEX);

    }

    @Override
    public User getWhiteUser() {
        return this.getModel().getReplay().get().getWhiteUser();
    }

    @Override
    public User getBlackUser() {
        return this.getModel().getReplay().get().getBlackUser();
    }

}
