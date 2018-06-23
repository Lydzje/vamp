package vampire.level.tiles;

import vampire.Game;
import vampire.graphics.Screen;
import vampire.graphics.Sprite;

public class Tile {

	// ---------------------------------VARIABLES----------------------------------

	protected int x, y;
	private Sprite sprite;
	private Sprite[] sprites;
	protected int id;

	public static Tile cloud = new Cloud(Sprite.clouds);
	//public static Tile fG0 = new FrontGround0(Sprite.fG0);
	//public static Tile fG1 = new FrontGround1(Sprite.fG1);
	//public static Tile bG0 = new BackGround0(Sprite.bG0);

	// -----------------------------CONSTRUCTORS_&_ACCESOS-------------------------

	public Tile(Sprite sprite) {
		this.sprite = sprite;
	}

	public Tile(Sprite[] sprites) {
		this.sprites = sprites;
		this.sprite = this.sprites[Game.random.nextInt(sprites.length)];
	
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public Sprite getSprite() {
		return sprite;
	}

	public void setRandomSprite() {
		sprite = sprites[Game.random.nextInt(sprites.length)];
	}

	public int getID() {
		return id;
	}

	// ---------------------------------MÉTODOS----------------------------------

	public void render(int x, int y, Screen screen) {

	}
}
