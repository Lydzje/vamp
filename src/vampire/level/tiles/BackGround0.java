package vampire.level.tiles;

import vampire.graphics.Screen;
import vampire.graphics.Sprite;

public class BackGround0 extends Tile {
	// ---------------------------------VARIABLES----------------------------------

	// -----------------------------CONSTRUCTORS_&_ACCESOS-------------------------

	public BackGround0(Sprite[] sprites) {
		super(sprites);
		id = 'b' + 'G' + 0;
	}

	// ---------------------------------MÉTODOS----------------------------------

	public void render(int x, int y, Screen screen) {
		screen.renderTile(x, y, this,true);
	}
}
