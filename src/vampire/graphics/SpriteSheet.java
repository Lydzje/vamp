package vampire.graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SpriteSheet {
	// ---------------------------------VARIABLES----------------------------------

	public int width, height;
	public int[] pixels;
	public Sprite[] sprites;

	// public static SpriteSheet groundTest = new SpriteSheet("/textures/walkeable/groundTest.png");

	// public static SpriteSheet backGroundSheet = new SpriteSheet(
	// "/textures/walkeable/sheets/backGroundSheet.png");
	public static SpriteSheet skySheet = new SpriteSheet("/textures/sky/sky.png");

	public static SpriteSheet moonSheet = new SpriteSheet("/textures/sky/moon.png");

	public static SpriteSheet cloudsSheet = new SpriteSheet("/textures/sky/clouds.png");

	// public static SpriteSheet backGroundSheet = new SpriteSheet(
	// "/textures/walkeable/sheets/backGroundSheet.png");

	// public static SpriteSheet frontGroundSheet = new SpriteSheet(
	// "/textures/walkeable/sheets/frontGroundSheet.png");

	// public static SpriteSheet backCastleSheet = new SpriteSheet(
	// "/textures/walkeable/sheets/backCastleSheet.png");

	// public static SpriteSheet frontCastleSheet = new SpriteSheet(
	// "/textures/walkeable/sheets/frontCastleSheet.png");

	// MENUS

	public static SpriteSheet startMenuBgSheet = new SpriteSheet("/textures/menu/menuBackground.png");
	public static SpriteSheet titleSheet = new SpriteSheet("/textures/menu/title.png");

	public static SpriteSheet AnimatedMenuSheet = new SpriteSheet("/textures/menu/animatedMenu.png");
	public static SpriteSheet animatedMenu = new SpriteSheet(AnimatedMenuSheet, 0, 0, 1, 4, 455, 256);

	// PLAYER INTERFACE

	public static SpriteSheet hBB = new SpriteSheet("/textures/UI/healthBarBack.png");
	public static SpriteSheet hBF = new SpriteSheet("/textures/UI/healthBarFront.png");

	// MAP TEXTURES

	public static SpriteSheet pilarSheet = new SpriteSheet("/textures/walkeable/sheets/pilar.png");

	public static SpriteSheet rocksBackSheet = new SpriteSheet("/textures/walkeable/sheets/rocksBack.png");

	public static SpriteSheet rocksSheet = new SpriteSheet("/textures/walkeable/sheets/rocks.png");

	public static SpriteSheet treeSheet = new SpriteSheet("/textures/walkeable/sheets/tree.png");

	public static SpriteSheet bGroundSheet = new SpriteSheet("/textures/walkeable/sheets/bGround.png");

	public static SpriteSheet groundSheet = new SpriteSheet("/textures/walkeable/sheets/ground.png");

	// public static SpriteSheet grassSheet = new SpriteSheet("/textures/walkeable/sheets/grass2.png");

	// public static SpriteSheet test = new SpriteSheet("/textures/walkeable/sheets/groundRenderTest.png");

	// PLAYERSHEETS
	public static SpriteSheet player = new SpriteSheet("/textures/mobs/player/playerSheet.png");
	public static SpriteSheet player_right = new SpriteSheet(player, 0, 0, 1, 4, 64);
	public static SpriteSheet player_left = new SpriteSheet(player, 1, 0, 1, 4, 64);
	public static SpriteSheet player_runningR = new SpriteSheet(player, 2, 0, 1, 4, 64);
	public static SpriteSheet player_runningL = new SpriteSheet(player, 3, 0, 1, 4, 64);

	public static SpriteSheet player_death = new SpriteSheet("/textures/mobs/player/death.png");
	public static SpriteSheet player_deathR = new SpriteSheet(player_death, 0, 0, 1, 8, 64);
	public static SpriteSheet player_deathL = new SpriteSheet(player_death, 1, 0, 1, 8, 64);

	public static SpriteSheet playerBloodAttackSheet = new SpriteSheet(
			"/textures/mobs/player/bloodAttack.png");
	public static SpriteSheet player_blood_attackR = new SpriteSheet(playerBloodAttackSheet, 0, 0, 1, 10, 96);
	public static SpriteSheet player_blood_attackL = new SpriteSheet(playerBloodAttackSheet, 1, 0, 1, 10, 96);

	// GreenGuy
	public static SpriteSheet greenGuy = new SpriteSheet("/textures/mobs/enemies/greenGuy.png");
	public static SpriteSheet greenGuy_right = new SpriteSheet(greenGuy, 0, 0, 1, 4, 64);
	public static SpriteSheet greenGuy_left = new SpriteSheet(greenGuy, 1, 0, 1, 4, 64);
	public static SpriteSheet greenGuy_runningR = new SpriteSheet(greenGuy, 2, 0, 1, 4, 64);
	public static SpriteSheet greenGuy_runningL = new SpriteSheet(greenGuy, 3, 0, 1, 4, 64);

	public static SpriteSheet greenGuyBasicAttackSheet = new SpriteSheet(
			"/textures/mobs/enemies/basicAttack.png");
	public static SpriteSheet greenGuy_blood_attackR = new SpriteSheet(greenGuyBasicAttackSheet, 0, 0, 1, 6,
			146, 96);
	public static SpriteSheet greenGuy_blood_attackL = new SpriteSheet(greenGuyBasicAttackSheet, 1, 0, 1, 6,
			146, 96);

	// -----------------------------CONSTRUCTORS_&_ACCESOS-------------------------

	public SpriteSheet(String path) {
		load(path);
	}

	public SpriteSheet(BufferedImage image) {
		load(image);
	}

	public SpriteSheet(SpriteSheet sheet, int x, int y, int width, int height, int spriteSize) {
		int xx = x * spriteSize;
		int yy = y * spriteSize;
		int w = width * spriteSize;
		int h = height * spriteSize;

		this.width = w;
		this.height = h;
		pixels = new int[w * h];
		for (int y0 = 0; y0 < h; y0++) {
			int yP = yy + y0;
			for (int x0 = 0; x0 < w; x0++) {
				int xP = xx + x0;
				pixels[x0 + y0 * w] = sheet.pixels[xP + yP * sheet.width];
			}
		}
		int frame = 0;
		sprites = new Sprite[width * height];
		for (int ya = 0; ya < height; ya++) {
			for (int xa = 0; xa < width; xa++) {
				int[] spritePixels = new int[spriteSize * spriteSize];
				for (int y0 = 0; y0 < spriteSize; y0++) {
					for (int x0 = 0; x0 < spriteSize; x0++) {
						spritePixels[x0 + y0 * spriteSize] = pixels[(x0 + xa * spriteSize)
								+ (y0 + ya * spriteSize) * this.width];
					}
				}
				Sprite sprite = new Sprite(spritePixels, spriteSize, spriteSize);
				sprites[frame++] = sprite;
			}
		}
	}

	public SpriteSheet(SpriteSheet sheet, int x, int y, int width, int height, int spriteW, int spriteH) {
		int xx = x * spriteW;
		int yy = y * spriteH;
		int w = width * spriteW;
		int h = height * spriteH;

		this.width = w;
		this.height = h;
		pixels = new int[w * h];
		for (int y0 = 0; y0 < h; y0++) {
			int yP = yy + y0;
			for (int x0 = 0; x0 < w; x0++) {
				int xP = xx + x0;
				pixels[x0 + y0 * w] = sheet.pixels[xP + yP * sheet.width];
			}
		}
		int frame = 0;
		sprites = new Sprite[width * height];
		for (int ya = 0; ya < height; ya++) {
			for (int xa = 0; xa < width; xa++) {
				int[] spritePixels = new int[spriteW * spriteH];
				for (int y0 = 0; y0 < spriteH; y0++) {
					for (int x0 = 0; x0 < spriteW; x0++) {
						spritePixels[x0 + y0 * spriteW] = pixels[(x0 + xa * spriteW) + (y0 + ya * spriteH)
								* this.width];
					}
				}
				Sprite sprite = new Sprite(spritePixels, spriteW, spriteH);
				sprites[frame++] = sprite;
			}
		}
	}

	public BufferedImage getImage() {
		return image;
	}

	// ---------------------------------MÉTODOS----------------------------------

	public BufferedImage image;

	private void load(String path) {

		try {
			System.out.print("Loading file from " + path);
			image = ImageIO.read(SpriteSheet.class.getResource(path));
			System.out.println(" succeded!");
			width = image.getWidth();
			height = image.getHeight();
			pixels = new int[width * height];
			image.getRGB(0, 0, width, height, pixels, 0, width);
			// for(int i =0; i<pixels.length;i++){
			// if(i%1000==0)System.out.println(pixels[i] + " cabesa" + i);
			// }
		} catch (IOException e) {
			System.err.println(" failed!");
			e.printStackTrace();
		}
	}

	private void load(BufferedImage image) {
		width = image.getWidth();
		height = image.getHeight();
		pixels = new int[width * height];
		image.getRGB(0, 0, width, height, pixels, 0, width);
	}

}
