package io.github.josephtaylor.marshal;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class MarshallersUnitTest {
    @Test
    public void testForDataFormat() {
        Marshallers marshallers = new Marshallers();
        assertEquals(DataFormat.JSON, marshallers.forDataFormat(DataFormat.JSON).dataFormat());
        assertEquals(DataFormat.XML, marshallers.forDataFormat(DataFormat.XML).dataFormat());
        assertEquals(DataFormat.YAML, marshallers.forDataFormat(DataFormat.YAML).dataFormat());
    }
}