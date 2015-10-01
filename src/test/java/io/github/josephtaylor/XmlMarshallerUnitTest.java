package io.github.josephtaylor;

import org.junit.Test;

import junit.framework.TestCase;

/**
 * Created by joconnor on 7/19/15.
 */
public class XmlMarshallerUnitTest extends TestCase {

    private static final String expected = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
            "<thing>\n" +
            "    <id>100</id>\n" +
            "    <name>Taylor</name>\n" +
            "</thing>\n" +
            "<!--__Type__io.github.josephtaylor.Thing-->";

    @Test
    public void testMarshal() {
        Thing thing = new Thing();
        thing.setName("Taylor");
        thing.setId(100);

        assertEquals(expected, new XmlMarshaller().marshal(thing));
    }
}