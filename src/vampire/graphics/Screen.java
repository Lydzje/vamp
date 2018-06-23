package vampire.graphics;

import vampire.Game;
import vampire.graphics.layers.Layer;
import vampire.level.tiles.Tile;
import vampire.utils.ImageUtils;

public class Screen {

	// ---------------------------------VARIABLES----------------------------------

	private int width, height;
	private int xOffset, yOffset;
	private int[] pixels;

	// -----------------------------CONSTRUCTORS_&_ACCESOS-------------------------

	public Screen(int width, int height, int[] pixels) {
		this.width = width;
		this.height = height;
		this.pixels = pixels;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public void setOffset(int xOffset, int yOffset) {
		this.xOffset = xOffset;
		this.yOffset = yOffset;
	}

	// ---------------------------------MÉTODOS----------------------------------

	public void clean() {
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = 0xffCAD5D9;
		}
	}

	public void drawRectangle(int xp, int yp, int width, int height, int color, boolean fixed) {
		if (fixed) {
			xp -= xOffset;
			yp -= yOffset;
		}
		int yO, xO;
		for (int y = 0; y < height; y++) {
			yO = y + yp;
			for (int x = 0; x < width; x++) {
				xO = x + xp;
				pixels[xO + yO * this.width] = color + 0x00ff0000;
			}
		}
	}

	public void renderRandomLayerSprites(int xp, int yp, Layer layer) {
		xp -= xOffset;
		yp -= yOffset;

		int xA, yA;
		int index = Game.random.nextInt(layer.getSprites().length);
		int w = layer.getSprites()[index].width;
		int h = layer.getSprites()[index].height;
		for (int y = 0; y < h; y++) {
			yA = y + yp;
			for (int x = 0; x < w; x++) {
				xA = x + xp;

				if (xA < 0 || xA >= width || yA < 0 || yA >= height) continue;
				pixels[xA + yA * width] = layer.getSprites()[index].pixels[x + y * w];
				// System.out.println(layer.getSprites()[index].pixels[x + y * w]);
			}
		}
	}

	public void renderBasicLayer(int xp, int yp, Layer layer) {
		xp -= xOffset;
		yp -= yOffset;

		int xA, yA;
		int w = layer.getSprite().width;
		int h = layer.getSprite().height;
		for (int y = 0; y < h; y++) {
			yA = y + yp;
			for (int x = 0; x < w; x++) {
				xA = x + xp;

				if (xA < 0 || xA >= width || yA < 0 || yA >= height) continue;
				pixels[xA + yA * width] = layer.getSprite().pixels[x + y * w];
			}
		}

	}

	public void renderSprite(int xp, int yp, Sprite sprite, boolean fixed) {
		if (fixed) {
			xp -= xOffset;
			yp -= yOffset;
		}
		int xA, yA;
		int w = sprite.width;
		int h = sprite.height;
		for (int y = 0; y < h; y++) {
			yA = y + yp;
			for (int x = 0; x < w; x++) {
				xA = x + xp;
				if (xA < 0 || xA >= width || yA < 0 || yA >= height) continue;
				int color = sprite.pixels[x + y * w];
				if (color != 0x00 && color != 0x00ffffff) pixels[xA + yA * width] = color;
			}
		}
	}

	public void renderMob(int xp, int yp, Sprite sprite, boolean fixed, boolean hurt) {
		if (fixed) {
			xp -= xOffset;
			yp -= yOffset;
		}
		int xA, yA;
		int w = sprite.width;
		int h = sprite.height;
		for (int y = 0; y < h; y++) {
			yA = y + yp;
			for (int x = 0; x < w; x++) {
				xA = x + xp;
				if (xA < 0 || xA >= width || yA < 0 || yA >= height) continue;
				int color = sprite.pixels[x + y * w];
				if (color != 0x00 && color != 0x00ffffff) {
					if (hurt) color = ImageUtils.addColor(color, 80, -10, -10);
					pixels[xA + yA * width] = color;
				}
			}
		}
	}

	public void renderTile(int xp, int yp, Tile tile, boolean fixed) {
		if (fixed) {
			xp -= xOffset;
			yp -= yOffset;
		}
		int xA, yA;
		int w = tile.getSprite().width;
		int h = tile.getSprite().height;
		for (int y = 0; y < h; y++) {
			yA = y + yp;
			for (int x = 0; x < w; x++) {
				xA = x + xp;
				if (xA < 0 || xA >= width || yA < 0 || yA >= height) continue;
				int color = tile.getSprite().pixels[x + y * w];
				if (color != 0x00 && color != 0x00ffffff) pixels[xA + yA * width] = color;
			}
		}
	}

	public void renderTiledLayer(int xp, int yp, Layer layer) {

		for (int x = 0; x < layer.getWidth(); x++) {
			for (int y = 0; y < layer.getHeight(); y++) {
				Tile tile = layer.getTiles()[x + y * layer.getWidth()];
				tile.render(x * tile.getSprite().width + xp, y * tile.getSprite().height * 2 + yp, this);
			}
		}
	}

}