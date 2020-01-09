# ImageIO ICNS

Allows Java's ImageIO to write the [Apple Icon Image Format](https://en.wikipedia.org/wiki/Apple_Icon_Image_format).

Restrictions: Only one resolution will be included in the output file. Reading is not supported, just writing.

## Acceptable ICNS pixel dimensions / type identifiers

| pixel size | @1x         | @2x           |
|------------|-------------|---------------|
| 16         | 16 = icp4   |               |
| 32         | 32 = icp5   | 16@2x = ic11  |
| 64         | 64 = icp6   | 32@2x = ic12  |
| 128        | 128 = ic07  |               |
| 256        | 256 = ic08  | 128@2x = ic13 |
| 512        | 512 = ic09  | 256@2x = ic14 |
| 1024       | 1024 = ic10 | 512@2x = ic10 |

The plugin will always try to match a @2x resolution type identifier. You can change this default behaviour with an ICNSWriteParam:

```java
ICNSWriteParam param = new ICNSWriteParam();
param.setDevicePixelRatio(1); // default = 2
```

## Example

```java
// Image must be scaled to fit one of the allowed dimensions.
// An exception will be thrown otherwise.
BufferedImage image;
...
boolean success = ImageIO.write(i, "ICNS", new File("icon.icns"));
```
