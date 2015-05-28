package jto.processing.marshal;

import processing.core.PApplet;

import java.util.Arrays;
import java.util.List;

/**
 * Test PApplet for trying things out.
 */
public class ProcessingTest extends PApplet {

    private MarshalP5 marshalP5;

    public static void main(String[] args) {
        PApplet.main(ProcessingTest.class.getName());
    }

    @Override
    public void setup() {
        marshalP5 = new MarshalP5(this);

        Thing thing = new Thing();
        thing.setName("testThing");
        thing.setData(Arrays.asList(1, 2, 3, 4));


        //println(marshalP5.marshal(thing, DataFormat.JSON));

        String xmlMarshalled = marshalP5.marshal(thing, DataFormat.XML);
        Object test = marshalP5.unmarshal(xmlMarshalled, DataFormat.XML);
        Thing output = marshalP5.unmarshal(xmlMarshalled, Thing.class, DataFormat.XML);
        println(output.getName());
        println(output.getData());

        //println(marshalP5.transform(thing).to(DataFormat.XML).getString());

        marshalP5.save(thing, "test.xml", DataFormat.XML);

        println(marshalP5.transformFile("test.xml").from("xml").to("json").getString());
    }

    private static class Thing {
        private String name;
        private List<Integer> data;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<Integer> getData() {
            return data;
        }

        public void setData(List<Integer> data) {
            this.data = data;
        }
    }
}
