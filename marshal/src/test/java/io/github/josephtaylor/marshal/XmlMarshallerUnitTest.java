package io.github.josephtaylor.marshal;

import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;

public class XmlMarshallerUnitTest {

    @Test
    public void testMarshal() {
        Thing thing = new Thing();
        thing.setName("Taylor");
        thing.setId(100);

        assertEquals(
                "<io.github.josephtaylor.marshal.Thing>\n" +
                        "  <name>Taylor</name>\n" +
                        "  <id>100</id>\n" +
                        "</io.github.josephtaylor.marshal.Thing>",
                new XmlMarshaller().marshal(thing));
    }

    @Test
    public void testMarshal_list() {
        Thing first = new Thing();
        first.setName("Taylor");
        first.setId(100);

        Thing second = new Thing();
        second.setName("Bob");
        second.setId(200);

        List<Thing> things = Stream.of(first, second).collect(Collectors.toList());

        assertEquals(
                "<list>\n" +
                        "  <io.github.josephtaylor.marshal.Thing>\n" +
                        "    <name>Taylor</name>\n" +
                        "    <id>100</id>\n" +
                        "  </io.github.josephtaylor.marshal.Thing>\n" +
                        "  <io.github.josephtaylor.marshal.Thing>\n" +
                        "    <name>Bob</name>\n" +
                        "    <id>200</id>\n" +
                        "  </io.github.josephtaylor.marshal.Thing>\n" +
                        "</list>",
                new XmlMarshaller().marshal(things));
    }
}