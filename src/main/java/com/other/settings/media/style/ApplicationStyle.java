package com.other.settings.media.style;

import java.nio.file.Path;
import java.util.Optional;

import com.other.settings.config.ApplicationStyleConfigStrategy;
import com.other.settings.filegetter.ApplicationStyleListStrategy;

public final class ApplicationStyle {

    private static ApplicationStyleListStrategy applicationStyleList = new ApplicationStyleListStrategy();
    private static ApplicationStyleConfigStrategy currentStyle = applicationStyleList.getAll().stream()
            .filter(e -> "ocean".contentEquals(e.getName())).findAny().get();

    private ApplicationStyle() {

    }

    public static void setApplicationStyle(final ApplicationStyleConfigStrategy style) {
        if (applicationStyleList.getAll().contains(style)) {
            currentStyle = style;
        }
    }

    public static ApplicationStyleConfigStrategy getApplicationStyle() {
        return currentStyle;
    }

    public static Path getCurrentApplicationStylePath() {
        return getApplicationStylePath(currentStyle).get();
    }

    public static Optional<Path> getApplicationStylePath(final ApplicationStyleConfigStrategy style) {
        if (applicationStyleList.getAll().contains(style)) {
            return Optional.of(style.getFilePath());
        }
        return Optional.empty();
    }

}
