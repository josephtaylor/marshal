package io.github.josephtaylor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Helper for creating instances of marshallers based on format.
 */
public class Marshallers {

	private static Map<DataFormat, Marshaller> marshallers;

	/**
	 * Returns the marshaller implementation for the given {@code DataFormat}.
	 *
	 * @param dataFormat the data format.
	 * @return the marshaller.
	 */
	public Marshaller forDataFormat(final DataFormat dataFormat) {
		return marshallers.get(dataFormat);
	}

	static {
		final List<Marshaller> marshallersList = new ArrayList<>();
		marshallersList.add(new JsonMarshaller());
		marshallersList.add(new XmlMarshaller());
		marshallers = marshallersList.stream().collect(Collectors.toMap(Marshaller::dataFormat, Function.identity()));

	}
}
