package jto.processing.marshal;

import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.Assert.assertEquals;

public class FileHandlerImplUnitTest {

    @Test
    public void testSaveAndRead() {
        String filename = String.valueOf((int) (Math.random() * Integer.MAX_VALUE)) + ".json";
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