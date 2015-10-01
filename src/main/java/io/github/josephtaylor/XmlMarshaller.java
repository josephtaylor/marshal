package io.github.josephtaylor;

import java.io.StringWriter;

import javax.xml.bind.JAXB;

/**
 * XML implementation of the {@link Marshaller} interface.
 */
public class XmlMarshaller implements Marshaller {

	@Override
	public DataFormat dataFormat() {
		return DataFormat.XML;
	}

	@Override
	public String marshal(Object object) {
		StringWriter writer = new StringWriter();
		JAXB.marshal(object, writer);
		writer.append("<!--__Type__" + object.getClass().getName() + "-->");
		return writer.toString();
	}
}
