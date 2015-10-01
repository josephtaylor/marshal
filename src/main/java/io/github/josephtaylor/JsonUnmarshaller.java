package io.github.josephtaylor;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * JSON implementation of the {@link Unmarshaller} interface.
 */
public class JsonUnmarshaller implements Unmarshaller {

	private static final String ERROR = "Unmarshalling failed. %s";

	private final ObjectMapper objectMapper;

	public JsonUnmarshaller() {
		this.objectMapper = new ObjectMapper();
	}

	@Override
	public DataFormat dataFormat() {
		return DataFormat.JSON;
	}

	@Override
	public <T> T unmarshal(String marshaledObject, Class<T> type) {
		try {
			return objectMapper.readValue(marshaledObject, type);
		} catch (Exception e) {
			throw new RuntimeException(String.format(ERROR, e.getMessage()), e);
		}
	}

	@Override
	public Object unmarshal(String marshaledObject) {
		try {
			return objectMapper.readValue(marshaledObject, Object.class);
		} catch (IOException e) {
			throw new RuntimeException(String.format(ERROR, e.getMessage()), e);
		}
	}
}
