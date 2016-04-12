package io.github.josephtaylor.marshal;

import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class DataFormatUnitTest {

    @Test
    public void testExtension() {
        assertEquals("xml", DataFormat.XML.extension());
        assertEquals("json", DataFormat.JSON.extension());
    }

    @Test
    public void testForExtension() {
        assertEquals(DataFormat.JSON, DataFormat.forExtension("json"));
        assertEquals(DataFormat.XML, DataFormat.forExtension("xml"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testForExtension_exceptionThrown() {
        try {
            DataFormat.forExtension("java");
        } catch (IllegalArgumentException e) {
            assertEquals("The format with extension java is not supported", e.getMessage());
            throw e;
        }
    }
}