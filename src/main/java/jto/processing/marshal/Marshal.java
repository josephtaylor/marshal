package jto.processing.marshal;

import java.io.File;

/**
 * Main interface of the MarshalP5 library.
 * Allows for saving / loading objects to various data formats
 * and transforming them among formats.
 */
public interface Marshal {
    /**
     * Save the given object to the given file.
     * Format is determined by the extension of the file name.
     *
     * @param obj  the object to be saved.
     * @param file the file to which the object will be saved.
     */
    void save(Object obj, File file);

    /**
     * Saves the object to the file using the specified format.
     *
     * @param obj        the object to be saved.
     * @param file       the file where the object is to be saved.
     * @param dataFormat the file format.
     */
    void save(Object obj, File file, DataFormat dataFormat);

    /**
     * Saves the object to the file using the specified format.
     *
     * @param obj        the object to be saved.
     * @param filename   the full filepath or the relative path
     *                   to the data folder.
     * @param dataFormat the file format.
     */
    void save(Object obj, String filename, DataFormat dataFormat);

    /**
     * Loads the file to an object.
     * Format is determined by the extension on the file.
     *
     * @param file the file to be loaded.
     * @return the object(s) contained in the file.
     */
    Object load(File file);

    /**
     * Loads the file to an object.
     *
     * @param file       the file to be loaded.
     * @param dataFormat the file format of the file.
     * @return the object(s) contained in the file.
     */
    Object load(File file, DataFormat dataFormat);

    /**
     * Loads the file to an object.
     *
     * @param filename   the full path of the file or the
     *                   relative path of the file to the data folder
     * @param dataFormat the file format of the file.
     * @return the object(s) contained in the file.
     */
    Object load(String filename, DataFormat dataFormat);

    /**
     * Loads the file to an object of type {@link T}.
     * File format is determined by the extension of the file name.
     *
     * @param filename the full path of the file or the
     *                 relative path of the file to the data folder.
     * @param type     the type of the object to be loaded.
     * @param <T>      the type of the object to be loaded.
     * @return the object(s) contained in the file.
     */
    <T> T load(String filename, Class<T> type);

    /**
     * Loads the file to an object of type {@link T}.
     *
     * @param filename   the full path of the file or the
     *                   relative path of the file to the data folder.
     * @param type       the type of the object to be loaded.
     * @param dataFormat the file format of the file.
     * @param <T>        the type of the object to be loaded.
     * @return the object(s) contained in the file.
     */
    <T> T load(String filename, Class<T> type, DataFormat dataFormat);

    /**
     * Converts the given String to an object.
     *
     * @param marshaledObject the marshaled String representation of the object
     * @param dataFormat      the file format of the marshaled String object.
     * @return the unmarshaled object.
     */
    Object unmarshal(String marshaledObject, DataFormat dataFormat);

    /**
     * Converts the given String to an object of type {@link T}.
     *
     * @param marshaledObject the marshaled String representation of the object
     * @param type            the type of the marshaled object to be returned.
     * @param dataFormat      the data format of the marshaled object.
     * @param <T>             the type of the marshaled object
     * @return the unmarshaled object.
     */
    <T> T unmarshal(String marshaledObject, Class<T> type, DataFormat dataFormat);

    /**
     * Converts the given object to a marshaled String representation in
     * the given data format.
     *
     * @param obj        the object to be marshaled.
     * @param dataFormat the data format.
     * @return a marshaled String representation of the object.
     */
    String marshal(Object obj, DataFormat dataFormat);

    /**
     * Returns a {@link TransformationBuilder} for the given file.
     *
     * @param filename the full path of the file or the
     *                 path relative to the data folder.
     * @return the {@link TransformationBuilder} for performing a transformation.
     */
    TransformationBuilder transformFile(String filename);

    /**
     * Returns a {@link TransformationBuilder} for the given file.
     *
     * @param file the file to be transformed.
     * @return the {@link TransformationBuilder} for performing a transformation.
     */
    TransformationBuilder transformFile(File file);

    /**
     * Returns a {@link TransformationBuilder} for the given object.
     *
     * @param object the object to be transformed.
     * @return the {@link TransformationBuilder} for performing a transformation.
     */
    TransformationBuilder transform(Object object);
}
