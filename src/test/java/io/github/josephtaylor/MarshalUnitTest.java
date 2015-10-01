package io.github.josephtaylor;

import static org.junit.Assert.assertEquals;

import java.io.File;
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
		assertEquals(100, map.get("id"));
	}

	@Test
	public void testLoad_path() {
		String path = getPath("test.json");
		Object result = marshal.load(path, DataFormat.JSON);
		HashMap map = (HashMap) result;
		assertEquals("taylor", map.get("name"));
		assertEquals(100, map.get("id"));
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

	//TODO: finish these up
}