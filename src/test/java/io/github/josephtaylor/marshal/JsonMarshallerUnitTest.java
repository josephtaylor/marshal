package io.github.josephtaylor.marshal;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class JsonMarshallerUnitTest {

    @Test
    public void testMarshal() {
        Thing thing = new Thing();
        thing.setId(100);
        thing.setName("testing");

        assertEquals("{\n" +
                        "  \"name\": \"testing\",\n" +
                        "  \"id\": 100\n" +
                        "}",
                new JsonMarshaller().marshal(thing));
    }

    @Test
    public void testMarshal_list() {
        Thing thing = new Thing();
        thing.setId(100);
        thing.setName("testing");

        Thing other = new Thing();
        other.setId(120);
        other.setName("theOther");

        List<Thing> things = Arrays.asList(thing, other);

        assertEquals("[\n" +
                "  {\n" +
                "    \"name\": \"testing\",\n" +
                "    \"id\": 100\n" +
                "  },\n" +
                "  {\n" +
                "    \"name\": \"theOther\",\n" +
                "    \"id\": 120\n" +
                "  }\n" +
                "]", new JsonMarshaller().marshal(things));
    }
}
