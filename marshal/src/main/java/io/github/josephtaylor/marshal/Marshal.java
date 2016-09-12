package io.github.josephtaylor.marshal;

import java.io.File;

import processing.core.PApplet;

/**
 * Main class of the MarshalP5 library.
 * Allows for saving / loading objects to various data formats
 * and transforming them among formats.
 */
public class Marshal {

    private final PApplet parent;
    private final Marshallers marshallers;
    private final Unmarshallers unmarshallers;
    private final FileHandler fileHandler;

    public Marshal(final PApplet parent) {
        this.parent = parent;
        marshallers = new Marshallers();
        unmarshallers = new Unmarshallers();
        fileHandler = new FileHandlerImpl();
    }

    /**
     * Loads the file to an object.
     * Format is determined by the extension on the file.
     *
     * @param file the file to be loaded.
     * @return the object(s) contained in the file.
     */
    public Object load(final File file) {
        return load(file, toFormat(file.getName()));
    }

    /**
     * Loads the file to an object.
     *
     * @param file       the file to be loaded.
     * @param dataFormat the file format of the file.
     * @return the object(s) contained in the file.
     */
    public Object load(final File file, final DataFormat dataFormat) {
        return unmarshal(fileHandler.readFile(file), dataFormat);
    }

    /**
     * Loads the file to an object.
     *
     * @param filename   the full path of the file or the
     *                   relative path of the file to the data folder
     * @param dataFormat the file format of the file.
     * @return the object(s) contained in the file.
     */
    public Object load(final String filename, final DataFormat dataFormat) {
        return unmarshal(fileHandler.readFile(parent.dataPath(filename)), dataFormat);
    }

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
    public <T> T load(final String filename, final Class<T> type) {
        return load(parent.dataPath(filename), type, toFormat(filename));
    }

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
    public <T> T load(final String filename, final Class<T> type, final DataFormat dataFormat) {
        return unmarshal(fileHandler.readFile(parent.dataPath(filename)), type, dataFormat);
    }

    /**
     * Converts the given object to a marshaled String representation in
     * the given data format.
     *
     * @param obj        the object to be marshaled.
     * @param dataFormat the data format.
     * @return a marshaled String representation of the object.
     */
    public String marshal(final Object obj, final DataFormat dataFormat) {
        return marshallers.forDataFormat(dataFormat).marshal(obj);
    }

    /**
     * Save the given object to the given file.
     * Format is determined by the extension of the file name.
     *
     * @param obj  the object to be saved.
     * @param file the file to which the object will be saved.
     */
    public void save(final Object obj, final File file) {
        save(obj, file, toFormat(file.getName()));
    }

    /**
     * Saves the object to the file using the specified format.
     *
     * @param obj        the object to be saved.
     * @param file       the file where the object is to be saved.
     * @param dataFormat the file format.
     */
    public void save(final Object obj, final File file, final DataFormat dataFormat) {
        fileHandler.saveFile(file, marshal(obj, dataFormat));
    }

    /**
     * Saves the object to the file using the specified format.
     *
     * @param obj        the object to be saved.
     * @param filename   the full filepath or the relative path
     *                   to the data folder.
     * @param dataFormat the file format.
     */
    public void save(final Object obj, final String filename, final DataFormat dataFormat) {
        fileHandler.saveFile(parent.dataPath(filename), marshal(obj, dataFormat));
    }

    private DataFormat toFormat(final String filename) {
        if (!filename.contains(".")) {
            throw new RuntimeException("Unable to determine file type.");
        }
        String format = filename.substring(filename.indexOf(".") + 1);
        return DataFormat.forExtension(format);
    }

    /**
     * Returns a {@link TransformationBuilder} for the given object.
     *
     * @param object the object to be transformed.
     * @return the {@link TransformationBuilder} for performing a transformation.
     */
    public TransformationBuilder transform(final Object object) {
        return new TransformationBuilderImpl(object, marshallers, unmarshallers, fileHandler, parent);
    }

    /**
     * Returns a {@link TransformationBuilder} for the given file.
     *
     * @param filename the full path of the file or the
     *                 path relative to the data folder.
     * @return the {@link TransformationBuilder} for performing a transformation.
     */
    public TransformationBuilder transformFile(final String filename) {
        return new TransformationBuilderImpl(parent.dataPath(filename), marshallers, unmarshallers, fileHandler, parent);
    }

    /**
     * Returns a {@link TransformationBuilder} for the given file.
     *
     * @param file the file to be transformed.
     * @return the {@link TransformationBuilder} for performing a transformation.
     */
    public TransformationBuilder transformFile(final File file) {
        return new TransformationBuilderImpl(file, marshallers, unmarshallers, fileHandler, parent);
    }

    /**
     * Converts the given String to an object.
     *
     * @param marshaledObject the marshaled String representation of the object
     * @param dataFormat      the file format of the marshaled String object.
     * @return the unmarshaled object.
     */
    public Object unmarshal(final String marshaledObject, final DataFormat dataFormat) {
        return unmarshallers.forDataFormat(dataFormat).unmarshal(marshaledObject);
    }

    /**
     * Converts the given String to an object of type {@link T}.
     *
     * @param marshaledObject the marshaled String representation of the object
     * @param type            the type of the marshaled object to be returned.
     * @param dataFormat      the data format of the marshaled object.
     * @param <T>             the type of the marshaled object
     * @return the unmarshaled object.
     */
    public <T> T unmarshal(final String marshaledObject, final Class<T> type, final DataFormat dataFormat) {
        return unmarshallers.forDataFormat(dataFormat).unmarshal(marshaledObject, type);
    }
}
