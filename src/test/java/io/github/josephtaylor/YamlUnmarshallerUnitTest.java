package io.github.josephtaylor;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.HashMap;

import org.junit.Test;

public class YamlUnmarshallerUnitTest {
	@Test
	public void testUnmarshal() {
		Object object = new YamlUnmarshaller().unmarshal("{\"name\":\"testing\",\"id\":100}");
		HashMap map = (HashMap) object;
		assertEquals("testing", map.get("name"));
		assertEquals(Double.valueOf(100), Double.valueOf((String) map.get("id")));
	}

	@Test
	public void testUnmarshal_empty() {
		Thing thing = new YamlUnmarshaller().unmarshal("", Thing.class);
		assertNull(thing);
	}

	@Test(expected = NullPointerException.class)
	public void testUnmarshal_null() {
		Thing thing = new YamlUnmarshaller().unmarshal(null, Thing.class);
	}

	@Test
	public void testUnmarshal_typed() {
		Thing thing = new YamlUnmarshaller().unmarshal("{\"name\":\"testing\",\"id\":100}", Thing.class);
		assertEquals("testing", thing.getName());
		assertEquals(100, thing.getId());
	}
}
