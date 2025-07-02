package com.views.pages;

import com.controllers.BasicController;
import com.controllers.Controller;
import com.controllers.editor.EditorControllerImpl;
import com.controllers.home.HomeControllerImpl;
import com.controllers.leaderboard.LeaderboardControllerImpl;
import com.controllers.loading.LoadingControllerImpl;
import com.controllers.login.LoginControllerImpl;
import com.controllers.match.MatchControllerImpl;
import com.controllers.problem.ProblemControllerImpl;
import com.controllers.settings.SettingsControllerImpl;
import com.controllers.setup.SetupControllerImpl;

import java.util.function.Supplier;

public enum Pages {

    /**
     * Loading screen.
     */
    LOADING("loading", LoadingControllerImpl::new),

    /**
     * Splash page.
     */
    SPLASH("splash", BasicController::new),

    /**
     * Home page.
     */
    HOME("home", HomeControllerImpl::new),

    /**
     * Leaderboard page.
     */
    LEADERBOARD("leaderboard", LeaderboardControllerImpl::new),


    /**
     * Settings page.
     */
    SETTINGS("settings", SettingsControllerImpl::new),

    /**
     * New Game page.
     */
    NEWGAME("newgame", BasicController::new),

    /**
     * Select Game page.
     */
    SELECT_GAME("selectgame", BasicController::new),

    /**
     * Game Type Selection page.
     */
    SETUP("setup", SetupControllerImpl::new),

    /**
     * Match page.
     */
    MATCH("match", MatchControllerImpl::new),

    /**
     * Login page.
     */
    LOGIN("login", LoginControllerImpl::new),

    /**
     * Editor page.
     */
    EDITOR("editor", EditorControllerImpl::new),

    /**
     * Problem setup page.
     */
    PROBLEM("problem", ProblemControllerImpl::new);


    private final String name;
    private final Supplier<Controller> controllerGenerator;

    Pages(final String name, final Supplier<Controller> controllerGenerator) {
        this.name = name;
        this.controllerGenerator = controllerGenerator;
    }

    public String getName() {
        return this.name;
    }

    public Controller getNewControllerInstance() {
        return this.controllerGenerator.get();
    }

}
