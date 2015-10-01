package io.github.josephtaylor;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class JsonMarshallerUnitTest {

	@Test
	public void testMarshal() {
		Thing thing = new Thing();
		thing.setId(100);
		thing.setName("testing");

		String expected = "{\"name\":\"testing\",\"id\":100}";
		assertEquals(expected, new JsonMarshaller().marshal(thing));
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

		String expected = "[{\"name\":\"testing\",\"id\":100},{\"name\":\"theOther\",\"id\":120}]";
		assertEquals(expected, new JsonMarshaller().marshal(things));
	}
}