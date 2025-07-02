package com.other.settings.filegetter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Set;


import com.other.DirectoryConfigurations;
import org.reflections.Reflections;
import org.reflections.scanners.ResourcesScanner;

public abstract class PathFromDirectory implements ConfigurationListStrategy {
    private static final int BUFFER = 1024;

    public List<String> getDirectoryContent(final String folderPath, final String resourcesRootFolder) {
        try {
            DirectoryConfigurations.validateResourcesDirectory();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.fileValidator(resourcesRootFolder);

        final File folder = new File(folderPath);
        final String[] files = folder.list();
        return files == null ? List.of() : Arrays.asList(files);

    }

    private void fileValidator(final String folderPath) {
        final Reflections reflections = new Reflections(folderPath, new ResourcesScanner());
        final Set<String> resourceFileList = reflections.getResources(x -> true);
        resourceFileList.forEach(resourceFile -> {

            try {
                if (!Files.exists(
                        Path.of(Path.of(DirectoryConfigurations.RESOURCES_DIRECTORY_PATH + resourceFile).toUri()))) {

                    copy(ClassLoader.getSystemResourceAsStream(resourceFile),
                            new FileOutputStream(DirectoryConfigurations.RESOURCES_DIRECTORY_PATH + resourceFile));

                }
            } catch (IOException e) {

                e.printStackTrace();
            }

        });
    }

    private static void copy(final InputStream instream, final FileOutputStream outstream) {

        try {

            final byte[] buffer = new byte[BUFFER];

            int length;

            for (length = instream.read(buffer); length > 0; length = instream.read(buffer)) {
                outstream.write(buffer, 0, length);
            }

            // Closing the input/output file streams
            instream.close();
            outstream.close();

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
