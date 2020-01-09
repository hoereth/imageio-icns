package info.hoereth.imageio.icns;

import java.awt.image.RenderedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Iterator;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageTypeSpecifier;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.metadata.IIOMetadata;
import javax.imageio.spi.ImageWriterSpi;
import javax.imageio.stream.ImageOutputStream;

public class ICNSImageWriter extends ImageWriter {

	protected ICNSImageWriter(ImageWriterSpi originatingProvider) {
		super(originatingProvider);
	}

	@Override
	public ImageWriteParam getDefaultWriteParam() {
		ICNSWriteParam wparam = new ICNSWriteParam();
		wparam.setDevicePixelRatio(2);
		return wparam;
	}

	@Override
	public IIOMetadata getDefaultStreamMetadata(ImageWriteParam param) {
		return null;
	}

	@Override
	public IIOMetadata getDefaultImageMetadata(ImageTypeSpecifier imageType, ImageWriteParam param) {
		return null;
	}

	@Override
	public IIOMetadata convertStreamMetadata(IIOMetadata inData, ImageWriteParam param) {
		throw new UnsupportedOperationException();
	}

	@Override
	public IIOMetadata convertImageMetadata(IIOMetadata inData, ImageTypeSpecifier imageType, ImageWriteParam param) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void write(IIOMetadata streamMetadata, IIOImage image, ImageWriteParam param) throws IOException {
		ImageOutputStream ios = (ImageOutputStream) getOutput();

		RenderedImage renderedImage = image.getRenderedImage();
		if (renderedImage.getWidth() != renderedImage.getHeight()) {
			throw new RuntimeException("image size must be square.");
		}
		OSType type = OSType.valueOf(renderedImage.getWidth());

		Iterator<ImageWriter> writers = ImageIO.getImageWritersBySuffix("png");
		if (writers.hasNext()) {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write(renderedImage, "PNG", baos);

			ios.writeBytes("icns");
			ios.writeInt(baos.size() + 12);

			ios.writeBytes(type.name());
			ios.writeInt(baos.size() + 4);
			ios.write(baos.toByteArray());
		} else {
			throw new RuntimeException(getClass().getSimpleName() + " requires PNG image writer.");
		}
	}

}
