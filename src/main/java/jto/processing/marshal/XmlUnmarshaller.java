package jto.processing.marshal;

import javax.xml.bind.JAXB;
import java.io.StringReader;

/**
 * XML implementation of the {@link Unmarshaller} interface.
 */
public class XmlUnmarshaller implements Unmarshaller {

    private static final String TYPE_TAG = "<!--__Type__";
    private static final String END_TAG = "-->";

    @Override
    public Object unmarshal(String marshaledObject) {
        return unmarshal(marshaledObject, objectClass(marshaledObject));
    }

    @Override
    public <T> T unmarshal(String marshaledObject, Class<T> type) {
        return JAXB.unmarshal(new StringReader(marshaledObject), type);
    }

    @Override
    public DataFormat dataFormat() {
        return DataFormat.XML;
    }

    private Class<?> objectClass(final String marshaledObject) {
        if (!marshaledObject.contains(TYPE_TAG)) {
            return Object.class;
        }
        int startIndex = marshaledObject.indexOf(TYPE_TAG) + TYPE_TAG.length();
        int endIndex = marshaledObject.indexOf(END_TAG, startIndex);
        String className = marshaledObject.substring(startIndex, endIndex);
        try {
            return Thread.currentThread().getContextClassLoader().loadClass(className);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Error unmarshalling.", e);
        }
    }
}
