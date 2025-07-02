package com.other.console;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Arrays;


public final class CommandLineConsole {

    private final BufferedReader reader = new BufferedReader(
            new InputStreamReader(System.in, Charset.defaultCharset()));


    public void clearConsole() {

        final String os = System.getProperty("os.name");

        if (!os.contains("Windows")) {
            System.out.print("\033\143");
        }

    }

    public String readLine(final String format, final Object... args) {

        if (System.console() != null) {
            return System.console().readLine(format, args);
        }

        this.print(String.format(format, args));

        try {
            return this.reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }

    public String readLine() {

        if (System.console() != null) {
            return System.console().readLine();
        }

        try {
            return this.reader.readLine();
        } catch (final IOException e) {
            e.printStackTrace();
        }
        return null;

    }

    public String readPassword(final String format, final Object... args) {
        if (System.console() != null) {
            return String.valueOf(System.console().readPassword(format, args));
        }
        return String.valueOf(readLine(format, args).toCharArray());
    }

    public String readPassword() {
        if (System.console() != null) {
            return Arrays.toString(System.console().readPassword());
        }
        final String line = this.readLine();
        return String.valueOf(line.toCharArray());
    }

    public void println(final String format) {
        System.out.println(format);
    }


    public void print(final String format) {
        System.out.print(format);
    }
}
