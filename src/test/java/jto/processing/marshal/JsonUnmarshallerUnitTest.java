package jto.processing.marshal;

import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class JsonUnmarshallerUnitTest {

    @Test
    public void testUnmarshal() {
        Object object = new JsonUnmarshaller().unmarshal("{\"name\":\"testing\",\"id\":100}");
        HashMap map = (HashMap) object;
        assertEquals("testing", map.get("name"));
        assertEquals(100, map.get("id"));
    }

    @Test(expected = RuntimeException.class)
    public void testUnmarshal_empty() {
        new JsonUnmarshaller().unmarshal("", Thing.class);
    }

    @Test(expected = RuntimeException.class)
    public void testUnmarshal_null() {
        new JsonUnmarshaller().unmarshal(null, Thing.class);
    }

    @Test
    public void testUnmarshal_typed() {
        Thing thing = new JsonUnmarshaller().unmarshal("{\"name\":\"testing\",\"id\":100}", Thing.class);
        assertEquals("testing", thing.getName());
        assertEquals(100, thing.getId());
    }
}