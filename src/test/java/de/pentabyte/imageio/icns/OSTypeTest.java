package de.pentabyte.imageio.icns;

import org.junit.Test;

import de.pentabyte.imageio.icns.OSType;

public class OSTypeTest {
	@Test
	public void test_valueOf_pixelSize() {
		OSType.valueOf(128);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void test_valueOf_illegal_pixelSize() {
		OSType.valueOf(88);
	}
	
	@Test
	public void test_valueOf() {
		OSType.valueOf(256, 2);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void test_valueOf_illegal() {
		OSType.valueOf(256, 7);
	}
}
