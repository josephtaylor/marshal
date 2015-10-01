package io.github.josephtaylor;

import java.io.File;

/**
 * The implementation of the {@link TransformationBuilder} interface.
 */
public class TransformationBuilderImpl implements TransformationBuilder {

	private static final String OBJECT = "object";

	private final String filename;
	private final Unmarshallers unmarshallers;
	private final Marshallers marshallers;
	private final FileHandler fileHandler;
	private Object initial;
	private String transformed = "";

	/**
	 * Constructor for {@code TransformationBuilderImpl} objects.
	 *
	 * @param file          the file to be transformed.
	 * @param marshallers   the marshaller implementation factory.
	 * @param unmarshallers the unmarshaller implementation factory.
	 * @param fileHandler   the file handler.
	 */
	public TransformationBuilderImpl(final File file, final Marshallers marshallers,
			final Unmarshallers unmarshallers, final FileHandler fileHandler) {
		this(file.getAbsolutePath(), marshallers, unmarshallers, fileHandler);
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
			final Unmarshallers unmarshallers, final FileHandler fileHandler) {
		if (object instanceof String) {
			this.filename = (String) object;
			this.initial = null;
		} else {
			this.filename = OBJECT;
		}
		this.initial = object;
		this.unmarshallers = unmarshallers;
		this.marshallers = marshallers;
		this.fileHandler = fileHandler;
	}

	@Override
	public void andSaveTo(String filename) {
		fileHandler.saveFile(filename, transformed);
	}

	@Override
	public void andSaveTo(File file) {
		fileHandler.saveFile(file, transformed);
	}

	@Override
	public TransformationBuilder from(String dataFormat) {
		return from(DataFormat.forExtension(dataFormat));
	}

	@Override
	public TransformationBuilder from(DataFormat dataFormat) {
		if (!OBJECT.equals(filename)) {
			initial = unmarshallers.forDataFormat(dataFormat).unmarshal(fileHandler.readFile(filename));
		}
		return this;
	}

	@Override
	public String getString() {
		return transformed;
	}

	@Override
	public TransformationBuilder to(DataFormat dataFormat) {
		if (null == initial) {
			throw new IllegalStateException("Initial object to be transformed is null.");
		}
		transformed = marshallers.forDataFormat(dataFormat).marshal(initial);
		return this;
	}

	@Override
	public TransformationBuilder to(String dataFormat) {
		return to(DataFormat.forExtension(dataFormat));
	}
}
