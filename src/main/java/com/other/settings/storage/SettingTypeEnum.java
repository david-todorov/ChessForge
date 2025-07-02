package com.other.settings.storage;


public enum SettingTypeEnum {

    APPLICATION_STYLE("application_style"),

    PIECES_STYLE("piece_style"),

    SOUND_VOLUME("sound_volume");


    private final String settingName;


    SettingTypeEnum(final String settingName) {
        this.settingName = settingName;
    }


    public String getSettingName() {
        return this.settingName;
    }

}
