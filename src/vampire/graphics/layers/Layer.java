package vampire.graphics.layers;

import vampire.graphics.Screen;
import vampire.graphics.Sprite;
import vampire.level.tiles.BackGround0;
import vampire.level.tiles.Cloud;
import vampire.level.tiles.FrontGround0;
import vampire.level.tiles.FrontGround1;
import vampire.level.tiles.Tile;

public class Layer {

	// ---------------------------------VARIABLES----------------------------------

	protected double x, y;
	protected int width, height; // ????????
	protected Sprite sprite;
	protected Sprite[] sprites;
	protected Tile[] tiles;

	protected int id;

	// -----------------------------CONSTRUCTORS_&_ACCESOS-------------------------

	public Layer(double x, double y, Sprite sprite) {
		this.x = x;
		this.y = y;
		this.sprite = sprite;
	}

	public Layer(double x, double y, Sprite[] sprites) {
		this.x = x;
		this.y = y;
		this.sprites = sprites;
	}

	public Layer(double x, double y, int width, int height, int id) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.id = id;
		tiles = new Tile[width * height];
		fillTiles();
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public void setPosition(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public void addPosition(double x, double y) {
		this.x += x;
		this.y += y;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public Sprite getSprite() {
		return sprite;
	}

	public Sprite[] getSprites() {
		return sprites;
	}

	public Tile[] getTiles() {
		return tiles;
	}

	// ---------------------------------MÉTODOS----------------------------------

	/**
	 * Devuelve un tile en función de la y
	 * 
	 * @param y = valor asociado a cada tile en función de donde esté situado en su spriteSheet
	 */
	private Tile getTile(int y) {
		if (y == (Tile.cloud.getID() - id)) return new Cloud(Sprite.clouds);
		else if (y == ('f' + 'G' - id)) return new FrontGround0(Sprite.ground);
		else if (y == ('f' + 'G' + 1 - id)) return new FrontGround1(Sprite.ground);
		else if (y == ('b' + 'G' - id)) return new BackGround0(Sprite.bGround);
		else return null;
	}

	private void fillTiles() {
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				tiles[x + y * width] = getTile(y);
			}
		}
	}

	protected void move() {

	}

	protected void update() {
	}

	protected void render(Screen screen) {
	}

}
