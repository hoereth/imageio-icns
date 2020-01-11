
# 🍏 ImageIO Plugin for writing ICNS (Apple Icon) Images

Allows Java's ImageIO framework to write the [Apple Icon Image Format](https://en.wikipedia.org/wiki/Apple_Icon_Image_format).

Restrictions: Only one resolution will be included in the output file. Reading is **not** supported, just **writing**.

## Maven Coordinates

```xml
<!-- https://mvnrepository.com/artifact/de.pentabyte/imageio-icns -->
<dependency>
    <groupId>de.pentabyte</groupId>
    <artifactId>imageio-icns</artifactId>
    <version>1.3</version>
</dependency>
```

## Acceptable Input Image Dimensions

| pixel size | @1x ICNS type | @2x ICNS type   |
|------------|---------------|-----------------|
| 16         | icp4          | fallback = icp4 |
| 32         | icp5          | 16@2x = ic11    |
| 64         | icp6          | 32@2x = ic12    |
| 128        | ic07          | fallback = ic07 |
| 256        | ic08          | 128@2x = ic13   |
| 512        | ic09          | 256@2x = ic14   |
| 1024       | ic10          | 512@2x = ic10   |

You must provide an input image with equal width and height of an acceptable pixel size.
The plugin will always try to match a @2x resolution ICNS type identifier, otherwise
it will fall back to @1x resolution. You can change this default behavior with the ICNSWriteParam:

```java
ICNSWriteParam param = new ICNSWriteParam();
param.setDevicePixelRatio(1); // default = 2
```

## Example

```java
// You need to reference the library at least once
// to kick off auto service-discovery.
import de.pentabyte.imageio.icns.ICNS;
// Image must be sized to fit one of the allowed dimensions.
// An exception will be thrown otherwise.
BufferedImage image;
...
boolean foundWriter = ImageIO.write(i, ICNS.NAME, new File("icon.icns"));
```
