package io.github.josephtaylor.marshal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

import java.io.File;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.internal.util.reflection.Whitebox;
import org.mockito.runners.MockitoJUnitRunner;

import processing.core.PApplet;

@RunWith(MockitoJUnitRunner.class)
public class TransformationBuilderImplUnitTest {
    @InjectMocks
    private TransformationBuilderImpl transformationBuilder;
    @Mock
    private Marshallers marshallers;
    @Mock
    private Marshaller marshaller;
    @Mock
    private Unmarshallers unmarshallers;
    @Mock
    private Unmarshaller unmarshaller;
    @Mock
    private FileHandler fileHandler;
    @Mock
    private PApplet parent;

    private String getPath(final String name) {
        return Thread.currentThread().getContextClassLoader().getResource(name).getFile();
    }

    @Test
    public void testAndSaveTo() {
        transformationBuilder.andSaveTo(new File("test"));
        verify(fileHandler).saveFile(any(File.class), anyString());
    }

    @Test
    public void testAndSaveTo_string() {
        transformationBuilder.andSaveTo("test");
        verify(fileHandler).saveFile(anyString(), anyString());
    }

    @Test
    public void testFrom_object() {
        Whitebox.setInternalState(transformationBuilder, "filename", "object");
        verifyZeroInteractions(unmarshallers);
        verifyZeroInteractions(fileHandler);
    }

    @Test
    public void testFrom_noType() {
        Whitebox.setInternalState(transformationBuilder, "filename", "test");
        when(fileHandler.readFile("test")).thenReturn("\"HEY\"");
        when(unmarshallers.forDataFormat(DataFormat.JSON)).thenReturn(unmarshaller);
        when(unmarshaller.unmarshal(anyString())).thenReturn("HEY");

        transformationBuilder.from("json");

        assertEquals("HEY", Whitebox.getInternalState(transformationBuilder, "initial"));
    }

    @Test
    public void testFrom_typed() {
        Whitebox.setInternalState(transformationBuilder, "filename", "test");
        Whitebox.setInternalState(transformationBuilder, "type", String.class);
        when(fileHandler.readFile("test")).thenReturn("\"HEY\"");
        when(unmarshallers.forDataFormat(DataFormat.JSON)).thenReturn(unmarshaller);
        when(unmarshaller.unmarshal(anyString(), eq(String.class))).thenReturn("HEY");

        transformationBuilder.from("json");

        assertEquals("HEY", Whitebox.getInternalState(transformationBuilder, "initial"));
    }

    @Test
    public void testGetString() {
        Whitebox.setInternalState(transformationBuilder, "transformed", "WOO");
        assertEquals("WOO", transformationBuilder.getString());
    }

    @Test
    public void testOfType() {
        transformationBuilder.ofType(String.class);
        assertEquals(String.class, Whitebox.getInternalState(transformationBuilder, "type"));
    }

    @Test
    public void testTo_illegalState() {
        Whitebox.setInternalState(transformationBuilder, "initial", null);
        try {
            transformationBuilder.to("json");
            fail("An exception should have been thrown.");
        } catch (IllegalStateException e) {
            assertEquals("Initial object to be transformed is null.", e.getMessage());
        }
    }

    @Test
    public void testTo() {
        when(marshallers.forDataFormat(DataFormat.JSON)).thenReturn(marshaller);
        when(marshaller.marshal(anyObject())).thenReturn("TRANSFORMED");

        transformationBuilder.to("json");

        assertEquals("TRANSFORMED", Whitebox.getInternalState(transformationBuilder, "transformed"));
    }

    @Test
    public void testConstructors() {
        TransformationBuilder builder = new TransformationBuilderImpl(
                new File(getPath("test")), marshallers, unmarshallers, fileHandler, parent);
        assertTrue(((String) Whitebox.getInternalState(builder, "filename")).endsWith("test"));
        assertNull(Whitebox.getInternalState(builder, "initial"));
    }


}