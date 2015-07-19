package jto.processing.marshal;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class JsonMarshallerUnitTest {

    @Test
    public void testMarshal() {
        Thing thing = new Thing();
        thing.setId(100);
        thing.setName("testing");

        String expected = "{\"name\":\"testing\",\"id\":100}";
        assertEquals(expected, new JsonMarshaller().marshal(thing));
    }

    @Test
    public void testMarshal_list() {
        Thing thing = new Thing();
        thing.setId(100);
        thing.setName("testing");

        Thing other = new Thing();
        other.setId(120);
        other.setName("theOther");

        List<Thing> things = Lists.newArrayList(thing, other);

        String expected = "[{\"name\":\"testing\",\"id\":100},{\"name\":\"theOther\",\"id\":120}]";
        assertEquals(expected, new JsonMarshaller().marshal(things));
    }
}