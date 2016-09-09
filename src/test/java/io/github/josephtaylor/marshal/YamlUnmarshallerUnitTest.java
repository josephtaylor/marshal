package io.github.josephtaylor.marshal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Test;

public class YamlUnmarshallerUnitTest {

    private static final String TEST_STRING = "!java.util.Arrays$ArrayList\n"
            + "- !io.github.josephtaylor.marshal.Thing\n"
            + "   id: 100\n"
            + "   name: testing\n"
            + "- !io.github.josephtaylor.marshal.Thing\n"
            + "   id: 120\n"
            + "   name: theOther\n";

    @Test
    @SuppressWarnings("unchecked")
    public void testUnmarshal() {
        List<Thing> things = (List<Thing>) new YamlUnmarshaller().unmarshal(TEST_STRING);

        assertEquals("testing", things.get(0).getName());
        assertEquals(100, things.get(0).getId());

        assertEquals("theOther", things.get(1).getName());
        assertEquals(120, things.get(1).getId());
    }

    @Test
    public void testUnmarshal_empty() {
        Thing thing = new YamlUnmarshaller().unmarshal("", Thing.class);
        assertNull(thing);
    }

    @Test(expected = NullPointerException.class)
    public void testUnmarshal_null() {
        new YamlUnmarshaller().unmarshal(null, Thing.class);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testUnmarshal_typed() {
        List<Thing> things = new YamlUnmarshaller().unmarshal(TEST_STRING, List.class);

        assertEquals("testing", things.get(0).getName());
        assertEquals(100, things.get(0).getId());

        assertEquals("theOther", things.get(1).getName());
        assertEquals(120, things.get(1).getId());
    }

    @Test
    public void testUnmarshal_exception() {
        try {
            new YamlUnmarshaller().unmarshal("HOORAYYYY", Integer.class);
            fail("An exception should have been thrown.");
        } catch (Exception e) {
            assertEquals("YAML Umarshalling failed.", e.getMessage());
        }
    }

    @Test
    public void testUnmarshal_noType_exception() {
        try {
            new YamlUnmarshaller().unmarshal("!@#!%%^!@#$!@#%");
            fail("An exception should have been thrown.");
        } catch (Exception e) {
            assertEquals("YAML Umarshalling failed.", e.getMessage());
        }
    }
}
