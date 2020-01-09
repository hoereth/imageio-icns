package info.hoereth.imageio.icns;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * ICNS relevant OSTypes (https://en.wikipedia.org/wiki/FourCC)
 */
public enum OSType {
	icp4(16, 1), icp5(32, 1), icp6(64, 1), ic07(128, 1), ic08(256, 1), ic09(512, 1), ic10(1024, 2), ic11(32, 2),
	ic12(64, 2), ic13(256, 2);

	private final int pixelSize;
	private final int devicePixelRatio;

	private OSType(int pixelSize, int devicePixelRatio) {
		this.pixelSize = pixelSize;
		this.devicePixelRatio = devicePixelRatio;
	}

	public int getPixelSize() {
		return pixelSize;
	}

	public int getDevicePixelRatio() {
		return devicePixelRatio;
	}

	@Override
	public String toString() {
		return name() + ": " + pixelSize + "x" + pixelSize
				+ (devicePixelRatio != 1 ? "@" + devicePixelRatio + "x" : "");
	}

	public static OSType valueOf(int pixelSize, int devicePixelRatio) {
		for (OSType type : values()) {
			if (type.pixelSize == pixelSize && type.devicePixelRatio == devicePixelRatio)
				return type;
		}
		String types = Arrays.stream(values()).map(e -> e.toString()).collect(Collectors.joining(", "));
		throw new IllegalArgumentException("No type found for pixel size " + pixelSize + " and device pixel ratio "
				+ devicePixelRatio + ". Available types: " + types);
	}

	public static OSType valueOf(int pixelSize) {
		List<OSType> candidates = new ArrayList<>();
		for (OSType type : values()) {
			if (type.pixelSize == pixelSize)
				candidates.add(type);
		}
		candidates.sort((t1, t2) -> {
			int primary = t2.pixelSize - t1.pixelSize;
			if (primary == 0) {
				return t2.devicePixelRatio - t1.devicePixelRatio;
			} else {
				return primary;
			}
		});
		if (candidates.size() == 0) {
			String sizes = Arrays.stream(values()).map(e -> String.valueOf(e.pixelSize))
					.collect(Collectors.joining(", "));
			throw new IllegalArgumentException(
					"No type found for pixel size " + pixelSize + ". Available sizes: " + sizes);
		} else {
			return candidates.get(0);
		}
	}

}
