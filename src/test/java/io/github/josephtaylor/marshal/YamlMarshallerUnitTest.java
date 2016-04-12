package io.github.josephtaylor.marshal;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class YamlMarshallerUnitTest {
	@Test
	public void testMarshal() {
		Thing thing = new Thing();
		thing.setId(100);
		thing.setName("testing");

		String expected = "!io.github.josephtaylor.marshal.Thing\n"
						  + "id: 100\n"
						  + "name: testing\n";
		assertEquals(expected, new YamlMarshaller().marshal(thing));
	}

	@Test
	public void testMarshal_list() {
		Thing thing = new Thing();
		thing.setId(100);
		thing.setName("testing");

		Thing other = new Thing();
		other.setId(120);
		other.setName("theOther");

		List<Thing> things = Arrays.asList(thing, other);

		String expected = "!java.util.Arrays$ArrayList\n"
						  + "- !io.github.josephtaylor.marshal.Thing\n"
						  + "   id: 100\n"
						  + "   name: testing\n"
						  + "- !io.github.josephtaylor.marshal.Thing\n"
						  + "   id: 120\n"
						  + "   name: theOther\n";
		assertEquals(expected, new YamlMarshaller().marshal(things));
	}
}