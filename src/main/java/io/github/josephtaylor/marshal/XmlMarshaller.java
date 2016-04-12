package io.github.josephtaylor.marshal;

import javax.xml.bind.JAXB;
import java.io.StringWriter;

/**
 * XML implementation of the {@link Marshaller} interface.
 */
public class XmlMarshaller implements Marshaller {

    @Override
    public DataFormat dataFormat() {
        return DataFormat.XML;
    }

    @Override
    public String marshal(final Object object) {
        StringWriter writer = new StringWriter();
        JAXB.marshal(object, writer);
        writer.append("<!--__Type__").append(object.getClass().getName()).append("-->");
        return writer.toString();
    }
}
