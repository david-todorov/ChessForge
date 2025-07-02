package com.other.settings.config;

import java.nio.file.Path;

import com.other.DirectoryConfigurations;

public final class PieceStyleConfigStrategy implements ConfigurationObjectStrategy {

    private final String folderName;

    public PieceStyleConfigStrategy(final String directoryName) {
        this.folderName = directoryName;
    }

    @Override
    public Path getPath() {
        return Path.of(DirectoryConfigurations.PIECE_STYLE_PATH);
    }

    @Override
    public String getName() {
        return folderName;
    }

    @Override
    public String getFileName() {
        return folderName;
    }

    @Override
    public Path getFilePath() {
        return Path.of(getPath() + "/" + folderName);
    }

    @Override
    public String toString() {
        return this.getName();
    }

}
