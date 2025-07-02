package com.controllers.replay;

import java.util.Optional;

import com.controllers.Controller;
import com.model.board.Board;
import com.model.user.User;


public interface ReplayController extends Controller {


    Board getFirstBoard();


    User getWhiteUser();


    User getBlackUser();


    Optional<Board> getPreviousBoard();


    Optional<Board> getNextBoard();

}
