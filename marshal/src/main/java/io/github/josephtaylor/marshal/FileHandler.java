package io.github.josephtaylor.marshal;

import java.io.File;

/**
 * The <code>FileHandler</code> is responsible for
 * loading/saving files from/to Strings.
 */
public interface FileHandler {
    /**
     * Reads the contents of the given file to a String.
     *
     * @param filename the full path of the file or the
     *                 path relative to the data folder.
     * @return the contents of the file as a String.
     */
    String readFile(String filename);

    /**
     * Reads the contents of the given file to a String.
     *
     * @param file the file that is to be read.
     * @return the contents of the file as a String.
     */
    String readFile(File file);

    /**
     * Saves the content string to the given file.
     *
     * @param filename the full path and file name or
     *                 relative path to the data folder.
     * @param content  the String content of the file to be saved.
     */
    void saveFile(String filename, String content);

    /**
     * Saves the content string to the given file.
     *
     * @param file    the file to which the content is to be saved.
     * @param content the String content of the file to be saved.
     */
    void saveFile(File file, String content);
}
