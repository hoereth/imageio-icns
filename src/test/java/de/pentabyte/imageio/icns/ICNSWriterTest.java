package de.pentabyte.imageio.icns;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Iterator;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;

import org.junit.Assert;
import org.junit.Test;

import de.pentabyte.imageio.icns.ICNSImageWriter;
import de.pentabyte.imageio.icns.ICNSWriteParam;

public class ICNSWriterTest {
	@Test
	public void test_registration() {
		Iterator<ImageWriter> writers = ImageIO.getImageWritersByFormatName(ICNS.NAME);
		Assert.assertTrue(writers.hasNext());
	}

	@Test
	public void createICNSfromPng() throws IOException {
		BufferedImage i = createExampleImage(64);
		boolean success = ImageIO.write(i, ICNS.NAME, new ByteArrayOutputStream());
		Assert.assertTrue("no writer has been found", success);
	}
	
	@Test
	public void test_file_type_ic09() throws IOException {
		BufferedImage i = createExampleImage(512);
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		ICNSWriteParam param = new ICNSWriteParam();
		param.setDevicePixelRatio(1);
		
		ICNSImageWriter writer = lookupImageWriter();
		ImageOutputStream ios = ImageIO.createImageOutputStream(baos);
		writer.setOutput(ios);
		writer.write(null, new IIOImage(i, null, null), param);
		ios.close();
		
		Assert.assertTrue(baos.toString().contains(OSType.ic09.name()));
	}
	
	@Test
	public void test_file_type_ic14() throws IOException {
		BufferedImage i = createExampleImage(512);
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		ICNSWriteParam param = new ICNSWriteParam();
		param.setDevicePixelRatio(2);
		
		ICNSImageWriter writer = lookupImageWriter();
		ImageOutputStream ios = ImageIO.createImageOutputStream(baos);
		writer.setOutput(ios);
		writer.write(null, new IIOImage(i, null, null), param);
		ios.close();
		
		Assert.assertTrue(baos.toString().contains(OSType.ic14.name()));
	}
	
	@Test
	public void test_file_type_icp4() throws IOException {
		BufferedImage i = createExampleImage(16);
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		ICNSWriteParam param = new ICNSWriteParam();
		
		ICNSImageWriter writer = lookupImageWriter();
		ImageOutputStream ios = ImageIO.createImageOutputStream(baos);
		writer.setOutput(ios);
		writer.write(null, new IIOImage(i, null, null), param);
		ios.close();
		
		Assert.assertTrue(baos.toString().contains(OSType.icp4.name()));
	}

	private ICNSImageWriter lookupImageWriter() {
		Iterator<ImageWriter> writers = ImageIO.getImageWritersByFormatName(ICNS.NAME);
		return (ICNSImageWriter) writers.next();
	}

	private BufferedImage createExampleImage(int width) {
		BufferedImage image = new BufferedImage(width, width, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = image.createGraphics();

		g.setComposite(AlphaComposite.Src);
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.setColor(Color.MAGENTA);
		g.fill(new RoundRectangle2D.Float(0, 0, width, width, 10, 10));

		// ... then compositing the image on top,
		// using the white shape from above as alpha source
		g.setComposite(AlphaComposite.SrcAtop);
		g.drawImage(image, 0, 0, null);

		g.dispose();

		return image;
	}
}
