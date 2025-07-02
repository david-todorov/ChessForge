package com.other.settings.config;

import java.nio.file.Path;

public interface ConfigurationObjectStrategy {

    Path getPath();

    Path getFilePath();

    String getName();

    String getFileName();

}
