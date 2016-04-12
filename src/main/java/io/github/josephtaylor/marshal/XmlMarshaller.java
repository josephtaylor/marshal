package io.github.josephtaylor.marshal;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

/**
 * XML implementation of the {@link Marshaller} interface.
 */
public class XmlMarshaller implements Marshaller {

    private final XStream xstream;

    public XmlMarshaller() {
        this.xstream = new XStream(new DomDriver());
    }

    @Override
    public DataFormat dataFormat() {
        return DataFormat.XML;
    }

    @Override
    public String marshal(final Object object) {
        return xstream.toXML(object);
    }
}
