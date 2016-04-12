package io.github.josephtaylor.marshal;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.Test;

public class FileHandlerImplUnitTest {

    @Test
    public void testSaveAndRead() {
        String filename = "/tmp/" + String.valueOf((int) (Math.random() * Integer.MAX_VALUE)) + ".json";
        String content = "{\"test\"}";

        try {
            FileHandler fileHandler = new FileHandlerImpl();
            fileHandler.saveFile(filename, content);
            assertEquals(content, fileHandler.readFile(filename));
        } finally {
            try {
                Files.deleteIfExists(Paths.get(filename));
            } catch (IOException e) {
                throw new RuntimeException("Unable to delete temporary file.", e);
            }
        }
    }
}