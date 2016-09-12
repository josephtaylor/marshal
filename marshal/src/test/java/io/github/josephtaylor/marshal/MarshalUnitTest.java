package io.github.josephtaylor.marshal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

import org.junit.Test;

import processing.core.PApplet;

public class MarshalUnitTest {

    private Marshal marshal = new Marshal(new PApplet());

    private String getPath(final String name) {
        return Thread.currentThread().getContextClassLoader().getResource(name).getFile();
    }

    @Test
    public void testLoad() {
        File file = new File(getPath("test.json"));
        Object result = marshal.load(file);
        HashMap map = (HashMap) result;
        assertEquals("taylor", map.get("name"));
        assertEquals(100.0d, map.get("id"));
    }

    @Test
    public void testLoad_invalidFormat() {
        try {
            marshal.load(new File(getPath("test.txt")));
            fail("An exception should have been thrown.");
        } catch (Exception e) {
            assertEquals("The format with extension txt is not supported", e.getMessage());
        }
    }

    @Test
    public void testLoad_unknownFormat() {
        try {
            marshal.load(new File(getPath("test")));
            fail("An exception should have been thrown.");
        } catch (Exception e) {
            assertEquals("Unable to determine file type.", e.getMessage());
        }
    }

    @Test
    public void testLoad_path() {
        String path = getPath("test.json");
        Object result = marshal.load(path, DataFormat.JSON);
        HashMap map = (HashMap) result;
        assertEquals("taylor", map.get("name"));
        assertEquals(100.0d, map.get("id"));
    }

    @Test
    public void testLoad_typed() {
        String path = getPath("test.json");
        Thing thing = marshal.load(path, Thing.class);
        assertEquals("taylor", thing.getName());
        assertEquals(100, thing.getId());
    }

    @Test
    public void testLoad_typedWithFormat() {
        String path = getPath("test.json");
        Thing thing = marshal.load(path, Thing.class, DataFormat.JSON);
        assertEquals("taylor", thing.getName());
        assertEquals(100, thing.getId());
    }

    @Test
    public void testMarshal() {
        Thing thing = new Thing();
        thing.setId(1234);
        thing.setName("Some Name");

        assertEquals("{\n" +
                     "  \"name\": \"Some Name\",\n" +
                     "  \"id\": 1234\n" +
                     "}",
                marshal.marshal(thing, DataFormat.JSON));

        assertEquals("<io.github.josephtaylor.marshal.Thing>\n" +
                     "  <name>Some Name</name>\n" +
                     "  <id>1234</id>\n" +
                     "</io.github.josephtaylor.marshal.Thing>",
                marshal.marshal(thing, DataFormat.XML));

        assertEquals("!io.github.josephtaylor.marshal.Thing\n" +
                     "id: 1234\n" +
                     "name: Some Name\n",
                marshal.marshal(thing, DataFormat.YAML));
    }

    @Test
    public void testSave_dataFormat() throws IOException {
        Thing thing = new Thing();
        thing.setId(1234);
        thing.setName("Some Name");

        String filename = "/tmp/" + String.valueOf((int) (Math.random() * Integer.MAX_VALUE)) + ".xml";

        marshal.save(thing, new File(filename), DataFormat.XML);

        assertEquals("<io.github.josephtaylor.marshal.Thing>\n" +
                     "  <name>Some Name</name>\n" +
                     "  <id>1234</id>\n" +
                     "</io.github.josephtaylor.marshal.Thing>",
                new String(Files.readAllBytes(Paths.get(filename)), Charset.forName("UTF-8")));
    }

    @Test
    public void testSave_filename_dataFormat() throws IOException {
        Thing thing = new Thing();
        thing.setId(1234);
        thing.setName("Some Name");

        String filename = "/tmp/" + String.valueOf((int) (Math.random() * Integer.MAX_VALUE)) + ".xml";

        marshal.save(thing, filename, DataFormat.XML);

        assertEquals("<io.github.josephtaylor.marshal.Thing>\n" +
                     "  <name>Some Name</name>\n" +
                     "  <id>1234</id>\n" +
                     "</io.github.josephtaylor.marshal.Thing>",
                new String(Files.readAllBytes(Paths.get(filename)), Charset.forName("UTF-8")));
    }

    @Test
    public void testSave_noType() throws IOException {
        Thing thing = new Thing();
        thing.setId(1234);
        thing.setName("Some Name");

        String filename = "/tmp/" + String.valueOf((int) (Math.random() * Integer.MAX_VALUE)) + ".json";

        marshal.save(thing, new File(filename));

        assertEquals("{\n" +
                     "  \"name\": \"Some Name\",\n" +
                     "  \"id\": 1234\n" +
                     "}",
                new String(Files.readAllBytes(Paths.get(filename)), Charset.forName("UTF-8")));
    }

    @Test
    public void testTransform() {
        assertNotNull(marshal.transform(new Thing()));
    }

    @Test
    public void testTransformFile() {
        assertNotNull(marshal.transformFile(new File(getPath("test.json"))));
    }

    @Test
    public void testTransformFile_filename() {
        assertNotNull(marshal.transformFile("test.json"));
    }
}