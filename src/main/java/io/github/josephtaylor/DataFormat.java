package io.github.josephtaylor;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Enumerated type representing the different supported data types
 * and their extensions.
 */
public enum DataFormat {
	XML("xml"),
	JSON("json");

	private static final Map<String, DataFormat> lookupMap;
	private final String extension;

	DataFormat(String extension) {
		this.extension = extension;
	}

	/**
	 * Returns the <code>DataFormat</code> type for the given file extension.
	 *
	 * @param extension String representation of the file extension
	 * @return the <code>DataFormat</code> for the given file extension.
	 */
	public static DataFormat forExtension(final String extension) {
		DataFormat dataFormat = lookupMap.get(extension);
		if (null == dataFormat) {
			throw new IllegalArgumentException(String.format("The format with extension %s is not supported", extension));
		}
		return dataFormat;
	}

	/**
	 * Returns the extension.
	 *
	 * @return the extension
	 */
	public String extension() {
		return extension;
	}

	static {
		lookupMap = Arrays.stream(DataFormat.values()).collect(Collectors.toMap(DataFormat::extension, Function.identity()));
	}
}
