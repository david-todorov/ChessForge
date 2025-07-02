package com;

import com.controllers.login.LoginController;
import com.controllers.login.LoginControllerImpl;
import com.model.ApplicationInstance;
import com.model.Model;
import com.views.login.CommandLineLoginView;

public final class Launcher {

    private static final String COMMAND_LINE_PARAMETER = "-cmd";

    private Launcher() {

    }

    private static void startCommandLine() {
        final Model instance = new ApplicationInstance();

        final LoginController loginController = new LoginControllerImpl();
        loginController.setModel(instance);

        final CommandLineLoginView view = new CommandLineLoginView();
        view.setController(loginController);

        loginController.setView(view);
        view.run();
    }

    private static void startJavaFx(final String[] args) {
        ChessForgeApplication.main(args);
    }

    public static void main(final String[] args) {
        if (args.length > 0 && COMMAND_LINE_PARAMETER.equals(args[0])) {
            startCommandLine();
        } else {
            startJavaFx(args);
        }
    }

}
