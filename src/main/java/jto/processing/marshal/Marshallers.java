package jto.processing.marshal;

import com.google.common.collect.Maps;

import java.util.Arrays;
import java.util.Map;

/**
 * Helper for creating instances of marshallers based on format.
 */
public class Marshallers {

    private static Map<DataFormat, Marshaller> marshallers;

    static {
        marshallers = Maps.uniqueIndex(
                Arrays.asList(
                        new JsonMarshaller(),
                        new XmlMarshaller()
                ), m -> m.dataFormat());

    }

    /**
     * Returns the marshaller implementation for the given {@code DataFormat}.
     *
     * @param dataFormat the data format.
     * @return the marshaller.
     */
    public Marshaller forDataFormat(final DataFormat dataFormat) {
        return marshallers.get(dataFormat);
    }
}
