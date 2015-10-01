package io.github.josephtaylor;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * This is the implementation of the {@link FileHandler} interface.
 */
public class FileHandlerImpl implements FileHandler {

	private static final Charset UTF_8 = Charset.forName("UTF-8");

	@Override
	public String readFile(String filename) {
		try {
			return new String(Files.readAllBytes(Paths.get(filename)), UTF_8);
		} catch (IOException e) {
			throw new RuntimeException(String.format("Unable to load %s: %s", filename, e.getMessage()), e);
		}
	}

	@Override
	public String readFile(File file) {
		try {
			return new String(Files.readAllBytes(Paths.get(file.getAbsolutePath())), UTF_8);
		} catch (IOException e) {
			throw new RuntimeException(String.format("Unable to load %s: %s", file.getName(), e.getMessage()), e);
		}
	}

	@Override
	public void saveFile(String filename, String content) {
		try {
			Files.createDirectories(Paths.get(filename).getParent());
			Files.write(Paths.get(filename), content.getBytes(UTF_8));
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
}
