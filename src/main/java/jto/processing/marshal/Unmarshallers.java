package jto.processing.marshal;

import com.google.common.collect.Maps;

import java.util.Arrays;
import java.util.Map;

/**
 * Helper for creating instances of the {@link Unmarshaller} interface.
 */
public class Unmarshallers {
    private static final Map<DataFormat, Unmarshaller> unmarshallers;

    static {
        unmarshallers = Maps.uniqueIndex(
                Arrays.asList(
                        new JsonUnmarshaller(),
                        new XmlUnmarshaller()
                ), u -> u.dataFormat());
    }

    public Unmarshaller forDataFormat(final DataFormat dataFormat) {
        return unmarshallers.get(dataFormat);
    }
}
