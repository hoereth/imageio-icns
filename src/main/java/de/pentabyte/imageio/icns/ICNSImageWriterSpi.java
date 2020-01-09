package de.pentabyte.imageio.icns;

import java.util.Locale;

import javax.imageio.ImageTypeSpecifier;
import javax.imageio.ImageWriter;
import javax.imageio.spi.ImageWriterSpi;
import javax.imageio.stream.ImageOutputStream;

public class ICNSImageWriterSpi extends ImageWriterSpi {
	static final String vendorName = "Michael Hoereth";
	static final String version = "1.0";
	static final String writerClassName = ICNSImageWriter.class.getName();
	static final String[] names = { ICNS.NAME };
	static final String[] suffixes = { ICNS.FILE_EXTENSION };
	static final String[] MIMETypes = {};
	static final String[] readerSpiNames = {};

	static final boolean supportsStandardStreamMetadataFormat = false;
	static final String nativeStreamMetadataFormatName = null;
	static final String nativeStreamMetadataFormatClassName = null;
	static final String[] extraStreamMetadataFormatNames = null;
	static final String[] extraStreamMetadataFormatClassNames = null;
	static final boolean supportsStandardImageMetadataFormat = false;
	static final String nativeImageMetadataFormatName = "com.apple.icns_1.0";
	static final String nativeImageMetadataFormatClassName = null;
	static final String[] extraImageMetadataFormatNames = null;
	static final String[] extraImageMetadataFormatClassNames = null;

	public ICNSImageWriterSpi() {
		super(vendorName, version, names, suffixes, MIMETypes, writerClassName, new Class[] { ImageOutputStream.class },
				readerSpiNames, supportsStandardStreamMetadataFormat, nativeStreamMetadataFormatName,
				nativeStreamMetadataFormatClassName, extraStreamMetadataFormatNames,
				extraStreamMetadataFormatClassNames, supportsStandardImageMetadataFormat, nativeImageMetadataFormatName,
				nativeImageMetadataFormatClassName, extraImageMetadataFormatNames, extraImageMetadataFormatClassNames);
	}

	public boolean canEncodeImage(ImageTypeSpecifier imageType) {
		return true;
	}

	public String getDescription(Locale locale) {
		return "SPI for ICNS Image Writer";
	}

	public ImageWriter createWriterInstance(Object extension) {
		return new ICNSImageWriter(this);
	}

}
