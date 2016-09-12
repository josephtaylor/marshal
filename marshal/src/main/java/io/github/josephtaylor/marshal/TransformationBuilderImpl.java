package io.github.josephtaylor.marshal;

import java.io.File;

import processing.core.PApplet;

/**
 * The implementation of the {@link TransformationBuilder} interface.
 */
public class TransformationBuilderImpl implements TransformationBuilder {

    private static final String OBJECT = "object";

    private final String filename;
    private final Unmarshallers unmarshallers;
    private final Marshallers marshallers;
    private final FileHandler fileHandler;
    private final PApplet parent;
    private Object initial;
    private String transformed = "";
    private Class<?> type;

    /**
     * Constructor for {@code TransformationBuilderImpl} objects.
     *
     * @param file          the file to be transformed.
     * @param marshallers   the marshaller implementation factory.
     * @param unmarshallers the unmarshaller implementation factory.
     * @param fileHandler   the file handler.
     */
    public TransformationBuilderImpl(final File file, final Marshallers marshallers,
            final Unmarshallers unmarshallers, final FileHandler fileHandler,
            final PApplet parent) {
        this(file.getAbsolutePath(), marshallers, unmarshallers, fileHandler, parent);
    }

    /**
     * Constructor for {@code TransformationBuilderImpl} objects.
     *
     * @param object        the object to be transformed.
     * @param marshallers   the marshaller implementation factory.
     * @param unmarshallers the unmarshaller implementation factory.
     * @param fileHandler   the file handler.
     */
    public TransformationBuilderImpl(final Object object, final Marshallers marshallers,
            final Unmarshallers unmarshallers, final FileHandler fileHandler, final PApplet parent) {
        if (object instanceof String) {
            this.filename = (String) object;
            this.initial = null;
        } else {
            this.filename = OBJECT;
            this.initial = object;
        }
        this.unmarshallers = unmarshallers;
        this.marshallers = marshallers;
        this.fileHandler = fileHandler;
        this.parent = parent;
    }

    @Override
    public void andSaveTo(final String filename) {
        fileHandler.saveFile(parent.dataPath(filename), transformed);
    }

    @Override
    public void andSaveTo(final File file) {
        fileHandler.saveFile(file, transformed);
    }

    @Override
    public TransformationBuilder from(final String dataFormat) {
        return from(DataFormat.forExtension(dataFormat));
    }

    @Override
    public TransformationBuilder from(final DataFormat dataFormat) {
        if (!OBJECT.equals(filename)) {
            if (null != type) {
                initial = unmarshallers.forDataFormat(dataFormat).unmarshal(fileHandler.readFile(filename), type);
                return this;
            }
            initial = unmarshallers.forDataFormat(dataFormat).unmarshal(fileHandler.readFile(filename));
        }
        return this;
    }

    @Override
    public String getString() {
        return transformed;
    }

    @Override
    public TransformationBuilder ofType(final Class<?> type) {
        this.type = type;
        return this;
    }

    @Override
    public TransformationBuilder to(final DataFormat dataFormat) {
        if (null == initial) {
            throw new IllegalStateException("Initial object to be transformed is null.");
        }
        transformed = marshallers.forDataFormat(dataFormat).marshal(initial);
        return this;
    }

    @Override
    public TransformationBuilder to(final String dataFormat) {
        return to(DataFormat.forExtension(dataFormat));
    }
}
