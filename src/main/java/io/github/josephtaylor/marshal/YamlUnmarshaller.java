package io.github.josephtaylor.marshal;

import com.esotericsoftware.yamlbeans.YamlException;
import com.esotericsoftware.yamlbeans.YamlReader;

/**
 * Yaml implementation of the {@link Unmarshaller} interface.
 */
public class YamlUnmarshaller implements Unmarshaller {

    private static final String ERROR_MESSAGE = "YAML Umarshalling failed.";

    @Override
    public DataFormat dataFormat() {
        return DataFormat.YAML;
    }

    @Override
    public <T> T unmarshal(final String marshaledObject, final Class<T> type) {
        try {
            return new YamlReader(marshaledObject).read(type);
        } catch (YamlException e) {
            throw new RuntimeException(ERROR_MESSAGE, e);
        }
    }

    @Override
    public Object unmarshal(final String marshaledObject) {
        try {
            return new YamlReader(marshaledObject).read();
        } catch (YamlException e) {
            throw new RuntimeException(ERROR_MESSAGE, e);
        }
    }
}
