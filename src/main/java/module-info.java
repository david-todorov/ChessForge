module com.chessforge {
    // JavaFX modules
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;

    // Third-party libs
    requires org.controlsfx.controls;
    requires one.util.streamex;
    requires com.google.common;
    requires com.google.gson;
    requires reflections;

    // Open FXML-related packages to JavaFX for runtime reflection
    opens com.controllers to javafx.fxml;
    opens com.controllers.editor to javafx.fxml;
    opens com.controllers.home to javafx.fxml;
    opens com.controllers.leaderboard to javafx.fxml;
    opens com.controllers.loading to javafx.fxml;
    opens com.controllers.login to javafx.fxml;
    opens com.controllers.match to javafx.fxml;
    opens com.controllers.problem to javafx.fxml;
    opens com.controllers.settings to javafx.fxml;
    opens com.controllers.setup to javafx.fxml;


    opens com.views.editor to javafx.fxml;
    opens com.views.home to javafx.fxml;
    opens com.views.interfaces to javafx.fxml;
    opens com.views.leaderboard to javafx.fxml;
    opens com.views.loading to javafx.fxml;
    opens com.views.login to javafx.fxml;
    opens com.views.match to javafx.fxml;
    opens com.views.newgame to javafx.fxml;

    opens com.views.pages to javafx.fxml;
    opens com.views.problem to javafx.fxml;
    opens com.views.selectgame to javafx.fxml;
    opens com.views.settings to javafx.fxml;
    opens com.views.setup to javafx.fxml;
    opens com.views.splash to javafx.fxml;
    opens com.views.other.board to javafx.fxml;

    opens com.views.other.board.strategy.history to javafx.fxml;
    opens com.views.other.board.strategy.movement to javafx.fxml;
    opens com.views.other.component to javafx.fxml;
    opens com.views.other.image to javafx.fxml;

    // Export everything to keep it simple for university
    exports com;
    exports com.controllers;
    exports com.controllers.editor;
    exports com.controllers.home;
    exports com.controllers.leaderboard;
    exports com.controllers.loading;
    exports com.controllers.login;
    exports com.controllers.match;
    exports com.controllers.problem;
    exports com.controllers.settings;
    exports com.controllers.setup;

    exports com.model;
    exports com.model.board;
    exports com.model.editor;
    exports com.model.game;
    exports com.model.history;
    exports com.model.leaderboard;
    exports com.model.match;
    exports com.model.movement;
    exports com.model.piece;
    exports com.model.player;
    exports com.model.problems;
    exports com.model.timer;
    exports com.model.user;
    exports com.model.board.builders;
    exports com.model.board.factory;
    exports com.model.board.position;
    exports com.model.game.builders;
    exports com.model.game.controllers;
    exports com.model.game.factory;
    exports com.model.game.types;
    exports com.model.leaderboard.adapter;
    exports com.model.leaderboard.builder;
    exports com.model.leaderboard.strategy;
    exports com.model.movement.manager;
    exports com.model.piece.factory;
    exports com.model.piece.movements;
    exports com.model.player.pair;
    exports com.model.timer.factory;
    exports com.model.user.datastorage;
    exports com.model.user.management;
    exports com.model.user.validators;

    exports com.other;
    exports com.other.console;
    exports com.other.functional;
    exports com.other.settings;
    exports com.other.settings.config;
    exports com.other.settings.filegetter;
    exports com.other.settings.storage;
    exports com.other.settings.media.sound;
    exports com.other.settings.media.style;

    exports com.views.editor;
    exports com.views.home;
    exports com.views.interfaces;
    exports com.views.leaderboard;
    exports com.views.loading;
    exports com.views.login;
    exports com.views.match;
    exports com.views.newgame;
    exports com.views.pages;
    exports com.views.problem;
    exports com.views.selectgame;
    exports com.views.settings;
    exports com.views.setup;
    exports com.views.splash;
    exports com.views.other.board;
    exports com.views.other.component;
    exports com.views.other.image;
    exports com.views.other.board.strategy.history;
    exports com.views.other.board.strategy.movement;
}
