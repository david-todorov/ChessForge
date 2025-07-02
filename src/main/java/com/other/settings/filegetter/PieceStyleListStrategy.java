package com.other.settings.filegetter;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.other.DirectoryConfigurations;
import com.other.settings.config.PieceStyleConfigStrategy;

public final class PieceStyleListStrategy extends PathFromDirectory {

    @Override
    public Path getFolderPath() {
        return Path.of(DirectoryConfigurations.PIECE_STYLE_PATH);
    }

    @Override
    public List<Path> getAllPath() {
        return this.getAll().stream().map(e -> e.getFilePath()).collect(Collectors.toList());

    }

    @Override
    public List<PieceStyleConfigStrategy> getAll() {
        final List<PieceStyleConfigStrategy> piecesStyleList = new ArrayList<>();

        this.getDirectoryContent(this.getFolderPath().toString(), "piece").stream()
                .filter(elem -> Files.isDirectory(Path.of(this.getFolderPath().toString().concat("/").concat(elem))))
                .forEach(elem -> piecesStyleList.add(new PieceStyleConfigStrategy(elem)));
        return piecesStyleList;

    }

}
