package jto.processing.marshal;

/**
 * Interface representing an object unmarshaller.
 */
public interface Unmarshaller {
    /**
     * Indicates the data format that the implemenation uses.
     *
     * @return the data format.
     */
    DataFormat dataFormat();

    /**
     * Unmarshals the given string into an instance of class {@link T}.
     *
     * @param marshaledObject the marshaled object.
     * @param type            the type of the returned object.
     * @param <T>             the type of the returned object.
     * @return an instance of class {@link T}.
     */
    <T> T unmarshal(String marshaledObject, Class<T> type);

    /**
     * Unmarshals the given string into an object.
     *
     * @param marshaledObject the marshaled object.
     * @return the unmarshaled object.
     */
    Object unmarshal(String marshaledObject);
}
