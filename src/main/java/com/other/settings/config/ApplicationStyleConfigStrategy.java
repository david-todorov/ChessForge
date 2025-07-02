package com.other.settings.config;

import com.other.DirectoryConfigurations;

import java.nio.file.Path;




public final class ApplicationStyleConfigStrategy implements ConfigurationObjectStrategy {

    private final String fileName;

    public ApplicationStyleConfigStrategy(final String filename) {
        this.fileName = filename;
    }

    @Override
    public Path getPath() {
        return Path.of(DirectoryConfigurations.APPLICATION_STYLE_PATH);
    }

    @Override
    public String getName() {
        return getFileName().substring(0, getFileName().length() - 4);
    }

    @Override
    public String getFileName() {
        return fileName;
    }

    @Override
    public Path getFilePath() {
        return Path.of(this.getPath() + "/" + this.getFileName());
    }

    @Override
    public String toString() {
        return this.getName();
    }

}
