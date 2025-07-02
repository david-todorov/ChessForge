package com.other.settings.storage;

import java.io.IOException;
import java.util.Optional;

public interface SettingsDataStorageJsonStrategy<T> {

    void setSetting(T value) throws IOException;

    Optional<T> getSetting() throws IOException;
}
