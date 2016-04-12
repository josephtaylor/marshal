package io.github.josephtaylor.marshal;

import com.esotericsoftware.yamlbeans.YamlException;
import com.esotericsoftware.yamlbeans.YamlWriter;

import java.io.StringWriter;

/**
 * Yaml implementation of the {@link Marshaller} interface.
 */
public class YamlMarshaller implements Marshaller {

    @Override
    public DataFormat dataFormat() {
        return DataFormat.YAML;
    }

    @Override
    public String marshal(final Object object) {
        StringWriter output = new StringWriter();
        try {
            YamlWriter writer = new YamlWriter(output);
            writer.write(object);
            writer.close();
        } catch (YamlException e) {
            throw new RuntimeException("YAML Marshalling failed.", e);
        }
        return output.toString();
    }
}
