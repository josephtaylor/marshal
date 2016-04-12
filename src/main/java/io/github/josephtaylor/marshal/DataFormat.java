package io.github.josephtaylor.marshal;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Enumerated type representing the different supported data types
 * and their extensions.
 */
public enum DataFormat {
    XML("xml"),
    JSON("json"),
    YAML("yaml");

    private static final String ERROR_MESSAGE = "The format with extension %s is not supported";
    private static final Map<String, DataFormat> LOOKUP_MAP;

    static {
        LOOKUP_MAP = Stream.of(DataFormat.values())
                .collect(Collectors.toMap(DataFormat::extension, Function.identity()));
    }

    private final String extension;

    DataFormat(final String extension) {
        this.extension = extension;
    }

    /**
     * Returns the <code>DataFormat</code> type for the given file extension.
     *
     * @param extension String representation of the file extension
     * @return the <code>DataFormat</code> for the given file extension.
     */
    public static DataFormat forExtension(final String extension) {
        DataFormat dataFormat = LOOKUP_MAP.get(extension);
        if (null == dataFormat) {
            throw new IllegalArgumentException(String.format(ERROR_MESSAGE, extension));
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
}
