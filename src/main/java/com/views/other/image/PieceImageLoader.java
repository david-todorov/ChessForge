package com.views.other.image;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import com.model.piece.Piece;
import com.model.piece.PieceType;
import com.model.player.PlayerColor;
import com.other.Pair;
import com.other.settings.SettingMediator;
import com.other.settings.media.style.PieceStyle;
import javafx.scene.image.Image;


public final class PieceImageLoader {


    private final Map<Pair<PieceType, PlayerColor>, Image> pieceImageMapper = new HashMap<>();

    public PieceImageLoader() {
        Arrays.stream(PieceType.values()).forEach(pieceType -> {

            try {

                final Image whitePieceImage = new Image(
                        PieceStyle.getPieceStylePath(SettingMediator.getSavedPieceStyle(), pieceType, PlayerColor.WHITE)
                                .toUri().toString());
                this.pieceImageMapper.put(new Pair<>(pieceType, PlayerColor.WHITE), whitePieceImage);
                final Image blackPieceImage = new Image(
                        PieceStyle.getPieceStylePath(SettingMediator.getSavedPieceStyle(), pieceType, PlayerColor.BLACK)
                                .toUri().toString());
                this.pieceImageMapper.put(new Pair<>(pieceType, PlayerColor.BLACK), blackPieceImage);
            } catch (IOException e) {
                e.printStackTrace();
            }

        });
    }

    public Image getPieceImage(final Piece piece) {
        return this.pieceImageMapper.get(new Pair<>(piece.getType(), piece.getPlayer().getColor()));
    }
}
