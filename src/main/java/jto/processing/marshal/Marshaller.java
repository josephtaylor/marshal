package jto.processing.marshal;

/**
 * An interface representing an object marshaller.
 */
public interface Marshaller {
    /**
     * Returns the Object marshaled into a String representation.
     * The format of the String is determined by the {@link #dataFormat()} method.
     *
     * @param object
     * @return the marshaled String represenation of the object.
     */
    String marshal(Object object);

    /**
     * Returns the data format that the marshaller implementation uses.
     *
     * @return the data format.
     */
    DataFormat dataFormat();
}
