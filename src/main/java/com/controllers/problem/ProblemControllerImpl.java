package com.controllers.problem;

import com.controllers.BasicController;
import com.controllers.setup.WhitePlayerChoice;
import com.model.game.Game;
import com.model.game.factory.GameFactoryImpl;
import com.model.match.Match;
import com.model.match.MatchImpl;
import com.model.player.pair.PlayerPair;
import com.model.problems.Problem;
import com.model.problems.Problems;
import com.model.timer.DefaultTimers;
import com.model.user.management.UsersManager;

public final class ProblemControllerImpl extends BasicController implements ProblemController {

    private Problems problem;
    private final WhitePlayerChoice playerChoice = WhitePlayerChoice.FIRST_USER;
    private final DefaultTimers timer = DefaultTimers.NO_LIMIT;


    @Override
    public void setProblem(final Problems problem) {
        this.problem = problem;
    }


    @Override
    public boolean createMatch() {
        if (this.problem == null) {
            return false;
        }

        final PlayerPair players = this.playerChoice.getPlayers(this.getModel().getFirstUser().get(),
                UsersManager.COMPUTER);

        final Problem chessProblem = this.problem.getChessProblem(players);

        final Game chessGameType = new GameFactoryImpl().chessProblemGameType(players, chessProblem);

        final Match match = new MatchImpl(chessGameType, this.timer.getTimer(players));

        this.getModel().setMatch(match);
        return true;

    }

}
