package io.github.josephtaylor;

import java.util.HashMap;

import com.google.gson.Gson;

/**
 * Json implementation of the {@link Unmarshaller} interface.
 * Uses google's gson library.
 */
public class JsonUnmarshaller implements Unmarshaller {

	private final Gson gson;

	public JsonUnmarshaller() {
		gson = new Gson();
	}

	@Override
	public DataFormat dataFormat() {
		return DataFormat.JSON;
	}

	@Override
	public <T> T unmarshal(final String marshaledObject, final Class<T> type) {
		return gson.fromJson(marshaledObject, type);
	}

	@Override
	public Object unmarshal(final String marshaledObject) {
		return gson.fromJson(marshaledObject, HashMap.class);
	}
}
