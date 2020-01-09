package info.hoereth.imageio.icns;

import java.util.Locale;

import javax.imageio.ImageWriteParam;

public class ICNSWriteParam extends ImageWriteParam {
	private int devicePixelRatio = 1;
	
	public ICNSWriteParam() {
		super(Locale.getDefault());
	}

	public int getDevicePixelRatio() {
		return devicePixelRatio;
	}

	public void setDevicePixelRatio(int devicePixelRatio) {
		this.devicePixelRatio = devicePixelRatio;
	}
}
