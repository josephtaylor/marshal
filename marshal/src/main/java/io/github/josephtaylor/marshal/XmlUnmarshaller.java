package io.github.josephtaylor.marshal;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

/**
 * XML implementation of the {@link Unmarshaller} interface.
 */
public class XmlUnmarshaller implements Unmarshaller {

    private static final String TYPE_TAG = "<!--__Type__";
    private static final String END_TAG = "-->";

    private final XStream xstream;

    public XmlUnmarshaller() {
        xstream = new XStream(new DomDriver());
    }

    @Override
    public DataFormat dataFormat() {
        return DataFormat.XML;
    }

    @Override
    public Object unmarshal(final String marshaledObject) {
        return xstream.fromXML(marshaledObject);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T unmarshal(final String marshaledObject, final Class<T> type) {
        return (T) xstream.fromXML(marshaledObject);
    }
}
