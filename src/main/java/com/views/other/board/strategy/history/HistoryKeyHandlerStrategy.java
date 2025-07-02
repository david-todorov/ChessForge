package com.views.other.board.strategy.history;

import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

@FunctionalInterface
public interface HistoryKeyHandlerStrategy extends EventHandler<KeyEvent> {

    void handle(KeyEvent event);
}
