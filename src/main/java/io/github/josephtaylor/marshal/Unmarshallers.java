package io.github.josephtaylor.marshal;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Helper for creating instances of the {@link Unmarshaller} interface.
 */
public class Unmarshallers {
	private static final Map<DataFormat, Unmarshaller> unmarshallers;

	public Unmarshaller forDataFormat(final DataFormat dataFormat) {
		return unmarshallers.get(dataFormat);
	}

	static {
		final List<Unmarshaller> unmarshallerList = new ArrayList<>();
		unmarshallerList.add(new JsonUnmarshaller());
		unmarshallerList.add(new XmlUnmarshaller());
		unmarshallerList.add(new YamlUnmarshaller());
		unmarshallers = unmarshallerList.stream().collect(Collectors.toMap(Unmarshaller::dataFormat, Function.identity()));
	}
}
