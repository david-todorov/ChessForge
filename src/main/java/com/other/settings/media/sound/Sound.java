package com.other.settings.media.sound;

import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.Map;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public final class Sound {

    private static final String PATH_START = "/sounds/";
    private static final String PATH_END = ".mp3";
    private static final Map<SoundsEnum, Media> SOUNDS_CACHE;
    private static double volume = 1;

    static {
        SOUNDS_CACHE = new EnumMap<>(SoundsEnum.class);
        Arrays.stream(SoundsEnum.values()).forEach(e -> {
            final Media mediaSound = new Media(
                    Sound.class.getResource(PATH_START + e.getSoundFileName() + PATH_END).toExternalForm()
            );

            SOUNDS_CACHE.put(e, mediaSound);
        });
    }

    private Sound() {
    }

    public static synchronized void play(final SoundsEnum sound) {
        final MediaPlayer mediaPlayer = new MediaPlayer(SOUNDS_CACHE.get(sound));
        mediaPlayer.setVolume(volume);
        mediaPlayer.play();
        mediaPlayer.setOnEndOfMedia(mediaPlayer::dispose);
    }

    public static double getVolume() {
        return volume;
    }

    public static void setVolume(final double volumeLevel) {
        volume = volumeLevel;
    }

}
