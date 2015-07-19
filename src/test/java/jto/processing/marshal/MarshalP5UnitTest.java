package jto.processing.marshal;

import com.google.common.io.Resources;
import org.junit.Test;
import processing.core.PApplet;

import java.io.File;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class MarshalP5UnitTest {

    private Marshal marshalP5 = new MarshalP5(new PApplet());

    @Test
    public void testLoad() {
        File file = new File(Resources.getResource("test.json").getFile());
        Object result = marshalP5.load(file);
        HashMap map = (HashMap) result;
        assertEquals("taylor", map.get("name"));
        assertEquals(100, map.get("id"));
    }

    @Test
    public void testLoad_path() {
        String path = Resources.getResource("test.json").getFile();
        Object result = marshalP5.load(path, DataFormat.JSON);
        HashMap map = (HashMap) result;
        assertEquals("taylor", map.get("name"));
        assertEquals(100, map.get("id"));
    }

    @Test
    public void testLoad_typed() {
        String path = Resources.getResource("test.json").getFile();
        Thing thing = marshalP5.load(path, Thing.class);
        assertEquals("taylor", thing.getName());
        assertEquals(100, thing.getId());
    }

    @Test
    public void testLoad_typedWithFormat() {
        String path = Resources.getResource("test.json").getFile();
        Thing thing = marshalP5.load(path, Thing.class, DataFormat.JSON);
        assertEquals("taylor", thing.getName());
        assertEquals(100, thing.getId());
    }

    //TODO: finish these up
}