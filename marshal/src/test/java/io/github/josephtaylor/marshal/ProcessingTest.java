package io.github.josephtaylor.marshal;

import java.util.Arrays;
import java.util.List;

import processing.core.PApplet;

/**
 * Test PApplet for trying things out.
 */
public class ProcessingTest extends PApplet {

	private static class Thing {
		private String name;
		private List<Integer> data;

		public List<Integer> getData() {
			return data;
		}

		public String getName() {
			return name;
		}

		public void setData(List<Integer> data) {
			this.data = data;
		}

		public void setName(String name) {
			this.name = name;
		}
	}
	private static final long serialVersionUID = 1L;
	private Marshal marshal;

	public static void main(String[] args) {
		PApplet.main(ProcessingTest.class.getName());
	}

	@Override
	public void setup() {
		marshal = new Marshal(this);

		Thing thing = new Thing();
		thing.setName("testThing");
		thing.setData(Arrays.asList(1, 2, 3, 4));

		//println(marshal.marshal(thing, DataFormat.JSON));

		String xmlMarshalled = marshal.marshal(thing, DataFormat.XML);
		Object test = marshal.unmarshal(xmlMarshalled, DataFormat.XML);
		Thing output = marshal.unmarshal(xmlMarshalled, Thing.class, DataFormat.XML);
		println(output.getName());
		println(output.getData());

		//println(marshal.transform(thing).to(DataFormat.XML).getString());

		marshal.save(thing, "test.xml", DataFormat.XML);

		println(marshal.transformFile("test.xml").from("xml").to("json").getString());
	}
}
