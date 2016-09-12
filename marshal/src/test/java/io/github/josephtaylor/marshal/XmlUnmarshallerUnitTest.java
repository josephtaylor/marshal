package io.github.josephtaylor.marshal;


import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class XmlUnmarshallerUnitTest {

    private static final String THING_STRING = "<io.github.josephtaylor.marshal.Thing>\n" +
            "  <name>Taylor</name>\n" +
            "  <id>100</id>\n" +
            "</io.github.josephtaylor.marshal.Thing>";

    private static final String THING_LIST_STRING = "<list>" +
            "  <io.github.josephtaylor.marshal.Thing>\n" +
            "    <name>Taylor</name>\n" +
            "    <id>100</id>\n" +
            "  </io.github.josephtaylor.marshal.Thing>\n" +
            "  <io.github.josephtaylor.marshal.Thing>\n" +
            "    <name>Bob</name>\n" +
            "    <id>200</id>\n" +
            "  </io.github.josephtaylor.marshal.Thing>\n" +
            "</list>";

    @Test
    public void testDataFormat() {
        assertEquals(DataFormat.XML, new XmlUnmarshaller().dataFormat());
    }

    @Test
    public void testUnmarshal() {
        Object result = new XmlUnmarshaller().unmarshal(THING_STRING);
        Thing thing = (Thing) result;
        assertEquals("Taylor", thing.getName());
        assertEquals(100, thing.getId());
    }

    @Test(expected = RuntimeException.class)
    public void testUnmarshal_null() {
        new XmlUnmarshaller().unmarshal(null);
    }

    @Test
    public void testUnmarshal_typed() {
        Thing thing = new XmlUnmarshaller().unmarshal(THING_STRING, Thing.class);
        assertEquals("Taylor", thing.getName());
        assertEquals(100, thing.getId());
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testUnmarshal_typedList() {
        List<Thing> things = new XmlUnmarshaller().unmarshal(THING_LIST_STRING, List.class);
        assertEquals("Taylor", things.get(0).getName());
    }
}