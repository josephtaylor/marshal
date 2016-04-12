package io.github.josephtaylor.marshal;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class XmlMarshallerUnitTest {

	private static final String expected = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
										   "<thing>\n" +
										   "    <id>100</id>\n" +
										   "    <name>Taylor</name>\n" +
										   "</thing>\n" +
										   "<!--__Type__io.github.josephtaylor.marshal.Thing-->";

	@Test
	public void testMarshal() {
		Thing thing = new Thing();
		thing.setName("Taylor");
		thing.setId(100);

		assertEquals(expected, new XmlMarshaller().marshal(thing));
	}
}