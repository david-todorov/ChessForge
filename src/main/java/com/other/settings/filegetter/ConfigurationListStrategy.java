package com.other.settings.filegetter;

import java.nio.file.Path;
import java.util.List;

import com.other.settings.config.ConfigurationObjectStrategy;


public interface ConfigurationListStrategy {

    List<Path> getAllPath();

    List<? extends ConfigurationObjectStrategy> getAll();

    Path getFolderPath();
}
