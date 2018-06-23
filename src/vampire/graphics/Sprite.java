package vampire.graphics;

import java.awt.image.BufferedImage;

public class Sprite {

	// ---------------------------------VARIABLES----------------------------------

	public int x, y, width, height;
	public int[] pixels;
	public SpriteSheet sheet;
	public BufferedImage image;
	// public static Sprite groundTestSprite = new Sprite(0, 0, 455, 96, SpriteSheet.groundTest);

	// FRONT_GROUND_SPRITES
	// F1 : fila 1
	// public static Sprite[] fG0 = getArrayOfSprites(SpriteSheet.frontGroundSheet, 0, 32, 64);

	// F2 : fila 2
	// public static Sprite[] fG1 = getArrayOfSprites(SpriteSheet.frontGroundSheet, 2, 32, 32);

	// BACKGROUND_SPRITES
	// F1 : fila 1
	// public static Sprite[] bG0 = getArrayOfSprites(SpriteSheet.backGroundSheet, 0, 32, 32);

	// FRONT_CASTLE_SPRITES
	// F1 : fila 1
	// public static Sprite[] fC0 = getArrayOfSprites(SpriteSheet.frontCastleSheet, 0, 32, 64);

	// F2 : fila 2
	// public static Sprite[] fC1 = getArrayOfSprites(SpriteSheet.frontCastleSheet, 2, 32, 32);

	// BACKCASTLE_SPRITES
	// F1 : fila 1
	// public static Sprite[] bC0 = getArrayOfSprites(SpriteSheet.backCastleSheet, 0, 32, 32);

	// MENUS SPRITES

	public static Sprite startMenu = new Sprite(0, 0, 455, 256, SpriteSheet.startMenuBgSheet);
	public static Sprite title = new Sprite(0, 0, 151, 42, SpriteSheet.titleSheet);

	// PLAYER INTERFACE

	public static Sprite healthBarBack = new Sprite(0, 0, 186, 63, SpriteSheet.hBB);
	public static Sprite healthBarFront = new Sprite(0, 0, 186, 63, SpriteSheet.hBF);

	// MAPS SPRITES

	public static Sprite pilar = new Sprite(0, 0, 32, 96, SpriteSheet.pilarSheet);
	public static Sprite[] rocks = getArrayOfSprites(SpriteSheet.rocksSheet, 0, 96, 68);
	public static Sprite[] rocksBack = getArrayOfSprites(SpriteSheet.rocksBackSheet, 0, 96, 18);

	public static Sprite tree = new Sprite(0, 0, 96, 96, SpriteSheet.treeSheet);
	public static Sprite[] ground = getArrayOfSprites(SpriteSheet.groundSheet, 0, 96, 68);
	public static Sprite[] bGround = getArrayOfSprites(SpriteSheet.bGroundSheet, 0, 96, 18);

	// public static Sprite[] grass = getArrayOfSprites(SpriteSheet.grassSheet, 0, 96, 22);

	// SKY
	public static Sprite sky = new Sprite(0, 0, 455, 192, SpriteSheet.skySheet);

	public static Sprite moon = new Sprite(0, 0, 120, 120, SpriteSheet.moonSheet);

	public static Sprite[] clouds = getArrayOfSprites(SpriteSheet.cloudsSheet, 0, 96, 32);

	// public static Sprite test = new Sprite(0, 0, 3200, 81, SpriteSheet.test);

	// PARTICLE

	public static Sprite normalParticle = new Sprite(1, 1, 0xff29593E);
	public static Sprite deathParticle = new Sprite(1, 1, 0xff6F9999);

	// -----------------------------CONSTRUCTORS_&_ACCESOS-------------------------

	public Sprite(int x, int y, int width, int height, SpriteSheet sheet) {
		this.x = x * width;
		this.y = y * height;
		this.width = width;
		this.height = height;
		this.sheet = sheet;
		image = sheet.getImage();
		pixels = new int[this.width * this.height];
		load();
	}

	public Sprite(int[] pixels, int width, int height) {
		this.width = width;
		this.height = height;
		this.pixels = new int[pixels.length];
		for (int i = 0; i < pixels.length; i++) {
			this.pixels[i] = pixels[i];
		}

	}

	public Sprite(int width, int height, int color) {
		this.width = width;
		this.height = height;
		this.pixels = new int[this.width * this.height];
		setColor(color);
	}

	// ---------------------------------MÉTODOS----------------------------------

	public void setColor(int color) {
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = color;
		}
	}

	public void load() {
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				pixels[x + y * width] = sheet.pixels[(x + this.x) + (y + this.y) * sheet.width];
			}
		}
	}

	private static Sprite[] getArrayOfSprites(SpriteSheet sheet, int row, int width, int height) {
		int length = sheet.width / width;
		Sprite[] result = new Sprite[length];

		for (int x = 0; x < length; x++) {
			result[x] = new Sprite(x, row, width, height, sheet);
			// for (int i = 0; i < result[x].pixels.length; i++) {
			// if(i%70==0)System.out.println(result[x].pixels[i] + " cabesa " +i ) ;
			// }
		}

		return result;
	}
}
