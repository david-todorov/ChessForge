package com.other;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.Optional;


public final class ObjectSerializer {

    private ObjectSerializer() {
    }


    public static Object fromString(final String serializedObject) throws IOException, ClassNotFoundException {
        final byte[] data = Base64.getDecoder().decode(serializedObject);
        final ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(data));
        final Object o = ois.readObject();
        ois.close();
        return o;
    }


    public static String toString(final Serializable object) throws IOException {
        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        final ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(object);
        oos.close();
        return Base64.getEncoder().encodeToString(baos.toByteArray());
    }

    public static Optional<Object> loadFromFile(final String path) {
        try {
            return Optional.of(fromString(Files.readString(Paths.get(path))));
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public static void saveToFile(final Serializable object, final String path) {
        try (FileWriter file = new FileWriter(path, Charset.defaultCharset())) {
            file.write(toString(object));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
