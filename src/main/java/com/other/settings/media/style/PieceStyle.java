package com.other.settings.media.style;

import java.nio.file.Path;

import com.other.settings.config.PieceStyleConfigStrategy;
import com.other.settings.filegetter.PieceStyleListStrategy;
import com.model.piece.PieceType;
import com.model.player.PlayerColor;


public final class PieceStyle {


    private static PieceStyleListStrategy pieceStyleList = new PieceStyleListStrategy();


    private static PieceStyleConfigStrategy currentStyle = pieceStyleList.getAll().stream()
            .filter(e -> "shadow".contentEquals(e.getName())).findAny().get();

    private PieceStyle() {
    }


    public static void setPieceStyle(final PieceStyleConfigStrategy style) {
        currentStyle = style;
    }


    public static PieceStyleConfigStrategy getPieceStyle() {
        return currentStyle;
    }


    public static Path getPieceStylePath() {
        return currentStyle.getPath();
    }

    public static Path getCurrentPieceStylePath(final PieceType piece, final PlayerColor pieceColor) {

        return getPieceStylePath(currentStyle, piece, pieceColor);
    }

    public static Path getPieceStylePath(final PieceStyleConfigStrategy style, final PieceType piece,
            final PlayerColor pieceColor) {
        return Path.of(style.getFilePath() + "/" + pieceColor.toString().charAt(0) + "_" + piece.toString() + ".png");
    }

}
