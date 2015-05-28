package jto.processing.marshal;

import com.google.common.base.Charsets;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * This is the implementation of the {@link FileHandler} interface.
 */
public class FileHandlerImpl implements FileHandler {
    @Override
    public void saveFile(String filename, String content) {
        try {
            Files.createDirectories(Paths.get(filename).getParent());
            Files.write(Paths.get(filename), content.getBytes(Charsets.UTF_8));
        } catch (IOException e) {
            throw new RuntimeException(String.format("Unable to save %s: %s", filename, e.getMessage()), e);
        }
    }

    @Override
    public void saveFile(File file, String content) {
        try {
            new FileWriter(file).write(content);
        } catch (IOException e) {
            throw new RuntimeException(String.format("Unable to save %s: %s", file.getName(), e.getMessage()), e);
        }
    }

    @Override
    public String readFile(String filename) {
        try {
            return new String(Files.readAllBytes(Paths.get(filename)), Charsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(String.format("Unable to load %s: %s", filename, e.getMessage()), e);
        }
    }

    @Override
    public String readFile(File file) {
        try {
            return new String(Files.readAllBytes(Paths.get(file.getAbsolutePath())), Charsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(String.format("Unable to load %s: %s", file.getName(), e.getMessage()), e);
        }
    }
}
