package jto.processing.marshal;

import java.io.File;

/**
 * The {@code TransformationBuilder} interface allows for
 * transformating files and objects among data formats.
 * It is designed to be used along side {@link MarshalP5}.
 * Usage examples:<br/><br/>
 * <p>
 * {@code marshalP5.transform("someFile.xml").from("xml").to("json").andSaveTo("someFile.json);}
 * {@code marshalP5.transform(aJavaObject).to(DataFormat.JSON).getString();}<br/><br/>
 * <p>
 * Supports the use of {@link DataFormat} objects to specify formats
 * or their corresponding extension Strings.
 */
public interface TransformationBuilder {
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
     * marshals the object to the given format.
     *
     * @param dataFormat the extension of the file format.
     * @return this {@code TransformationBuilder}.
     */
    TransformationBuilder to(String dataFormat);

    /**
     * marshals the object to the given format.
     *
     * @param dataFormat the data format.
     * @return this {@code TransformationBuilder}.
     */
    TransformationBuilder to(DataFormat dataFormat);

    /**
     * Returns the marshaled String representation of
     * the transformed object.
     *
     * @return the String
     */
    String getString();

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
}
