package io.github.josephtaylor;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.HashMap;

import org.junit.Test;

public class JsonUnmarshallerUnitTest {
	@Test
	public void testUnmarshal() {
		Object object = new JsonUnmarshaller().unmarshal("{\"name\":\"testing\",\"id\":100}");
		HashMap map = (HashMap) object;
		assertEquals("testing", map.get("name"));
		assertEquals(100.0d, map.get("id"));
	}

	@Test
	public void testUnmarshal_empty() {
		Thing thing = new JsonUnmarshaller().unmarshal("", Thing.class);
		assertNull(thing);
	}

	@Test
	public void testUnmarshal_null() {
		Thing thing = new JsonUnmarshaller().unmarshal(null, Thing.class);
		assertNull(thing);
	}

	@Test
	public void testUnmarshal_typed() {
		Thing thing = new JsonUnmarshaller().unmarshal("{\"name\":\"testing\",\"id\":100}", Thing.class);
		assertEquals("testing", thing.getName());
		assertEquals(100, thing.getId());
	}
}
