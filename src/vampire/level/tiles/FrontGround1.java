package vampire.level.tiles;

import vampire.graphics.Screen;
import vampire.graphics.Sprite;

public class FrontGround1 extends Tile {
	// ---------------------------------VARIABLES----------------------------------

	// -----------------------------CONSTRUCTORS_&_ACCESOS-------------------------

	public FrontGround1(Sprite[] sprites) {
		super(sprites);
		id = 'f' + 'G' + 1;
	}

	// ---------------------------------MÉTODOS----------------------------------

	public void render(int x, int y, Screen screen) {
		screen.renderTile(x, y, this, true);
	}
}
