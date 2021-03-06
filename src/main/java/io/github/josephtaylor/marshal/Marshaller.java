package io.github.josephtaylor.marshal;

/**
 * An interface representing an object marshaller.
 */
public interface Marshaller {
	/**
	 * Returns the data format that the marshaller implementation uses.
	 *
	 * @return the data format.
	 */
	DataFormat dataFormat();

	/**
	 * Returns the Object marshaled into a String representation.
	 * The format of the String is determined by the {@link #dataFormat()} method.
	 *
	 * @param object the object to be marshaled
	 * @return the marshaled String representation of the object.
	 */
	String marshal(Object object);
}
