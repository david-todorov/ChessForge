package com.other.settings;

import java.io.IOException;

import com.other.settings.config.ApplicationStyleConfigStrategy;
import com.other.settings.config.PieceStyleConfigStrategy;
import com.other.settings.media.sound.Sound;
import com.other.settings.media.style.ApplicationStyle;
import com.other.settings.media.style.PieceStyle;
import com.other.settings.storage.ApplicationStyleDateStorageJsonStrategy;
import com.other.settings.storage.PiecesStyleDateStorageJsonStrategy;
import com.other.settings.storage.SettingsDataStorageJsonStrategy;
import com.other.settings.storage.SoundDateStorageStrategy;


public final class SettingMediator {


    private static SettingsDataStorageJsonStrategy<ApplicationStyleConfigStrategy> applicationStyleJson = new ApplicationStyleDateStorageJsonStrategy();


    private static SettingsDataStorageJsonStrategy<PieceStyleConfigStrategy> pieceStyleJson = new PiecesStyleDateStorageJsonStrategy();


    private static SettingsDataStorageJsonStrategy<Double> soundVolumeJson = new SoundDateStorageStrategy();


    private SettingMediator() {
    }


    public static void setAndSaveApplicationStyle(final ApplicationStyleConfigStrategy style) throws IOException {
        ApplicationStyle.setApplicationStyle(style);
        applicationStyleJson.setSetting(style);
    }


    public static void setAndSavePieceStyle(final PieceStyleConfigStrategy style) throws IOException {
        PieceStyle.setPieceStyle(style);
        pieceStyleJson.setSetting(style);
    }

    public static void setAndSaveSoundVolume(final double volume) throws IOException {
        Sound.setVolume(volume);
        soundVolumeJson.setSetting(volume);
    }


    public static ApplicationStyleConfigStrategy getSavedApplicatioStyle() throws IOException {
        if (applicationStyleJson.getSetting().isEmpty()) {
            setAndSaveApplicationStyle(ApplicationStyle.getApplicationStyle());
        }
        return applicationStyleJson.getSetting().get();
    }


    public static PieceStyleConfigStrategy getSavedPieceStyle() throws IOException {
        if (pieceStyleJson.getSetting().isEmpty()) {
            setAndSavePieceStyle(PieceStyle.getPieceStyle());
        }
        return pieceStyleJson.getSetting().get();
    }

    public static double getSavedSoundVolume() throws IOException {
        if (soundVolumeJson.getSetting().isEmpty()) {
            setAndSaveSoundVolume(Sound.getVolume());
        }
        return soundVolumeJson.getSetting().get();
    }

}
