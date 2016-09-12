package io.github.josephtaylor.marshal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.Before;
import org.junit.Test;

public class FileHandlerImplUnitTest {

    private FileHandler fileHandler;

    @Before
    public void setup() {
        fileHandler = new FileHandlerImpl();
    }

    @Test
    public void testReadFile_exception() {
        try {
            fileHandler.readFile("non-existent-file.txt");
            fail("An exception should have been thrown");
        } catch (Exception e) {
            assertEquals("Unable to load non-existent-file.txt: java.nio.file.NoSuchFileException: non-existent-file.txt",
                    e.getMessage());
        }
    }

    @Test
    public void testReadFile_file_exception() {
        try {
            fileHandler.readFile(new File("/non-existent-file.txt"));
            fail("An exception should have been thrown");
        } catch (Exception e) {
            assertEquals("Unable to load non-existent-file.txt: java.nio.file.NoSuchFileException: /non-existent-file.txt",
                    e.getMessage());
        }
    }

    @Test
    public void testSaveAndRead() {
        String filename = "/tmp/" + String.valueOf((int) (Math.random() * Integer.MAX_VALUE)) + ".json";
        String content = "{\"test\"}";

        try {
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

    @Test
    public void testSaveAndRead_file() {
        String filename = "/tmp/" + String.valueOf((int) (Math.random() * Integer.MAX_VALUE)) + ".json";
        String content = "{\"test\"}";
        File file = new File(filename);
        try {
            fileHandler.saveFile(file, content);
            assertEquals(content, fileHandler.readFile(file));
        } finally {
            try {
                Files.deleteIfExists(Paths.get(filename));
            } catch (IOException e) {
                throw new RuntimeException("Unable to delete temporary file.", e);
            }
        }
    }

    @Test
    public void testSaveFile_exception() {
        try {
            fileHandler.saveFile("/test.txt", "whatever");
            fail("An exception should have been thrown");
        } catch (Exception e) {
            assertEquals("Unable to save /test.txt: java.nio.file.AccessDeniedException: /test.txt", e.getMessage());
        }
    }

    @Test
    public void testSaveFile_file_exception() {
        try {
            fileHandler.saveFile(new File("/test.txt"), "whatever");
            fail("An exception should have been thrown");
        } catch (Exception e) {
            assertEquals("Unable to save test.txt: java.nio.file.AccessDeniedException: /test.txt", e.getMessage());
        }
    }
}