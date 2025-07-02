package com.other.settings.media.sound;

import com.model.movement.MovementResult;

public enum SoundsEnum {

    MOVE("move"),


    CHECK("check"),


    CHECKMATE("game-end"),


    CAPTURE("capture");

    private String soundFileName;

    SoundsEnum(final String fileName) {
        this.soundFileName = fileName;
    }

    public String getSoundFileName() {
        return soundFileName;
    }

    public static SoundsEnum fromMovementResult(final MovementResult movementResult) {
        switch (movementResult) {
        case CAPTURED:
            return CAPTURE;
        case CHECKED:
            return CHECK;
        case OVER:
            return CHECKMATE;
        case MOVED:
            return MOVE;
        default:
            throw new IllegalArgumentException("Illegal movement to sound conversion");
        }
    }

}
