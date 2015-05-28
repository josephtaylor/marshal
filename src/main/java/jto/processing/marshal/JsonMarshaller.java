package jto.processing.marshal;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * JSON implementation of the {@link Marshaller} interface.
 */
public class JsonMarshaller implements Marshaller {

    private final ObjectMapper objectMapper;

    public JsonMarshaller() {
        objectMapper = new ObjectMapper();
    }

    @Override
    public String marshal(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(String.format("Marshalling failed. %s", e.getMessage()));
        }
    }

    @Override
    public DataFormat dataFormat() {
        return DataFormat.JSON;
    }
}
