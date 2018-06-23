package vampire.utils;

import static vampire.utils.maths.MathsUtils.clamp;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.DataBufferInt;

import vampire.graphics.Sprite;
import vampire.graphics.SpriteSheet;

public class ImageUtils {

	public static int addColor(int color, int r1, int g1, int b1) {

		int red = (color >> 16) & 0xFF;
		int green = (color >> 8) & 0xFF;
		int blue = color & 0xFF;

		red = clamp(red + r1, 0, 255);
		green = clamp(green + g1, 0, 255);
		blue = clamp(blue + b1, 0, 255);

		return ((red) << 16) | (green << 8) | (blue);

	}

	public static BufferedImage changeBrightness(BufferedImage original, int atenuation) {
		BufferedImage result = new BufferedImage(original.getWidth(), original.getHeight(),
				BufferedImage.TYPE_INT_ARGB);
		byte[] pixels = ((DataBufferByte) original.getRaster().getDataBuffer()).getData();
		int[] resultPixels = ((DataBufferInt) result.getRaster().getDataBuffer()).getData();

		int offset = 0;
		for (int yy = 0; yy < original.getHeight(); yy++) {
			for (int xx = 0; xx < original.getWidth(); xx++) {

				int a = Byte.toUnsignedInt(pixels[offset++]);
				int b = Byte.toUnsignedInt(pixels[offset++]);
				int g = Byte.toUnsignedInt(pixels[offset++]);
				int r = Byte.toUnsignedInt(pixels[offset++]);

				r = clamp(r + atenuation, 0, 255);
				g = clamp(g + atenuation, 0, 255);
				b = clamp(b + atenuation, 0, 255);

				resultPixels[xx + yy * result.getWidth()] = a << 24 | r << 16 | g << 8 | b;
			}
		}

		return result;
	}

	public static Sprite changeBrightness(Sprite original, int atenuation) {

		BufferedImage result = new BufferedImage(original.image.getWidth(), original.image.getHeight(),
				BufferedImage.TYPE_INT_ARGB);

		byte[] pixels = ((DataBufferByte) original.image.getRaster().getDataBuffer()).getData();
		int[] resultPixels = ((DataBufferInt) result.getRaster().getDataBuffer()).getData();

		int offset = 0;
		for (int yy = 0; yy < original.image.getHeight(); yy++) {
			for (int xx = 0; xx < original.image.getWidth(); xx++) {

				int a = Byte.toUnsignedInt(pixels[offset++]);
				int b = Byte.toUnsignedInt(pixels[offset++]);
				int g = Byte.toUnsignedInt(pixels[offset++]);
				int r = Byte.toUnsignedInt(pixels[offset++]);

				r = clamp(r + atenuation, 0, 255);
				g = clamp(g + atenuation, 0, 255);
				b = clamp(b + atenuation, 0, 255);

				resultPixels[xx + yy * result.getWidth()] = a << 24 | r << 16 | g << 8 | b;

			}
		}

		return new Sprite(0, 0, original.image.getWidth(), original.image.getHeight(),
				new SpriteSheet(result));
	}

}
