package info.hoereth.imageio.icns;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageWriter;

import org.junit.Assert;
import org.junit.Test;

public class ICNSWriterTest {
	@Test
	public void test_registration() {
		Iterator<ImageWriter> writers = ImageIO.getImageWritersByFormatName("ICNS");
		Assert.assertTrue(writers.hasNext());
	}

	@Test
	public void createICNSfromPng() throws IOException {
		BufferedImage i = createExampleImage(64);
		boolean success = ImageIO.write(i, "ICNS", new File("/Users/mick/Documents/eclipse/imageio-icns/test.icns"));
		Assert.assertTrue("no writer has been found", success);
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
