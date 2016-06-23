package io.github.josephtaylor.marshal;

import java.io.File;

/**
 * The {@code TransformationBuilder} interface allows for
 * transforming files and objects among data formats.
 * It is designed to be used along side {@link Marshal}.
 * Usage examples:<br><br>
 * <p>
 * <code>marshal.transform("someFile.xml").from("xml").to("json").andSaveTo("someFile.json);</code>
 * <code>marshal.transform(aJavaObject).to(DataFormat.JSON).getString();</code><br><br>
 * <p>
 * Supports the use of {@link DataFormat} objects to specify formats
 * or their corresponding extension Strings.
 */
public interface TransformationBuilder {
    /**
     * Saves the transformed object to the given file.
     *
     * @param filename the full path of the file or the
     *                 relative path to the data folder.
     */
    void andSaveTo(String filename);

    /**
     * Saves the transformed object to the given file.
     *
     * @param file the file to use.
     */
    void andSaveTo(File file);

    /**
     * If a file is being read it is unmarshaled here.
     *
     * @param dataFormat the file extension of the data format.
     * @return this {@code TransformationBuilder}.
     */
    TransformationBuilder from(String dataFormat);

    /**
     * If a file is being read it is unmarshaled here.
     *
     * @param dataFormat the data format of the file.
     * @return this {@code TransformationBuilder}.
     */
    TransformationBuilder from(DataFormat dataFormat);

    /**
     * Returns the marshaled String representation of
     * the transformed object.
     *
     * @return the String
     */
    String getString();

    /**
     * Sets the type of the object to be unmarshaled.
     *
     * @param type the type of the object being transformed.
     * @return the {@code TransformationBuilder}
     */
    TransformationBuilder ofType(final Class<?> type);

    /**
     * marshals the object to the given format.
     *
     * @param dataFormat the data format.
     * @return this {@code TransformationBuilder}.
     */
    TransformationBuilder to(DataFormat dataFormat);

    /**
     * marshals the object to the given format.
     *
     * @param dataFormat the extension of the file format.
     * @return this {@code TransformationBuilder}.
     */
    TransformationBuilder to(String dataFormat);
}
