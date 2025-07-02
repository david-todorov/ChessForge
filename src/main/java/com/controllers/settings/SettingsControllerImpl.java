package com.controllers.settings;

import com.controllers.BasicController;
import com.other.settings.SettingMediator;
import com.other.settings.config.ApplicationStyleConfigStrategy;
import com.other.settings.config.PieceStyleConfigStrategy;
import com.other.settings.media.sound.Sound;
import com.other.settings.media.style.ApplicationStyle;
import com.other.settings.media.style.PieceStyle;

import java.io.IOException;

public final class SettingsControllerImpl extends BasicController implements SettingsController {


    @Override
    public void setApplicationStyle(final ApplicationStyleConfigStrategy style) {
        try {
            SettingMediator.setAndSaveApplicationStyle(style);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    @Override
    public ApplicationStyleConfigStrategy getCurrentApplicationStyle() {
        try {
            return SettingMediator.getSavedApplicatioStyle();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ApplicationStyle.getApplicationStyle();
    }


    @Override
    public void setPieceStyle(final PieceStyleConfigStrategy style) {
        try {
            SettingMediator.setAndSavePieceStyle(style);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public PieceStyleConfigStrategy getCurrentPieceStyle() {
        try {
            return SettingMediator.getSavedPieceStyle();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return PieceStyle.getPieceStyle();
    }


    @Override
    public void setApplicationVolume(final double volume) {
        try {
            SettingMediator.setAndSaveSoundVolume(volume);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    @Override
    public double getCurrentApplicationVolume() {
        try {
            return SettingMediator.getSavedSoundVolume();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Sound.getVolume();
    }

}