package com.other.settings.storage;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import com.other.DirectoryConfigurations;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.HashMap;
import java.util.Map;

public abstract class SettingDataStorageJson<T> implements SettingsDataStorageJsonStrategy<T> {

    private Map<SettingTypeEnum, String> settingsMap = new HashMap<>();

    private void dateLoader() throws IOException {
        DirectoryConfigurations.validateSettingsDirectory();
        DirectoryConfigurations.validateSettingsDataFile();
        if (this.settingsMap != null) {
            final Gson gson = new GsonBuilder().create();
            final String json = Files.readString(Path.of(DirectoryConfigurations.SETTINGS_DATA_FILE_PATH),
                    StandardCharsets.UTF_8);
            if (!json.isEmpty()) {
                final Type typeOfHashMap = new TypeToken<Map<SettingTypeEnum, String>>() {
                }.getType();
                this.settingsMap = gson.fromJson(json, typeOfHashMap);
            }

        }

    }

    protected void put(final SettingTypeEnum parameter, final String value) throws IOException {
        this.dateLoader();
        this.settingsMap.put(parameter, value);
        final Gson gson = new GsonBuilder().create();
        final String json = gson.toJson(this.settingsMap);
        Files.deleteIfExists(Path.of(DirectoryConfigurations.SETTINGS_DATA_FILE_PATH));
        Files.writeString(Path.of(DirectoryConfigurations.SETTINGS_DATA_FILE_PATH), json, StandardCharsets.UTF_8,
                StandardOpenOption.CREATE);

    }

    protected String getSettingValue(final SettingTypeEnum parameter) throws IOException {
        this.dateLoader();
        return this.settingsMap.get(parameter);

    }
}
