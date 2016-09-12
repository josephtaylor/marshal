package io.github.josephtaylor.marshal;

import java.io.StringWriter;

import com.esotericsoftware.yamlbeans.YamlWriter;

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
        } catch (Exception e) {
            throw new RuntimeException("YAML Marshalling failed.", e);
        }
        return output.toString();
    }
}
