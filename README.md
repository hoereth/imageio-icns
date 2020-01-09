# ImageIO ICNF

Allows Java's ImageIO to write the [Apple Icon Image Format](https://en.wikipedia.org/wiki/Apple_Icon_Image_format).

Restrictions: Only one resolution will be included in the output file. Reading is not supported, just writing.

Allowed dimensions: 16x16, 32x32, 64x64, 128x128, 256x256, 512x512.

## Example

```java
// Image must be scaled to fit one of the allowed dimensions.
// An exception will be thrown otherwise.
BufferedImage image;
...
boolean success = ImageIO.write(i, "ICNS", new File("icon.icns"));
```
