package io.github.josephtaylor.marshal;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * JSON Implementation of the {@link Marshaller} interface.
 * Uses google's gson library.
 */
public class JsonMarshaller implements Marshaller {

	private final Gson gson;

	public JsonMarshaller() {
		gson = new GsonBuilder().setPrettyPrinting().create();
	}

	@Override
	public DataFormat dataFormat() {
		return DataFormat.JSON;
	}

	@Override
	public String marshal(final Object object) {
		return gson.toJson(object);
	}
}
