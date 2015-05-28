package jto.processing.marshal;

import javax.xml.bind.JAXB;
import java.io.StringWriter;

/**
 * XML implementation of the {@link Marshaller} interface.
 */
public class XmlMarshaller implements Marshaller {

    @Override
    public String marshal(Object object) {
        StringWriter writer = new StringWriter();
        JAXB.marshal(object, writer);
        writer.append("<!--__Type__" + object.getClass().getName() + "-->");
        return writer.toString();
    }

    @Override
    public DataFormat dataFormat() {
        return DataFormat.XML;
    }
}
